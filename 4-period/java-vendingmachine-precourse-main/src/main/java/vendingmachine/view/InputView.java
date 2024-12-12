package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private String getUser() {
        return Console.readLine();
    }

    public String getMachineCoin() {
        OutputView.printMessage(PrintMessage.INPUT_COIN);
        return getUser();
    }

    public String getProductInfo() {
        OutputView.printMessage(PrintMessage.INPUT_PRODUCT_INFO);
        return getUser();
    }

    public String getMoney() {
        OutputView.printMessage(PrintMessage.INPUT_MONEY);
        return getUser();
    }

    public String getProductName() {
        OutputView.printMessage(PrintMessage.INPUT_PRODUCT_NAME);
        return getUser();
    }
}
