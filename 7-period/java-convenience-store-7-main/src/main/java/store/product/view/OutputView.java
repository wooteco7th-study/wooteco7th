package store.product.view;

import java.util.List;
import java.util.stream.Collectors;
import store.product.dto.ProductResponse;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INTRO = "안녕하세요. W편의점입니다." + LINE_SEPARATOR + "현재 보유하고 있는 상품입니다.";
    private static final String PRODUCT_FORMAT = "- %s %,d원 %s %s";
    public static final String PRODUCT_HAS_QUANTITY_FORMAT = "%d개";
    public static final String PRODUCT_HAS_NO_QUANTITY_FORMAT = "재고 없음";
    private static final String ASK_PRODUCT = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    public void printProductResponse(final List<ProductResponse> productResponses) {
        final String message = productResponses.stream()
                .map(productResponse -> String.format(PRODUCT_FORMAT, productResponse.name(), productResponse.price(),
                        convertToQuantityFormat(productResponse), productResponse.promotionName()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(INTRO);
        printlnMessage(LINE_SEPARATOR + message);
    }

    public void printAskProduct() {
        printlnMessage(LINE_SEPARATOR + ASK_PRODUCT);
    }

    private String convertToQuantityFormat(final ProductResponse productResponse) {
        if (productResponse.quantity() == 0) {
            return PRODUCT_HAS_NO_QUANTITY_FORMAT;
        }
        return String.format(PRODUCT_HAS_QUANTITY_FORMAT, productResponse.quantity());
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
