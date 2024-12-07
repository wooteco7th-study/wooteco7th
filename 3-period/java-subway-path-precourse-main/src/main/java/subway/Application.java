package subway;

import subway.controller.SubwayController;
import subway.view.InputView;
import subway.view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        SubwayController subwayController = getSubwayController(scanner);
        subwayController.run();
    }

    private static SubwayController getSubwayController(final Scanner scanner) {
        return new SubwayController(
                new InputView(scanner),
                new OutputView()
        );
    }
}
