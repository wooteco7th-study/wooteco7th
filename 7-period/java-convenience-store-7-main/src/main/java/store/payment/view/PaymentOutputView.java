package store.payment.view;

import java.util.List;
import java.util.stream.Collectors;
import store.payment.dto.BenefitProductReceipt;
import store.payment.dto.PaymentReceipt;
import store.payment.dto.PurchaseProductReceipt;

public class PaymentOutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_MEMBER_SHIP_DISCOUNT = "멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String PURCHASE_PRODUCT_LINE = "==============W 편의점================";
    private static final String PURCHASE_PRODUCT_COLUMN = "상품명\t\t\t\t수량\t\t  금액";
    private static final String NAME_AND_QUANTITY_AND_PRICE = "%-16s\t%,-9d %,-6d";
    private static final String BENEFIT_PRODUCT_LINE = "=============증\t\t정===============";
    private static final String NAME_AND_QUANTITY = "%-15s\t%,-9d";
    private static final String NAME_AND_PRICE = "%-17s\t\t\t  %,-6d";
    private static final String NAME_AND_DISCOUNT = "%-17s\t\t\t -%,-6d";
    private static final String PAYMENT_LINE = "====================================";
    private static final String TOTAL_PRICE = "총구매액";
    private static final String PROMOTION_DISCOUNT = "행사할인";
    private static final String MEMBER_SHIP_DISCOUNT = "멤버십할인";
    private static final String PAYMENT_PRICE = "내실돈";
    private static final String ASK_RETRY_PURCHASE = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";


    public void printAskMemberShip() {
        printlnMessage(LINE_SEPARATOR + ASK_MEMBER_SHIP_DISCOUNT);
    }

    public void printPurchaseProductReceipt(final List<PurchaseProductReceipt> purchaseProductReceipts) {
        printlnMessage(LINE_SEPARATOR + PURCHASE_PRODUCT_LINE + LINE_SEPARATOR + PURCHASE_PRODUCT_COLUMN);
        final String message = purchaseProductReceipts.stream()
                .map(purchaseProductReceipt -> String.format(NAME_AND_QUANTITY_AND_PRICE, purchaseProductReceipt.name(),
                        purchaseProductReceipt.totalQuantity(), purchaseProductReceipt.totalPrice()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(message);
    }

    public void printBenefitProductReceipts(final List<BenefitProductReceipt> benefitProductReceipts) {
        printlnMessage(BENEFIT_PRODUCT_LINE);
        final String message = benefitProductReceipts.stream()
                .map(receipt -> String.format(NAME_AND_QUANTITY, receipt.name(), receipt.quantity()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        if (!message.isBlank()) {
            printlnMessage(message);
        }
    }

    public void printPaymentReceipt(final PaymentReceipt paymentReceipt) {
        printlnMessage(PAYMENT_LINE);
        printlnMessage(String.format(NAME_AND_QUANTITY_AND_PRICE, TOTAL_PRICE, paymentReceipt.totalQuantity(),paymentReceipt.totalPrice()));
        printlnMessage(String.format(NAME_AND_DISCOUNT, PROMOTION_DISCOUNT, paymentReceipt.promotionDiscount()));
        printlnMessage(String.format(NAME_AND_DISCOUNT, MEMBER_SHIP_DISCOUNT, paymentReceipt.membershipDiscount()));
        printlnMessage(String.format(NAME_AND_PRICE, PAYMENT_PRICE, paymentReceipt.paymentPrice()));
    }

    public void printAskRetryPurchase() {
        printlnMessage(LINE_SEPARATOR + ASK_RETRY_PURCHASE);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }

}
