package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.domain.path.DistancePathFinder;
import subway.domain.path.TimePathFinder;
import subway.domain.route.SectionType;
import subway.domain.route.Sections;
import subway.exception.ExceptionHandler;
import subway.service.SubwayService;
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
        Sections sections = new Sections(SectionType.findAll());
        SubwayService subwayService = new SubwayService(new TimePathFinder(sections), new DistancePathFinder(sections));
        return new SubwayController(inputView, outputView, exceptionHandler, subwayService);
    }
}
