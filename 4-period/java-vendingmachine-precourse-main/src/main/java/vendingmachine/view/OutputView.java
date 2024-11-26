package vendingmachine.view;

import java.util.List;
import vendingmachine.dto.CoinDto;

public class OutputView {

    private static final String LINE = System.lineSeparator();

    private static final String REQUEST_HOLDING_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String INFORM_HOLDING_AMOUNT = "자판기가 보유한 동전";
    private static final String FORMAT_COIN = "%d원 - %d개";
    private static final String REQUEST_PRODUCT = "상품명과 가격, 수량을 입력해 주세요.";

    public void showExceptionMessage(final String message) {
        showln(message);
    }

    public void requestHoldingAmount() {
        showln(REQUEST_HOLDING_AMOUNT);
    }

    public void informHoldingAmount(final List<CoinDto> randomCoins) {
        showln(LINE + INFORM_HOLDING_AMOUNT);
        randomCoins.stream()
                .sorted((d1, d2) -> Long.compare(d2.coinType(), d1.coinType()))
                .map(dto -> format(FORMAT_COIN, dto.coinType(), dto.quantity()))
                .forEach(this::showln);
    }

    public void requestHoldingProduct() {
        showln(LINE + REQUEST_PRODUCT);
    }

    private void showln(final String message) {
        System.out.println(message);
    }

    private String format(String format, Object... args) {
        return String.format(format, args);
    }
}
