package subway;

import subway.controller.SubwayController;
import subway.view.InputView;
import subway.view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        SubwayController subwayController = new SubwayController(
                new InputView(scanner),
                new OutputView()
        );
        subwayController.run();
    }
}
