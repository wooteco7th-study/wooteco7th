package vendingmachine.view;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.price.coin.Coin;
import vendingmachine.dto.CoinDto;

public class OutputView {

    private static final String LINE = System.lineSeparator();

    private static final String REQUEST_HOLDING_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String INFORM_HOLDING_AMOUNT = "자판기가 보유한 동전";
    private static final String FORMAT_COIN = "%d원 - %d개";
    private static final String REQUEST_PRODUCT = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String REQUEST_INPUT_AMOUNT = "투입 금액을 입력해 주세요.";
    private static final String REQUEST_ORDER_PRODUCT = "구매할 상품명을 입력해 주세요.";

    private static final String REMAINING = "잔돈";

    private static final String FORMAT_INPUT_PRICE = "투입 금액: %d원";

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

    public void requestInputAmount() {
        showln(LINE + REQUEST_INPUT_AMOUNT);
    }

    public void requestOrderProduct() {
        showln(REQUEST_ORDER_PRODUCT);
    }

    private void showln(final String message) {
        System.out.println(message);
    }

    private String format(String format, Object... args) {
        return String.format(format, args);
    }

    public void showInputPrice(final long inputPrice) {
        showln(LINE + format(FORMAT_INPUT_PRICE, inputPrice));
    }

    public void showRemainingPrice(final Map<Coin, Integer> leastCoins) {
        showln(REMAINING);
        leastCoins.forEach((key, value) -> showln(format(FORMAT_COIN, key.getPrice().getAmount(), value)));
    }
}
