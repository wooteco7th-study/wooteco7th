package store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import store.domain.calculator.ResultCalculator;
import store.domain.order.Order;
import store.domain.promotion.ProcessType;
import store.domain.promotion.PromotionProcessor;
import store.domain.stock.Inventory;
import store.domain.stock.Stocks;
import store.dto.InventoryDto;
import store.dto.ReceiptResultDto;
import store.dto.ResultDto;

public class StoreService {

    private static final int GIFT_QUANTITY_INCREASE_UNIT = 1;

    public List<InventoryDto> convertToInventoryDtos(final Inventory inventory) {
        return inventory.getInventory().entrySet()
                .stream()
                .map(this::makeInventoryDto)
                .flatMap(List::stream)
                .toList();
    }

    private List<InventoryDto> makeInventoryDto(final Entry<String, Stocks> entry) {
        Stocks stocks = entry.getValue();
        List<InventoryDto> dtos = new ArrayList<>();
        if (stocks.hasPromotion()) {
            dtos.add(makeDto(entry, stocks.hasPromotion()));
        }
        dtos.add(makeDto(entry, false));
        return dtos;
    }

    private InventoryDto makeDto(final Entry<String, Stocks> entry, final boolean hasPromotion) {
        Stocks stocks = entry.getValue();
        return new InventoryDto(entry.getKey(), stocks.getPrice(),
                stocks.getQuantity(stocks, hasPromotion),
                stocks.getPromotionName(hasPromotion));
    }

    public ResultDto processOrder(final Order order, final Stocks stocks) {
        PromotionProcessor promotionProcessor = new PromotionProcessor(stocks);
        return promotionProcessor.process(order);
    }

    public ResultDto excludeRegularPurchaseQuantity(final ResultDto resultDto, final Stocks stocks) {
        int promotionPurchaseQuantity = resultDto.promotionPurchaseQuantity();
        stocks.subtractPromotionStock(promotionPurchaseQuantity);
        return new ResultDto(ProcessType.ONLY_PROMOTION, resultDto.productName(), resultDto.promotionPurchaseQuantity(),
                0, resultDto.giftQuantity(), resultDto.price(), 0);
    }

    public ResultDto processMixedPurchase(final ResultDto resultDto, final Stocks stocks) {
        int totalPurchaseQuantity = resultDto.promotionPurchaseQuantity() + resultDto.regularPurchaseQuantity();
        int remainingQuantity = totalPurchaseQuantity - stocks.getPromotionStockQuantity();
        stocks.subtractPromotionStock(stocks.getPromotionStockQuantity());
        stocks.subtractRegularStock(remainingQuantity);
        return resultDto;
    }

    public ResultDto addGiftQuantity(final ResultDto resultDto, final Stocks stocks) {
        int purchaseQuantity = resultDto.promotionPurchaseQuantity();
        int giftQuantity = resultDto.giftQuantity();
        int totalPromotionQuantity = purchaseQuantity + giftQuantity + GIFT_QUANTITY_INCREASE_UNIT;
        stocks.subtractPromotionStock(totalPromotionQuantity);
        return new ResultDto(ProcessType.ONLY_PROMOTION, resultDto.productName(),
                purchaseQuantity + GIFT_QUANTITY_INCREASE_UNIT,
                0, giftQuantity + GIFT_QUANTITY_INCREASE_UNIT, resultDto.price(), 0);
    }

    public ResultDto continuePurchase(final ResultDto resultDto, final Stocks stocks) {
        int purchaseQuantity = resultDto.promotionPurchaseQuantity();
        stocks.subtractPromotionStock(purchaseQuantity);
        return new ResultDto(ProcessType.ONLY_PROMOTION, resultDto.productName(), purchaseQuantity, 0,
                resultDto.giftQuantity(), resultDto.price(), 0);
    }

    public ReceiptResultDto convertToReceiptResultDtoWithMembership(final List<ResultDto> dtos) {
        ResultCalculator resultCalculator = new ResultCalculator(dtos);
        int totalQuantity = resultCalculator.calculateTotalQuantity();
        int totalPrice = resultCalculator.calcuclateTotalPrice();
        int promotionDiscount = resultCalculator.calculatePromotionDiscount();
        int membershipDiscount = resultCalculator.calculateMembershipDiscount();
        int payPrice = totalPrice - promotionDiscount - membershipDiscount;
        return new ReceiptResultDto(totalQuantity, totalPrice, promotionDiscount, membershipDiscount, payPrice);
    }

    public ReceiptResultDto convertToReceiptResultDto(final List<ResultDto> dtos) {
        ResultCalculator resultCalculator = new ResultCalculator(dtos);
        int totalQuantity = resultCalculator.calculateTotalQuantity();
        int totalPrice = resultCalculator.calcuclateTotalPrice();
        int promotionDiscount = resultCalculator.calculatePromotionDiscount();
        int payPrice = totalPrice - promotionDiscount;
        return new ReceiptResultDto(totalQuantity, totalPrice, promotionDiscount, 0, payPrice);
    }
}
