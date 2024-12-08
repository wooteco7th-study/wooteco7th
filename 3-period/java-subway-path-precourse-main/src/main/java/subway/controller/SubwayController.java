package subway.controller;

import subway.command.FunctionCommand;
import subway.command.RouteCriteriaCommand;
import subway.domain.Order;
import subway.domain.path.DistancePathFinder;
import subway.domain.path.TimePathFinder;
import subway.domain.route.SectionType;
import subway.domain.route.Sections;
import subway.domain.station.StationType;
import subway.dto.ResultDto;
import subway.exception.ExceptionHandler;
import subway.service.SubwayService;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final SubwayService subwayService;

    public SubwayController(final InputView inputView, final OutputView outputView,
                            final ExceptionHandler exceptionHandler,
                            final SubwayService subwayService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.subwayService = subwayService;
    }

    public void process() {
        processCommands();
    }

    private void processCommands() {
        Sections sections = new Sections(SectionType.findAll());
        TimePathFinder timePathFinder = new TimePathFinder(sections);
        DistancePathFinder distancePathFinder = new DistancePathFinder(sections);
        while (!isQuit()) {
            processCommand(timePathFinder, distancePathFinder);
        }
    }

    private void processCommand(final TimePathFinder timePathFinder, final DistancePathFinder distancePathFinder) {
        exceptionHandler.retryUntilSuccess(() -> {
            RouteCriteriaCommand command = makeCriteria();
            if (command.isGoBack()) {
                outputView.showBlank();
                return;
            }
            Order order = createOrder();
            processByCommand(command, order, timePathFinder, distancePathFinder);
        });
    }

    private Order createOrder() {
        StationType departureStation = makeDepartureStation();
        StationType arrivalStation = makeArrivalStation();
        return new Order(departureStation, arrivalStation);
    }

    private void processByCommand(final RouteCriteriaCommand command,
                                  final Order order, final TimePathFinder timePathFinder,
                                  final DistancePathFinder distancePathFinder) {
        ResultDto resultDto = processRoute(command, order, timePathFinder, distancePathFinder);
        outputView.showTotalResult(resultDto);
    }

    private ResultDto processRoute(final RouteCriteriaCommand command, final Order order,
                                   final TimePathFinder timePathFinder,
                                   final DistancePathFinder distancePathFinder) {
        if (command.isShortestDistance()) {
            return subwayService.process(order, distancePathFinder);
        }
        return subwayService.process(order, timePathFinder);
    }

    private StationType makeArrivalStation() {
        outputView.askArrivalStation();
        return exceptionHandler.retryUntilSuccess(() -> StationType.of(inputView.readDeparture()));
    }

    private StationType makeDepartureStation() {
        return exceptionHandler.retryUntilSuccess(() -> {
            outputView.askDepartureStation();
            return StationType.of(inputView.readDeparture());
        });
    }

    private RouteCriteriaCommand makeCriteria() {
        outputView.selectRouteCriteria();
        return exceptionHandler.retryUntilSuccess(() -> RouteCriteriaCommand.from(inputView.readRouteCriteria()));
    }

    private boolean isQuit() {
        outputView.welcome();
        return exceptionHandler.retryUntilSuccess(
                () -> FunctionCommand.from(inputView.informFunction()).isQuit());
    }
}
