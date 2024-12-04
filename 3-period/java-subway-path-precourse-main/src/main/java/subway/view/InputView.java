package subway.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String getUser() {
        return scanner.nextLine();
    }

    public String getMain() {
        OutputView.printMessage(PrintMessage.MAIN);
        OutputView.printMessage(PrintMessage.CHOICE);
        return getUser();
    }

    public String getWayStandard() {
        OutputView.printMessage(PrintMessage.FIND_WAY);
        OutputView.printMessage(PrintMessage.CHOICE);
        return getUser();
    }

    public String getStartSubway() {
        OutputView.printMessage(PrintMessage.START_SUBWAY);
        return getUser();
    }

    public String getEndSubway() {
        OutputView.printMessage(PrintMessage.END_SUBWAY);
        return getUser();
    }
}
