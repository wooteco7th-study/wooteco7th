package subway.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import subway.domain.EdgeGroup;
import subway.domain.EdgeGroupGenerator;
import subway.domain.Initializer;
import subway.domain.MainCommand;
import subway.domain.Path;
import subway.domain.PathCommand;
import subway.domain.Station;
import subway.domain.StationRepository;
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
        while (true) {
            final PathCommand pathCommand = requestPathCommand();
            if (Objects.equals(pathCommand, PathCommand.BACK)) {
                return;
            }
            final Optional<Path> path = requestPath();
            if (path.isEmpty()) {
                continue;
            }
            final Subway subway = new Subway(edgeGroup, path.get());
            final List<String> shortestPath = subway.getShortestPath(pathCommand);
            outputView.printResult(shortestPath, subway.calculateDistance(shortestPath),
                    subway.calculateTime(shortestPath));
            return;
        }
    }


    private Optional<Path> requestPath() {
        return LoopTemplate.tryCatchLoopOptional(() -> {
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
