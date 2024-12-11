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
import store.dto.PurchaseStateDto;

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

    public PurchaseStateDto processOrder(final Order order, final Stocks stocks) {
        PromotionProcessor promotionProcessor = new PromotionProcessor(stocks);
        return promotionProcessor.process(order);
    }

    public PurchaseStateDto excludeRegularPurchaseQuantity(final PurchaseStateDto purchaseStateDto, final Stocks stocks) {
        int promotionPurchaseQuantity = purchaseStateDto.promotionPurchaseQuantity();
        stocks.subtractPromotionStock(promotionPurchaseQuantity);
        return new PurchaseStateDto(ProcessType.ONLY_PROMOTION, purchaseStateDto.productName(), purchaseStateDto.promotionPurchaseQuantity(),
                0, purchaseStateDto.giftQuantity(), purchaseStateDto.price());
    }

    public PurchaseStateDto processMixedPurchase(final PurchaseStateDto purchaseStateDto, final Stocks stocks) {
        int totalPurchaseQuantity = purchaseStateDto.promotionPurchaseQuantity() + purchaseStateDto.regularPurchaseQuantity();
        int remainingQuantity = totalPurchaseQuantity - stocks.getPromotionStockQuantity();
        stocks.subtractPromotionStock(stocks.getPromotionStockQuantity());
        stocks.subtractRegularStock(remainingQuantity);
        return purchaseStateDto;
    }

    public PurchaseStateDto addGiftQuantity(final PurchaseStateDto purchaseStateDto, final Stocks stocks) {
        int purchaseQuantity = purchaseStateDto.promotionPurchaseQuantity();
        int giftQuantity = purchaseStateDto.giftQuantity();
        int totalPromotionQuantity = purchaseQuantity + giftQuantity + GIFT_QUANTITY_INCREASE_UNIT;
        stocks.subtractPromotionStock(totalPromotionQuantity);
        return new PurchaseStateDto(ProcessType.ONLY_PROMOTION, purchaseStateDto.productName(),
                purchaseQuantity + GIFT_QUANTITY_INCREASE_UNIT,
                0, giftQuantity + GIFT_QUANTITY_INCREASE_UNIT, purchaseStateDto.price());
    }

    public PurchaseStateDto continuePurchaseWithoutAddingGift(final PurchaseStateDto purchaseStateDto, final Stocks stocks) {
        int purchaseQuantity = purchaseStateDto.promotionPurchaseQuantity();
        stocks.subtractPromotionStock(purchaseQuantity);
        return new PurchaseStateDto(ProcessType.ONLY_PROMOTION, purchaseStateDto.productName(), purchaseQuantity, 0,
                purchaseStateDto.giftQuantity(), purchaseStateDto.price());
    }

    public ReceiptResultDto convertToReceiptResultDtoWithMembership(final List<PurchaseStateDto> dtos) {
        ResultCalculator resultCalculator = new ResultCalculator(dtos);
        int totalQuantity = resultCalculator.calculateTotalQuantity();
        int totalPrice = resultCalculator.calculateTotalPrice();
        int promotionDiscount = resultCalculator.calculatePromotionDiscount();
        int membershipDiscount = resultCalculator.calculateMembershipDiscount();
        int payPrice = totalPrice - promotionDiscount - membershipDiscount;
        return new ReceiptResultDto(totalQuantity, totalPrice, promotionDiscount, membershipDiscount, payPrice);
    }

    public ReceiptResultDto convertToReceiptResultDto(final List<PurchaseStateDto> dtos) {
        ResultCalculator resultCalculator = new ResultCalculator(dtos);
        int totalQuantity = resultCalculator.calculateTotalQuantity();
        int totalPrice = resultCalculator.calculateTotalPrice();
        int promotionDiscount = resultCalculator.calculatePromotionDiscount();
        int payPrice = totalPrice - promotionDiscount;
        return new ReceiptResultDto(totalQuantity, totalPrice, promotionDiscount, 0, payPrice);
    }
}
