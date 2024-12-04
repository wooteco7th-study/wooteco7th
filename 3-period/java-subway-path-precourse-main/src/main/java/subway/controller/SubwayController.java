package subway.controller;

import subway.domain.DataInitializer;
import subway.domain.MainCommand;
import subway.domain.PathCommand;
import subway.domain.ShortestDistanceFinder;
import subway.domain.ShortestDistanceRepository;
import subway.domain.ShortestTimeFinder;
import subway.domain.ShortestTimeRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
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
            DataInitializer.initRepository();
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
}
