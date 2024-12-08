package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.view.console.ConsoleInputView;
import subway.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final ConsoleInputView consoleInputView = new ConsoleInputView(scanner);
        final ConsoleOutputView consoleOutputView = new ConsoleOutputView();
        final SubwayController subwayController = new SubwayController(consoleInputView, consoleOutputView);
        subwayController.run();
    }
}
