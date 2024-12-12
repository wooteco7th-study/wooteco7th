package store.view;

import java.util.List;
import store.domain.stock.StockMessage;
import store.dto.InventoryDto;
import store.dto.ReceiptProductDto;
import store.dto.ReceiptResultDto;
import store.util.KoreanFormatter;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String TITLE_WELCOME = """
            안녕하세요. W편의점입니다.
            현재 보유하고 있는 상품입니다.
            """;
    private static final String FORMAT_INVENTORY = "- %s %,d원 %s %s";
    private static final String REQUEST_ORDER = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String REQUEST_MEMBERSHIP = "멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String INFORM_RECEIPT_TITLE = """
            ==============W 편의점================
            상품명\t\t\t\t수량\t\t\t금액""";
    private static final String FORMAT_RECEIPT_INVENTORY = "%s\t\t\t%d\t\t\t%,d";
    private static final String INFORM_RECEIPT_BONUS_TITLE = "=============증\t\t정===============";
    private static final String FORMAT_RECEIPT_BONUS = "%s\t\t\t%d";
    private static final String INFORM_RESULT_TITLE = "====================================";
    private static final String INFORM_RESULT_FORMAT = """
            총구매액\t\t\t\t%d\t\t\t%,d
            행사할인\t\t\t\t\t\t\t-%,d
            멤버십할인\t\t\t\t\t\t\t-%,d
            내실돈\t\t\t\t\t\t\t %,d
            """;
    private static final String REQUEST_RETRY = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";
    private static final String REQUEST_REGULAR_PRICE = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    private static final String REQUEST_BONUS = "현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final int FORMAT_SIZE = 12;

    public void showTitleWelcome() {
        showln(TITLE_WELCOME);
    }

    public void showInventory(List<InventoryDto> dtos) {
        dtos.stream()
                .map(dto -> format(FORMAT_INVENTORY, dto.name(), dto.price(), StockMessage.makeMessage(dto.quantity()),
                        dto.promotionName()))
                .forEach(this::showln);
    }

    public void showRequestOrder() {
        showln(LINE + REQUEST_ORDER);
    }

    public void showRequestMembership() {
        showln(LINE + REQUEST_MEMBERSHIP);
    }

    public void showReceipt(ReceiptProductDto productDto, ReceiptResultDto resultDto) {
        showTotalProducts(productDto);
        showGifts(productDto);
        showResults(resultDto);
    }

    private void showResults(final ReceiptResultDto resultDto) {
        showln(INFORM_RESULT_TITLE);
        showln(format(INFORM_RESULT_FORMAT, resultDto.totalQuantity(), resultDto.totalPrice(),
                resultDto.promotionDiscount(), resultDto.membershipDiscount(), resultDto.payPrice()));
    }

    private void showTotalProducts(final ReceiptProductDto productDto) {
        showln(LINE + INFORM_RECEIPT_TITLE);
        productDto.total().stream()
                .map(dto -> format(FORMAT_RECEIPT_INVENTORY, formatKorean(dto.name()), dto.quantity(), dto.price()))
                .forEach(this::showln);
    }

    private void showGifts(final ReceiptProductDto productDto) {
        showln(INFORM_RECEIPT_BONUS_TITLE);
        productDto.gifts().stream()
                .map(dto -> format(FORMAT_RECEIPT_BONUS, formatKorean(dto.name()), dto.quantity()))
                .forEach(this::showln);
    }

    public void showRequestRetry() {
        showln(REQUEST_RETRY);
    }

    public void showRequestRegularPrice(final String productName, final int quantity) {
        showln(LINE + format(REQUEST_REGULAR_PRICE, productName, quantity));
    }

    public void showRequestGift(String productName) {
        showln(LINE + format(REQUEST_BONUS, productName));
    }

    public void showException(Exception exception) {
        showln(exception.getMessage());
    }

    public void showBlank() {
        showln("");
    }

    private String format(String format, Object... args) {
        return String.format(format, args);
    }

    private String formatKorean(String korean) {
        return KoreanFormatter.formatKorean(korean, FORMAT_SIZE);
    }

    private void showln(String message) {
        System.out.println(message);
    }
}
