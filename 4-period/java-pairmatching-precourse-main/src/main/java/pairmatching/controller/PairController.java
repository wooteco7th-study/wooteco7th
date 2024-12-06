package pairmatching.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.domain.PairCommand;
import pairmatching.domain.PairResult;
import pairmatching.domain.PairSystem;
import pairmatching.domain.RematchCommand;
import pairmatching.dto.PairName;
import pairmatching.util.LoopTemplate;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairController {

    private final InputView inputView;
    private final OutputView outputView;

    public PairController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final PairResult pairResult = new PairResult(new HashMap<>());
        final PairSystem pairSystem = new PairSystem(pairResult);
        LoopTemplate.tryCatchLoop(() -> start(pairSystem));
    }

    private void start(final PairSystem pairSystem) {
        final PairCommand pairCommand = requestPairCommand();
        if (Objects.equals(pairCommand, PairCommand.QUIT)) {
            return;
        }
        if (Objects.equals(pairCommand, PairCommand.MATCHING)) {
            outputView.printPreview();
            match(pairSystem);
        }
        if (Objects.equals(pairCommand, PairCommand.LOOK_UP)) {
            outputView.printPreview();
            lookUp(pairSystem);
        }
        if (Objects.equals(pairCommand, PairCommand.CLEAR)) {
            clear(pairSystem);
        }
        start(pairSystem);
    }

    private void clear(final PairSystem pairSystem) {
        outputView.printClear();
        pairSystem.clear();
    }

    private void lookUp(final PairSystem pairSystem) {
        final Mission mission = requestMission();
        final List<Pair> pairs = pairSystem.lookUp(mission);
        responseMatchResult(pairs);
    }

    private void match(final PairSystem pairSystem) {
        final Mission mission = requestMission();
        if (pairSystem.hasMatchResult(mission)) {
            final boolean rematch = rematch(mission, pairSystem);
            if (!rematch) {
                match(pairSystem);
            }
            return;
        }
        responseMatchResult(pairSystem.match(mission));
    }

    private boolean rematch(final Mission mission, final PairSystem pairSystem) {
        final RematchCommand rematchCommand = requestRematchCommand();
        if (Objects.equals(rematchCommand, RematchCommand.YES)) {
            responseMatchResult(pairSystem.rematch(mission));
            return true;
        }
        return false;
    }

    private RematchCommand requestRematchCommand() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskRematch();
            return inputView.readRematchCommand();
        });
    }

    private Mission requestMission() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskMission();
            return inputView.readMission();
        });
    }

    private PairCommand requestPairCommand() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printPairCommand();
            return inputView.readPairCommand();
        });
    }

    private void responseMatchResult(final List<Pair> pairs) {
        final List<PairName> pairNames = pairs.stream()
                .map(PairName::of)
                .toList();
        outputView.printMatchResult(pairNames);
    }
}
