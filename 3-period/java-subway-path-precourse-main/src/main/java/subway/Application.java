package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.exception.ExceptionHandler;
import subway.service.SubwayService;
import subway.view.InputView;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        SubwayService subwayService = new SubwayService();
        SubwayController controller = new SubwayController(inputView, outputView, exceptionHandler, subwayService);
        try {
            controller.process();
        } finally {
            scanner.close();
        }
    }
}
