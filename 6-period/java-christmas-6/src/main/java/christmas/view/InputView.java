package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final OutputView outputView;

    public InputView(OutputView outputView) {
        this.outputView = outputView;
    }

    public String getUser() {
        return Console.readLine();
    }

    public String getVisitDate() {
        outputView.printMessage(PrintMessage.INPUT_VISIT_DATE_MESSAGE);
        return getUser();
    }

    public String getOrders() {
        outputView.printMessage(PrintMessage.INPUT_ORDERS_MESSAGE);
        return getUser();
    }
}
