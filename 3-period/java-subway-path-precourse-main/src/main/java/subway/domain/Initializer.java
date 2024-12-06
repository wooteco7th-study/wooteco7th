package subway.domain;

import static subway.util.StringParser.parseToInteger;

import java.util.List;
import subway.domain.line.Line;
import subway.domain.line.LineRepository;
import subway.domain.route.Route;
import subway.domain.route.RoutesRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.exception.ErrorMessage;

public class Initializer {

    public void initialize() {
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
}
