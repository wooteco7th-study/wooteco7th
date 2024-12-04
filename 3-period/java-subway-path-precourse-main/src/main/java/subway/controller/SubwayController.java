package subway.controller;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.MainCommand;
import subway.domain.ShortestDistanceFinder;
import subway.domain.ShortestDistanceRepository;
import subway.domain.ShortestTimeFinder;
import subway.domain.ShortestTimeRepository;
import subway.domain.Station;
import subway.domain.StationDistanceConnection;
import subway.domain.StationRepository;
import subway.domain.StationTimeConnection;
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
        initRepository();
        String pathOrQuit = inputView.readFirstGameCommand();
        MainCommand mainCommand = MainCommand.from(pathOrQuit);
        if (mainCommand == MainCommand.종료) {
            return;
        }
        ShortestDistanceFinder shortestDistanceFinder = new ShortestDistanceFinder();
        ShortestTimeFinder shortestTimeFinder = new ShortestTimeFinder();
        List<Station> shortestDistance = shortestDistanceFinder.findPath(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("양재시민의숲역")).getVertexList();
        List<Station> shortestTime = shortestTimeFinder.findPath(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("양재역")).getVertexList();
        System.out.println("shortestTime = " + shortestTime);
    }

    private void initRepository() {
        saveLines();
        saveStations();
        saveTimeConnections();
        saveDistanceConnections();
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

    private void saveTimeConnections() {
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("강남역"), 3));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("역삼역"), 3));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("남부터미널역"), 2));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("남부터미널역"), StationRepository.findLineByName("양재역"), 5));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("매봉역"), 1));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("양재역"), 8));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("양재시민의숲역"), 3));
    }

    private void saveDistanceConnections() {
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("강남역"), 2));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("역삼역"), 2));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("남부터미널역"), 3));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("남부터미널역"), StationRepository.findLineByName("양재역"), 6));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("매봉역"), 1));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("양재역"), 2));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("양재시민의숲역"), 10));
    }
}
