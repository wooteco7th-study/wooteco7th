package store.payment.controller;

import java.util.Objects;
import store.constant.StoreCommand;
import store.payment.domain.Payment;
import store.payment.view.PaymentInputView;
import store.payment.view.PaymentOutputView;
import store.product.domain.ProductRepository;
import store.product.domain.PurchaseProductGroup;
import store.promotion.domain.PromotionRepository;

public class PaymentController {

    private final PaymentInputView paymentInputView;
    private final PaymentOutputView paymentOutputView;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;
    private final PurchaseProductGroup purchaseProductGroup;

    public PaymentController(final PaymentInputView paymentInputView, final PaymentOutputView paymentOutputView,
                             final ProductRepository productRepository,
                             final PromotionRepository promotionRepository,
                             final PurchaseProductGroup purchaseProductGroup) {
        this.paymentInputView = paymentInputView;
        this.paymentOutputView = paymentOutputView;
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
        this.purchaseProductGroup = purchaseProductGroup;
    }

    public void run() {
        final Payment payment = new Payment(promotionRepository, purchaseProductGroup, productRepository);
        membership(payment);
        responsePurchaseProduct(payment);
        responseBenefitProduct(payment);
        responsePayment(payment);
        payment.purchase();
    }

    public boolean isRetry() {
        paymentOutputView.printAskRetryPurchase();
        return Objects.equals(paymentInputView.readStoreCommand(), StoreCommand.YES);
    }

    private void responsePayment(final Payment payment) {
        paymentOutputView.printPaymentReceipt(payment.getPaymentReceipt());
    }

    private void responseBenefitProduct(final Payment payment) {
        paymentOutputView.printBenefitProductReceipts(payment.getBenefitProductReceipts());
    }

    private void responsePurchaseProduct(final Payment payment) {
        paymentOutputView.printPurchaseProductReceipt(payment.getPurchaseProductReceipts());
    }

    private void membership(final Payment payment) {
        paymentOutputView.printAskMemberShip();
        final StoreCommand storeCommand = paymentInputView.readStoreCommand();
        payment.applyMemberShip(storeCommand);

    }


}
