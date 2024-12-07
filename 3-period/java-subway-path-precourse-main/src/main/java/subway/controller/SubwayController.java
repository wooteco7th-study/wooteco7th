package subway.controller;

import subway.command.FunctionCommand;
import subway.command.RouteCriteriaCommand;
import subway.domain.Initializer;
import subway.domain.Order;
import subway.domain.path.PathFinder;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
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
        Initializer.initialize();
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
            Order order = createOrder(pathFinder);
            processByCommand(command, order, pathFinder);
        });
    }

    private Order createOrder(final PathFinder pathFinder) {
        Station departureStation = makeDepartureStation();
        Station arrivalStation = makeArrivalStation();
        pathFinder.validatePathConnected(departureStation.getName(), arrivalStation.getName());
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

    private Station makeArrivalStation() {
        outputView.askArrivalStation();
        return exceptionHandler.retryUntilSuccess(() -> StationRepository.findByName(inputView.readDeparture()));
    }

    private Station makeDepartureStation() {
        return exceptionHandler.retryUntilSuccess(() -> {
            outputView.askDepartureStation();
            return StationRepository.findByName(inputView.readDeparture());
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
