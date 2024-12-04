package subway.controller;

import static subway.util.StringParser.parseToInteger;

import java.util.ArrayList;
import java.util.List;
import subway.command.FunctionCommand;
import subway.command.RouteCriteriaCommand;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Order;
import subway.domain.Repositories;
import subway.domain.Route;
import subway.domain.RouteRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.exception.ErrorMessage;
import subway.exception.ExceptionHandler;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;

    public SubwayController(final InputView inputView, final OutputView outputView,
                            final ExceptionHandler exceptionHandler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
    }

    public void process() {
        Repositories repositories = initialize();
        processCommand(repositories);

    }

    private void processCommand(final Repositories repositories) {
        while (true) {
            if (isQuit()) {
                break;
            }
            RouteCriteriaCommand command = makeCriteria();
            if (command.isGoBack()) {
                outputView.showBlank();
                continue;
            }
            processRoute(repositories);
        }
    }

    private void processRoute(final Repositories repositories) {
        // 출발역, 도착역 입력
        StationRepository stationRepository = repositories.getStationRepository();
        Station departureStation = makeDepartureStation(stationRepository);
        Order order = makeArrivalStation(departureStation, stationRepository, repositories.getRouteRepository());
    }

    private Order makeArrivalStation(final Station departureStation, final StationRepository stationRepository,
                                     final RouteRepository routeRepository) {
        outputView.askArrivalStation();
        return exceptionHandler.retryOn(() -> {
            Station arrivalStation = stationRepository.findByName(inputView.readDeparture());
            return new Order(departureStation, arrivalStation, routeRepository);
        });
    }

    private Station makeDepartureStation(final StationRepository stationRepository) {
        return exceptionHandler.retryOn(() -> {
            outputView.askDepartureStation();
            return stationRepository.findByName(inputView.readDeparture());
        });
    }

    private Repositories initialize() {
        StationRepository stationRepository = initializeStations();
        LineRepository lineRepository = initilaizeLines();
        // 교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역
        RouteRepository routeRepository = initializeRoutes(stationRepository, lineRepository);
        return new Repositories(lineRepository, routeRepository, stationRepository);
    }

    private RouteRepository initializeRoutes(final StationRepository stationRepository,
                                             final LineRepository lineRepository) {
        // - `2호선`
        //    - `교대역` - ( 2km / 3분 ) - `강남역` - ( 2km / 3분 ) - `역삼역`
        //- `3호선`
        //    - `교대역` - ( 3km / 2분 ) - `남부터미널역` - ( 6km / 5분 ) - `양재역` - ( 1km / 1분 ) - `매봉역`
        //- `신분당선`
        //    - `강남역` - ( 2km / 8분 ) - `양재역` - ( 10km / 3분 ) - `양재시민의숲역`
        List<Route> routes = new ArrayList<>();
        List<Line> lines = makeLines(lineRepository);

        List<List<String>> stations = List.of(List.of("교대역", "강남역", "역삼역"), List.of("교대역", "남부터미널역", "양재역", "매봉역"),
                List.of("강남역", "양재역", "양재시민의숲역"));
        List<List<String>> paths = List.of(List.of("2,3", "2,3"), List.of("3,2", "6,5", "1,1"), List.of("2,8", "10,3"));
        for (int i = 0; i < stations.size(); i++) {
            Line line = lines.get(i);
            List<String> path = paths.get(i);
            List<String> station = stations.get(i);
            for (int j = 0; j < path.size(); j++) {
                String[] pathInfo = path.get(j).split(",");
                Station start = new Station(station.get(j));
                Station end = new Station(station.get(j + 1));
                Route route = new Route(line, start, end, parseToInteger(pathInfo[0], ErrorMessage.INVALID_ARGUMENT),
                        parseToInteger(pathInfo[1], ErrorMessage.INVALID_ARGUMENT));
                routes.add(route);
            }
        }
        RouteRepository routeRepository = new RouteRepository(routes, stationRepository.stations());
        return routeRepository;
    }

    private List<Line> makeLines(final LineRepository lineRepository) {
        List<String> lineInputs = List.of("2호선", "3호선", "신분당선");
        return lineInputs.stream()
                .map(name -> lineRepository.findLineByName(name))
                .toList();
    }

    private LineRepository initilaizeLines() {
        LineRepository lineRepository = new LineRepository();
        List<String> lineInputs = List.of("2호선, 3호선, 신분당선".split(", "));
        for (String line : lineInputs) {
            lineRepository.addLine(new Line(line));
        }
        return lineRepository;
    }

    private StationRepository initializeStations() {
        StationRepository stationRepository = new StationRepository();
        List<String> stationsInput = List.of("교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역".split(", "));
        for (String station : stationsInput) {
            stationRepository.addStation(new Station(station));
        }
        return stationRepository;
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
