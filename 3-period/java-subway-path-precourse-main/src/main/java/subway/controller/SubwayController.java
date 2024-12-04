package subway.controller;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;
import subway.view.OutputView;

import java.util.List;

public class SubwayController {
    private final InputView inputView;
    private final OutputView outputView;

    public SubwayController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        saveLines();
        saveStations();
    }

    private void saveLines() {
        List<String> lines = List.of("2호선", "3호선", "신분당선");
        for (String lineName : lines) {
            Line line = new Line(lineName);
            LineRepository.addLine(line);
        }
    }

    private void saveStations() {
        List<String> stations = List.of("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
        for (String stationName : stations) {
            Station station = new Station(stationName);
            StationRepository.addStation(station);
        }
    }
}
