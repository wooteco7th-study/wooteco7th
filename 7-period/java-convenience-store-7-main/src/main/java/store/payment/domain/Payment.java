package store.payment.domain;

import java.util.List;
import java.util.Objects;
import store.constant.StoreCommand;
import store.payment.dto.BenefitProductReceipt;
import store.payment.dto.PaymentReceipt;
import store.payment.dto.PurchaseProductReceipt;
import store.product.domain.ProductRepository;
import store.product.domain.PurchaseProduct;
import store.product.domain.PurchaseProductGroup;
import store.product.domain.PurchaseProductQuantity;
import store.promotion.domain.Promotion;
import store.promotion.domain.PromotionRepository;

public class Payment {

    private static final int MEMBERSHIP_DISCOUNT_MAX = 8_000;
    private static final double MEMBERSHIP_DISCOUNT_RATIO = 0.3;
    private final PromotionRepository promotionRepository;
    private final PurchaseProductGroup purchaseProductGroup;
    private final ProductRepository productRepository;
    private int memberShipDiscount = 0;

    public Payment(final PromotionRepository promotionRepository,
                   final PurchaseProductGroup purchaseProductGroup, final ProductRepository productRepository) {
        this.promotionRepository = promotionRepository;
        this.purchaseProductGroup = purchaseProductGroup;
        this.productRepository = productRepository;
    }

    public void applyMemberShip(final StoreCommand storeCommand) {
        if (Objects.equals(storeCommand, StoreCommand.YES)) {
            memberShipDiscount = calculateMemberShipDiscount();
        }
        if (Objects.equals(storeCommand, StoreCommand.NO)) {
            memberShipDiscount = 0;
        }
    }

    public List<PurchaseProductReceipt> getPurchaseProductReceipts() {
        return purchaseProductGroup.getPurchaseProducts().stream()
                .map(PurchaseProductReceipt::of)
                .toList();
    }

    public List<BenefitProductReceipt> getBenefitProductReceipts() {
        return purchaseProductGroup.getPurchaseProducts().stream()
                .filter(purchaseProduct -> calculateBenefitQuantity(purchaseProduct) > 0)
                .map(purchaseProduct -> new BenefitProductReceipt(purchaseProduct.getName(),
                        calculateBenefitQuantity(purchaseProduct)))
                .toList();
    }

    public PaymentReceipt getPaymentReceipt() {
        final int totalPrice = purchaseProductGroup.calculateTotalPrice();
        final int promotionDiscount = purchaseProductGroup.getPurchaseProducts().stream()
                .mapToInt(purchaseProduct -> calculateBenefitQuantity(purchaseProduct) * purchaseProduct.getPrice())
                .sum();
        return new PaymentReceipt(purchaseProductGroup.calculateTotalQuantity(), totalPrice, promotionDiscount,
                memberShipDiscount,
                totalPrice - promotionDiscount - memberShipDiscount);
    }

    public void purchase() {
        final List<PurchaseProduct> purchaseProducts = purchaseProductGroup.getPurchaseProducts();
        for (PurchaseProduct purchaseProduct : purchaseProducts) {
            deduct(purchaseProduct);
        }
    }

    private void deduct(final PurchaseProduct purchaseProduct) {
        final PurchaseProductQuantity purchaseProductQuantity = purchaseProduct.getPurchaseProductQuantity();
        if (purchaseProduct.hasPromotion()) {
            productRepository.findPromotionProductByProductName(purchaseProduct.getName()).subtractQuantity(
                    purchaseProductQuantity.getPromotionQuantity()
                            + purchaseProductQuantity.getPromotionAndNonPromotionQuantity());
        }
        if (purchaseProductQuantity.getNonPromotionQuantity() > 0) {
            productRepository.findNonProductByProductName(purchaseProduct.getName())
                    .subtractQuantity(purchaseProductQuantity.getNonPromotionQuantity());
        }
    }


    private int calculateMemberShipDiscount() {
        final int discount = (int) ((double) purchaseProductGroup.calculateNonPromotionTotalPrice()
                * MEMBERSHIP_DISCOUNT_RATIO);
        return Math.min(MEMBERSHIP_DISCOUNT_MAX, discount);
    }

    private int calculateBenefitQuantity(final PurchaseProduct purchaseProduct) {
        final Promotion promotion = promotionRepository.findByName(purchaseProduct.getPromotionName());
        final int promotionQuantity = purchaseProduct.getPurchaseProductQuantity().getPromotionQuantity();
        return promotion.calculateBenefitQuantity(promotionQuantity);
    }

}
