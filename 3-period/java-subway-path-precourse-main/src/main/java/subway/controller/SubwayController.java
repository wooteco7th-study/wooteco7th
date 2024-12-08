package subway.controller;

import java.util.List;
import java.util.Objects;
import subway.domain.edge.EdgeGroup;
import subway.domain.edge.EdgeGroupGenerator;
import subway.domain.Initializer;
import subway.domain.MainCommand;
import subway.domain.Path;
import subway.domain.PathCommand;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.domain.Subway;
import subway.util.LoopTemplate;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;

    public SubwayController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Initializer.initialize();
        final EdgeGroup edgeGroup = EdgeGroupGenerator.generate();
        while (!isQuit()) {
            start(edgeGroup);
        }
    }

    private void start(final EdgeGroup edgeGroup) {
        LoopTemplate.tryCatchLoop(() -> {
            final PathCommand pathCommand = requestPathCommand();
            if (Objects.equals(pathCommand, PathCommand.BACK)) {
                return;
            }
            final Path path = requestPath();
            final Subway subway = new Subway(edgeGroup, path);
            final List<String> shortestPath = subway.getShortestPath(pathCommand);
            outputView.printResult(shortestPath, subway.calculateDistance(shortestPath),
                    subway.calculateTime(shortestPath));
        });
    }


    private Path requestPath() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskStartStation();
            final String start = inputView.readInput();
            final Station startStation = StationRepository.findByName(start);
            outputView.printAskEndStation();
            final String end = inputView.readInput();
            final Station endStation = StationRepository.findByName(end);
            return new Path(startStation, endStation);
        });
    }

    private PathCommand requestPathCommand() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printPathCommand();
            outputView.printAskCommand();
            final String input = inputView.readInput();
            return PathCommand.findByCommand(input);
        });
    }

    private MainCommand requestMainCommand() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printMainCommand();
            outputView.printAskCommand();
            final String input = inputView.readInput();
            return MainCommand.findByCommand(input);
        });
    }

    private boolean isQuit() {
        final MainCommand mainCommand = requestMainCommand();
        return Objects.equals(mainCommand, MainCommand.QUIT);
    }
}
