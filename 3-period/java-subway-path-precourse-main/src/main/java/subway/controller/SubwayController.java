package subway.controller;

import subway.command.FunctionCommand;
import subway.command.RouteCriteriaCommand;
import subway.domain.station.StationType;
import subway.support.Initializer;
import subway.domain.Order;
import subway.domain.path.PathFinder;
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
    private final Initializer initializer;

    public SubwayController(final InputView inputView, final OutputView outputView,
                            final ExceptionHandler exceptionHandler,
                            final SubwayService subwayService, final Initializer initializer) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.subwayService = subwayService;
        this.initializer = initializer;
    }

    public void process() {
        processCommands();
    }

    private void processCommands() {
        while (!isQuit()) {
            processCommand();
        }
    }

    private void processCommand() {
        exceptionHandler.retryUntilSuccess(() -> {
            RouteCriteriaCommand command = makeCriteria();
            if (command.isGoBack()) {
                outputView.showBlank();
                return;
            }
            PathFinder pathFinder = new PathFinder();
            Order order = createOrder();
            processByCommand(command, order, pathFinder);
        });
    }

    private Order createOrder() {
        StationType departureStation = makeDepartureStation();
        StationType arrivalStation = makeArrivalStation();
        return new Order(departureStation, arrivalStation);
    }

    private void processByCommand(final RouteCriteriaCommand command,
                                  final Order order, final PathFinder pathFinder) {
        ResultDto resultDto = processRoute(command, order, pathFinder);
        outputView.showTotalResult(resultDto);
    }

    private ResultDto processRoute(final RouteCriteriaCommand command, final Order order, final PathFinder pathFinder) {
        if (command.isShortestDistance()) {
            return subwayService.processShortestDistance(order, pathFinder);
        }
        return subwayService.processMinimumTime(order, pathFinder);
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
