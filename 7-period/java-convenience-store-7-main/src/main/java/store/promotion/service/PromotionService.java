package store.promotion.service;

import java.util.Objects;
import store.constant.StoreCommand;
import store.product.domain.Product;
import store.product.domain.ProductRepository;
import store.product.domain.PurchaseProduct;
import store.product.domain.PurchaseProductQuantity;
import store.promotion.domain.Promotion;
import store.promotion.domain.PromotionRepository;
import store.promotion.dto.NonPromotionCheckResult;
import store.promotion.dto.PromotionCheckResult;

public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    public PromotionService(final PromotionRepository promotionRepository, final ProductRepository productRepository) {
        this.promotionRepository = promotionRepository;
        this.productRepository = productRepository;
    }

    public void applyPromotion(final StoreCommand storeCommand, final PurchaseProduct purchaseProduct,
                               final PromotionCheckResult promotionCheckResult) {
        final int promotionQuantity = purchaseProduct.getPurchaseProductQuantity().getPromotionQuantity();
        if (Objects.equals(storeCommand, StoreCommand.YES)) {
            purchaseProduct.getPurchaseProductQuantity()
                    .updatePromotionQuantity(promotionQuantity + promotionCheckResult.quantity());
        }
        if (Objects.equals(storeCommand, StoreCommand.NO)) {
            final Promotion promotion = promotionRepository.findByName(purchaseProduct.getPromotionName());
            purchaseProduct.getPurchaseProductQuantity().updatePromotionQuantity(promotionQuantity - promotion.getBuy());
            purchaseProduct.getPurchaseProductQuantity()
                    .updateNonPromotionQuantity(promotion.getBuy());
        }
    }

    public void applyNonPromotion(final StoreCommand storeCommand, final PurchaseProduct purchaseProduct,
                                  final NonPromotionCheckResult nonPromotionCheckResult) {
        final int promotionQuantity = purchaseProduct.getPurchaseProductQuantity().getPromotionQuantity();
        purchaseProduct.getPurchaseProductQuantity()
                .updatePromotionQuantity(promotionQuantity - nonPromotionCheckResult.promotion());
        if (Objects.equals(storeCommand, StoreCommand.YES)) {
            purchaseProduct.getPurchaseProductQuantity()
                    .updatePromotionAndNonPromotionQuantity(nonPromotionCheckResult.promotion());
        }
        if (Objects.equals(storeCommand, StoreCommand.NO)) {
            final int nonPromotionQuantity = purchaseProduct.getPurchaseProductQuantity().getNonPromotionQuantity();
            purchaseProduct.getPurchaseProductQuantity()
                    .updateNonPromotionQuantity(nonPromotionQuantity - nonPromotionCheckResult.normal());
        }
    }


    public PromotionCheckResult getPromotionCheckResult(final PurchaseProduct purchaseProduct) {
        return new PromotionCheckResult(purchaseProduct.getName(), calculatePromotionQuantity(purchaseProduct));
    }

    public NonPromotionCheckResult getNonPromotionCheckResult(final PurchaseProduct purchaseProduct) {
        final int promotionQuantity = purchaseProduct.getPurchaseProductQuantity().getPromotionQuantity();
        final int nonPromotionQuantity = purchaseProduct.getPurchaseProductQuantity().getNonPromotionQuantity();
        final Promotion promotion = promotionRepository.findByName(purchaseProduct.getPromotionName());
        return new NonPromotionCheckResult(purchaseProduct.getName(), nonPromotionQuantity,
                promotion.calculateNonPromotionQuantity(promotionQuantity));
    }

    public boolean canNotReceivePromotion(final PurchaseProduct purchaseProduct) {
        if (!purchaseProduct.hasPromotion()) {
            return false;
        }
        final Promotion promotion = promotionRepository.findByName(purchaseProduct.getPromotionName());
        final int promotionQuantity = purchaseProduct.getPurchaseProductQuantity().getPromotionQuantity();
        return promotion.isIn() && promotion.calculateNonPromotionQuantity(promotionQuantity) > 0;
    }

    public boolean canReceivePromotion(final PurchaseProduct purchaseProduct) {
        if (!purchaseProduct.hasPromotion()) {
            return false;
        }
        final Product promotionProductByProductName = productRepository.findPromotionProductByProductName(
                purchaseProduct.getName());
        final Promotion promotion = promotionRepository.findByName(purchaseProduct.getPromotionName());
        final int promotionQuantity = purchaseProduct.getPurchaseProductQuantity().getPromotionQuantity();
        return promotionProductByProductName.getQuantity() > promotionQuantity && promotion.isIn()
                && promotion.calculatePromotionQuantity(promotionQuantity) > 0;
    }


    private int calculatePromotionQuantity(final PurchaseProduct purchaseProduct) {
        final Promotion promotion = promotionRepository.findByName(purchaseProduct.getPromotionName());
        final PurchaseProductQuantity purchaseProductQuantity = purchaseProduct.getPurchaseProductQuantity();
        final int promotionQuantity = purchaseProductQuantity.getPromotionQuantity();
        return promotion.calculatePromotionQuantity(promotionQuantity);
    }
}
