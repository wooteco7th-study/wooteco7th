package store.product.view;

import java.util.List;
import java.util.stream.Collectors;
import store.product.dto.ProductResponse;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INTRO = "안녕하세요. W편의점입니다." + LINE_SEPARATOR + "현재 보유하고 있는 상품입니다.";
    private static final String PRODUCT_FORMAT = "- %s %,d원 %d개 %s";

    public void printProductResponse(final List<ProductResponse> productResponses) {
        final String message = productResponses.stream()
                .map(productResponse -> String.format(PRODUCT_FORMAT, productResponse.name(), productResponse.price(),
                        productResponse.quantity(), productResponse.promotionName()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(INTRO);
        printlnMessage(LINE_SEPARATOR + message);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
