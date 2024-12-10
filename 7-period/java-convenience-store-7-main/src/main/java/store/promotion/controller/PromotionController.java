package store.promotion.controller;

import java.util.List;
import store.constant.StoreCommand;
import store.product.domain.PurchaseProduct;
import store.product.domain.PurchaseProductGroup;
import store.promotion.dto.NonPromotionCheckResult;
import store.promotion.dto.PromotionCheckResult;
import store.promotion.service.PromotionService;
import store.promotion.view.PromotionInputView;
import store.promotion.view.PromotionOutputView;
import store.util.LoopTemplate;

public class PromotionController {

    private final PromotionInputView promotionInputView;
    private final PromotionOutputView promotionOutputView;
    private final PromotionService promotionService;
    private final PurchaseProductGroup purchaseProductGroup;

    public PromotionController(final PromotionInputView promotionInputView, final PromotionOutputView promotionOutputView,
                               final PromotionService promotionService,
                               final PurchaseProductGroup purchaseProductGroup) {
        this.promotionInputView = promotionInputView;
        this.promotionOutputView = promotionOutputView;
        this.promotionService = promotionService;
        this.purchaseProductGroup = purchaseProductGroup;
    }

    public void run() {
        checkPromotion();
        checkNonPromotion();
    }

    private void checkPromotion() {
        final List<PurchaseProduct> purchaseProducts = purchaseProductGroup.getPurchaseProducts();
        for (PurchaseProduct purchaseProduct : purchaseProducts) {
            if (promotionService.canReceivePromotion(purchaseProduct)) {
                final PromotionCheckResult promotionCheckResult = promotionService.getPromotionCheckResult(
                        purchaseProduct);
                final StoreCommand storeCommand = requestPromotionCommand(promotionCheckResult);
                promotionService.applyPromotion(storeCommand, purchaseProduct, promotionCheckResult);
            }
        }
    }

    private void checkNonPromotion() {
        final List<PurchaseProduct> purchaseProducts = purchaseProductGroup.getPurchaseProducts();
        for (PurchaseProduct purchaseProduct : purchaseProducts) {
            if (promotionService.canNotReceivePromotion(purchaseProduct)) {
                final NonPromotionCheckResult nonPromotionCheckResult = promotionService.getNonPromotionCheckResult(
                        purchaseProduct);
                final StoreCommand storeCommand = requestNonPromotionCommand(nonPromotionCheckResult);
                promotionService.applyNonPromotion(storeCommand, purchaseProduct, nonPromotionCheckResult);
            }
        }
    }

    private StoreCommand requestPromotionCommand(final PromotionCheckResult promotionCheckResult) {
        return LoopTemplate.tryCatchLoop(() -> {
            promotionOutputView.printPromotionCheckResult(promotionCheckResult);
            return promotionInputView.readStoreCommand();
        });
    }

    private StoreCommand requestNonPromotionCommand(final NonPromotionCheckResult nonPromotionCheckResult) {
        return LoopTemplate.tryCatchLoop(() -> {
            promotionOutputView.printNonPromotionCheckResult(nonPromotionCheckResult);
            return promotionInputView.readStoreCommand();
        });
    }


}
