package vendingmachine.view.console;

import java.util.Map;
import java.util.stream.Collectors;
import vendingmachine.domain.Coin;
import vendingmachine.view.OutputView;

public class ConsoleOutputView implements OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_VENDING_MACHINE_MONEY = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String VENDING_MACHINE_COINS_HEADER = "자판기가 보유한 동전";
    private static final String COIN_FORMAT = "%d원 - %d개";

    @Override
    public void printAskVendingMachineMoney() {
        printlnMessage(ASK_VENDING_MACHINE_MONEY);
    }

    @Override
    public void printVendingMachineCoins(final Map<Coin, Integer> coins) {
        final String message = coins.entrySet().stream()
                .map(entry -> String.format(COIN_FORMAT, entry.getKey().getAmount(), entry.getValue()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(LINE_SEPARATOR + VENDING_MACHINE_COINS_HEADER + LINE_SEPARATOR + message);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
