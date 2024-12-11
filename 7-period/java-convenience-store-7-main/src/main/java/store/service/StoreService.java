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
import store.domain.PurchaseState;
import store.dto.ReceiptResultDto;

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

    public PurchaseState processOrder(final Order order, final Stocks stocks) {
        PromotionProcessor promotionProcessor = new PromotionProcessor(stocks);
        return promotionProcessor.process(order);
    }

    public PurchaseState excludeRegularPurchaseQuantity(final PurchaseState purchaseState,
                                                        final Stocks stocks) {
        int promotionPurchaseQuantity = purchaseState.promotionPurchaseQuantity();
        stocks.subtractPromotionStock(promotionPurchaseQuantity);
        return new PurchaseState(ProcessType.ONLY_PROMOTION, purchaseState.productName(),
                purchaseState.promotionPurchaseQuantity(), 0, purchaseState.giftQuantity(),
                purchaseState.price());
    }

    public PurchaseState processMixedPurchase(final PurchaseState purchaseState, final Stocks stocks) {
        int totalPurchaseQuantity =
                purchaseState.promotionPurchaseQuantity() + purchaseState.regularPurchaseQuantity();
        int remainingQuantity = totalPurchaseQuantity - stocks.getPromotionStockQuantity();
        stocks.subtractPromotionStock(stocks.getPromotionStockQuantity());
        stocks.subtractRegularStock(remainingQuantity);
        return purchaseState;
    }

    public PurchaseState addGiftQuantity(final PurchaseState purchaseState, final Stocks stocks) {
        int purchaseQuantity = purchaseState.promotionPurchaseQuantity();
        int giftQuantity = purchaseState.giftQuantity();
        int totalPromotionQuantity = purchaseQuantity + giftQuantity + GIFT_QUANTITY_INCREASE_UNIT;
        stocks.subtractPromotionStock(totalPromotionQuantity);
        return new PurchaseState(ProcessType.ONLY_PROMOTION, purchaseState.productName(),
                purchaseQuantity + GIFT_QUANTITY_INCREASE_UNIT,
                0, giftQuantity + GIFT_QUANTITY_INCREASE_UNIT, purchaseState.price());
    }

    public PurchaseState continuePurchaseWithoutAddingGift(final PurchaseState purchaseState,
                                                           final Stocks stocks) {
        int purchaseQuantity = purchaseState.promotionPurchaseQuantity();
        stocks.subtractPromotionStock(purchaseQuantity);
        return new PurchaseState(ProcessType.ONLY_PROMOTION, purchaseState.productName(), purchaseQuantity, 0,
                purchaseState.giftQuantity(), purchaseState.price());
    }

    public ReceiptResultDto convertToReceiptResultDtoWithMembership(final List<PurchaseState> dtos) {
        ResultCalculator resultCalculator = new ResultCalculator(dtos);
        int totalQuantity = resultCalculator.calculateTotalQuantity();
        int totalPrice = resultCalculator.calculateTotalPrice();
        int promotionDiscount = resultCalculator.calculatePromotionDiscount();
        int membershipDiscount = resultCalculator.calculateMembershipDiscount();
        int payPrice = totalPrice - promotionDiscount - membershipDiscount;
        return new ReceiptResultDto(totalQuantity, totalPrice, promotionDiscount, membershipDiscount, payPrice);
    }

    public ReceiptResultDto convertToReceiptResultDto(final List<PurchaseState> dtos) {
        ResultCalculator resultCalculator = new ResultCalculator(dtos);
        int totalQuantity = resultCalculator.calculateTotalQuantity();
        int totalPrice = resultCalculator.calculateTotalPrice();
        int promotionDiscount = resultCalculator.calculatePromotionDiscount();
        int payPrice = totalPrice - promotionDiscount;
        return new ReceiptResultDto(totalQuantity, totalPrice, promotionDiscount, 0, payPrice);
    }
}
