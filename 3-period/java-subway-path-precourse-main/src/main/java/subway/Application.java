package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.exception.ExceptionHandler;
import subway.service.SubwayService;
import subway.support.Initializer;
import subway.view.InputView;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        try (scanner) {
            SubwayController controller = makeController(scanner);
            controller.process();
        }
    }

    private static SubwayController makeController(final Scanner scanner) {
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        SubwayService subwayService = new SubwayService();
        Initializer initializer = new Initializer();
        return new SubwayController(inputView, outputView, exceptionHandler, subwayService, initializer);
    }
}
