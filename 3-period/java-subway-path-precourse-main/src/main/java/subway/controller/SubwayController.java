package subway.controller;

import static subway.util.StringParser.parseToInteger;

import java.util.List;
import subway.command.FunctionCommand;
import subway.command.ProcessingState;
import subway.command.RouteCriteriaCommand;
import subway.domain.Order;
import subway.domain.line.Line;
import subway.domain.line.LineRepository;
import subway.domain.path.PathFinder;
import subway.domain.route.Route;
import subway.domain.route.RoutesRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.dto.ResultDto;
import subway.exception.ErrorMessage;
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
        initialize();
        processCommands();
    }

    private void processCommands() {
        ProcessingState status = ProcessingState.START_FROM_BEGINNING;
        while (shouldContinue(status)) {
            RouteCriteriaCommand command = makeCriteria();
            status = processCommand(command);
        }
    }

    private boolean shouldContinue(final ProcessingState status) {
        return status.shouldContinue() || !isQuit();
    }

    private ProcessingState processCommand(final RouteCriteriaCommand command) {
        if (command.isGoBack()) {
            outputView.showBlank();
            return ProcessingState.START_FROM_BEGINNING;
        }
        try {
            PathFinder pathFinder = new PathFinder();
            Order order = createOrder(pathFinder);
            processByCommand(command, order, pathFinder);
            return ProcessingState.START_FROM_BEGINNING;
        } catch (IllegalArgumentException exception) {
            outputView.showException(exception);
            return ProcessingState.CONTINUE_IN_THE_MIDDLE;
        }
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
        return exceptionHandler.retryOn(() -> StationRepository.findByName(inputView.readDeparture()));
    }

    private Station makeDepartureStation() {
        return exceptionHandler.retryOn(() -> {
            outputView.askDepartureStation();
            return StationRepository.findByName(inputView.readDeparture());
        });
    }

    private void initialize() {
        initializeStations();
        initializeLines();
        initializeRoutes();
    }

    private void initializeRoutes() {
        List<List<String>> stations = makeStationList();
        List<List<String>> paths = makeInfoList();
        for (int i = 0; i < stations.size(); i++) {
            List<String> path = paths.get(i);
            List<String> station = stations.get(i);
            makeRoutes(path, station);
        }
    }

    private List<List<String>> makeInfoList() {
        return List.of(List.of("2,3", "2,3"), List.of("3,2", "6,5", "1,1"), List.of("2,8", "10,3"));
    }

    private List<List<String>> makeStationList() {
        return List.of(List.of("교대역", "강남역", "역삼역"), List.of("교대역", "남부터미널역", "양재역", "매봉역"),
                List.of("강남역", "양재역", "양재시민의숲역"));
    }

    private void makeRoutes(final List<String> path,
                            final List<String> station) {
        for (int j = 0; j < path.size(); j++) {
            RoutesRepository.addRoute(makeRoute(path, station, j));
        }
    }

    private Route makeRoute(final List<String> path, final List<String> station, final int j) {
        String[] pathInfo = path.get(j).split(",");
        Station start = new Station(station.get(j));
        Station end = new Station(station.get(j + 1));
        return new Route(start, end, parseToInteger(pathInfo[1], ErrorMessage.INVALID_ARGUMENT),
                parseToInteger(pathInfo[0], ErrorMessage.INVALID_ARGUMENT));
    }

    private void initializeLines() {
        List<String> lineInputs = List.of("2호선, 3호선, 신분당선".split(", "));
        for (String line : lineInputs) {
            LineRepository.addLine(new Line(line));
        }
    }

    private void initializeStations() {
        List<String> stationsInput = List.of("교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역".split(", "));
        for (String station : stationsInput) {
            StationRepository.addStation(new Station(station));
        }
    }

    private RouteCriteriaCommand makeCriteria() {
        outputView.selectRouteCriteria();
        return exceptionHandler.retryOn(() -> RouteCriteriaCommand.from(inputView.readRouteCriteria()));
    }

    private boolean isQuit() {
        outputView.welcome();
        return exceptionHandler.retryOn(
                () -> FunctionCommand.from(inputView.informFunction()).isQuit());
    }
}
