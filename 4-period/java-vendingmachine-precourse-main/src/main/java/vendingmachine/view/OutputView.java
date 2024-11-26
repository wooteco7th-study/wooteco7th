package vendingmachine.view;

import vendingmachine.Coin;
import vendingmachine.dto.response.MachineCoinsResponse;

import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String MACHINE_COINS_MSG = """
            
            자판기가 보유한 동전
            500원 - %d개
            100원 - %d개
            50원 - %d개
            10원 - %d개
            """;
    private static final String LEFT_AMOUNT_MSG = NEW_LINE + "투입 금액: %d원";
    private static final String CHANGE_MSG = NEW_LINE + "잔돈";
    private static final String CHANGE_FORMAT = "%d원 - %d개" + NEW_LINE;


    public void printError(String error) {
        System.out.println(error);
    }

    public void printMachineCoins(final MachineCoinsResponse response) {
        System.out.printf(MACHINE_COINS_MSG,
                response.quantityOf500(),
                response.quantityOf100(),
                response.quantityOf50(),
                response.quantityOf10());
    }

    public void printOrderAmount(int orderAmount) {
        System.out.printf(LEFT_AMOUNT_MSG, orderAmount);
    }

    public void printChange(final Map<Coin, Integer> coins) {
        System.out.println(CHANGE_MSG);
        coins.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .forEach(entry ->
                        System.out.printf(CHANGE_FORMAT, entry.getKey().getAmount(), entry.getValue()));
    }
}
