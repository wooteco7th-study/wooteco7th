package subway.controller;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.MainCommand;
import subway.domain.PathCommand;
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

import static subway.exception.ExceptionMessage.DUPLICATED_STATION;

public class SubwayController {
    private final InputView inputView;
    private final OutputView outputView;

    public SubwayController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            initRepository();
            process();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private void process() {
        MainCommand mainCommand = MainCommand.from(inputView.readFirstGameCommand());
        while (mainCommand == MainCommand.경로조회) {
            PathCommand pathCommand = PathCommand.from(inputView.readPathCommand());
            if (pathCommand == PathCommand.돌아가기) {
                mainCommand = MainCommand.from(inputView.readFirstGameCommand());
                continue;
            }
            findPath(pathCommand);
            mainCommand = MainCommand.from(inputView.readFirstGameCommand());
        }
    }

    private void initRepository() {
        saveLines();
        saveStations();
        saveTimeConnections();
        saveDistanceConnections();
    }

    private void validateDuplicatedStation(final Station startStation, final Station endStation) {
        if (startStation.getName().equals(endStation.getName())) {
            throw new IllegalArgumentException(DUPLICATED_STATION.getMessage());
        }
    }

    private void findPath(final PathCommand pathCommand) {
        Station startStation = StationRepository.findLineByName(inputView.readStartStation());
        Station endStation = StationRepository.findLineByName(inputView.readEndStation());
        validateDuplicatedStation(startStation, endStation);
        processShortestDistance(pathCommand, startStation, endStation);
        processShortestTime(pathCommand, startStation, endStation);
    }

    private void processShortestTime(final PathCommand pathCommand, final Station startStation, final Station endStation) {
        if (pathCommand == PathCommand.최소시간) {
            ShortestTimeFinder shortestTimeFinder = new ShortestTimeFinder();
            List<Station> stations = shortestTimeFinder.findPath(startStation, endStation).getVertexList();
            int totalDistance = ShortestTimeRepository.getTotalDistance(stations);
            int totalTime = ShortestTimeRepository.getTotalTime(stations);
            outputView.printResult(stations, totalDistance, totalTime);
        }
    }

    private void processShortestDistance(final PathCommand pathCommand, final Station startStation, final Station endStation) {
        if (pathCommand == PathCommand.최단거리) {
            ShortestDistanceFinder shortestDistanceFinder = new ShortestDistanceFinder();
            List<Station> stations = shortestDistanceFinder.findPath(startStation, endStation).getVertexList();
            int totalDistance = ShortestDistanceRepository.getTotalDistance(stations);
            int totalTime = ShortestDistanceRepository.getTotalTime(stations);
            outputView.printResult(stations, totalDistance, totalTime);
        }
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
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("강남역"), 2, 3));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("역삼역"), 2, 3));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("남부터미널역"), 3, 2));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("남부터미널역"), StationRepository.findLineByName("양재역"), 6, 5));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("매봉역"), 1, 1));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("양재역"), 2, 8));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("양재시민의숲역"), 10, 3));
    }

    private void saveDistanceConnections() {
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("강남역"), 2, 3));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("역삼역"), 2, 3));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("남부터미널역"), 3, 2));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("남부터미널역"), StationRepository.findLineByName("양재역"), 6, 5));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("매봉역"), 1, 1));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("양재역"), 2, 8));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("양재시민의숲역"), 10, 3));
    }
}
