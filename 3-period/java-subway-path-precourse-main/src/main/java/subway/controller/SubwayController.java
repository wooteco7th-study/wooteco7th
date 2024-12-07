package subway.controller;

import subway.domain.DataInitializer;
import subway.domain.Station;
import subway.domain.gamecommand.MainCommand;
import subway.domain.gamecommand.PathCommand;
import subway.domain.pathfinder.ShortestDistanceFinder;
import subway.domain.pathfinder.ShortestTimeFinder;
import subway.domain.repository.ShortestDistanceRepository;
import subway.domain.repository.ShortestTimeRepository;
import subway.domain.repository.StationRepository;
import subway.view.InputView;
import subway.view.OutputView;

import java.util.List;

import static subway.exception.ExceptionHandler.retryOnInvalidInput;
import static subway.exception.ExceptionMessage.DUPLICATED_STATION;

public class SubwayController {
    private final InputView inputView;
    private final OutputView outputView;

    public SubwayController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        DataInitializer.initRepository();
        process();
    }

    private void process() {
        while (keepProcess()) {
            retryOnInvalidInput(this::getPathCommand);
        }
    }

    private boolean keepProcess() {
        MainCommand mainCommand = retryOnInvalidInput(() -> MainCommand.from(inputView.readFirstGameCommand()));
        return mainCommand == MainCommand.경로조회;
    }

    private void getPathCommand() {
        PathCommand pathCommand = PathCommand.from(inputView.readPathCommand());
        if (pathCommand == PathCommand.돌아가기) {
            return;
        }
        findPath(pathCommand);
    }

    private void findPath(final PathCommand pathCommand) {
        Station startStation = StationRepository.findLineByName(inputView.readStartStation());
        Station endStation = StationRepository.findLineByName(inputView.readEndStation());
        validateDuplicatedStation(startStation, endStation);
        if (pathCommand == PathCommand.최소시간) {
            processShortestTime(startStation, endStation);
            return;
        }
        processShortestDistance(startStation, endStation);
    }

    private void validateDuplicatedStation(final Station startStation, final Station endStation) {
        if (startStation.getName().equals(endStation.getName())) {
            throw new IllegalArgumentException(DUPLICATED_STATION.getMessage());
        }
    }

    private void processShortestTime(final Station startStation, final Station endStation) {
        ShortestTimeFinder shortestTimeFinder = new ShortestTimeFinder();
        List<Station> stations = shortestTimeFinder.findPath(startStation, endStation).getVertexList();
        int totalDistance = ShortestTimeRepository.getTotalDistance(stations);
        int totalTime = ShortestTimeRepository.getTotalTime(stations);
        outputView.printResult(stations, totalDistance, totalTime);
    }

    private void processShortestDistance(final Station startStation, final Station endStation) {
        ShortestDistanceFinder shortestDistanceFinder = new ShortestDistanceFinder();
        List<Station> stations = shortestDistanceFinder.findPath(startStation, endStation).getVertexList();
        int totalDistance = ShortestDistanceRepository.getTotalDistance(stations);
        int totalTime = ShortestDistanceRepository.getTotalTime(stations);
        outputView.printResult(stations, totalDistance, totalTime);
    }
}
