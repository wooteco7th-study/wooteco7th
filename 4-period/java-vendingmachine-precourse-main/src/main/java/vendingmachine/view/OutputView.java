package vendingmachine.view;

import vendingmachine.dto.response.MachineCoinsResponse;

public class OutputView {

    private static final String MACHINE_COINS_MSG = """
            
            자판기가 보유한 동전
            500원 - %d개
            100원 - %d개
            50원 - %d개
            10원 - %d개
            """;

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
}
