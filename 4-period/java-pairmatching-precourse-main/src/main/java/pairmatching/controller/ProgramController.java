package pairmatching.controller;

import pairmatching.domain.Info;
import pairmatching.domain.OptionCommand;
import pairmatching.domain.Pair;
import pairmatching.domain.PairGenerator;
import pairmatching.domain.PairHistory;
import pairmatching.domain.RematchingCommand;
import pairmatching.util.CrewFileReader;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;

import static pairmatching.exception.ExceptionHandler.retryOnInvalidInput;

public class ProgramController {
    private final InputView inputView;
    private final OutputView outputView;

    public ProgramController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PairHistory history = new PairHistory();
        PairGenerator pairGenerator = new PairGenerator(history);
        start(history, pairGenerator);
    }

    private void start(PairHistory history, PairGenerator pairGenerator) {
        while (true) {
            OptionCommand optionCommand = retryOnInvalidInput(this::getOptionCommand);
            if (optionCommand == OptionCommand.종료) {
                return;
            }
            if (optionCommand == OptionCommand.페어매칭) {
                retryOnInvalidInput(() -> doMatch(history, pairGenerator));
            }
            if (optionCommand == OptionCommand.페어조회) {
                doSearch(history);
            }
            if (optionCommand == OptionCommand.페어초기화) {
                doClear(history);
            }
        }
    }

    private void doMatch(final PairHistory history, final PairGenerator pairGenerator) {
        while (true) {
            Info info = retryOnInvalidInput(this::getInfo);
            if (history.ExistsHistory(info.getMission()) && retryOnInvalidInput(this::getRematchingCommand) == RematchingCommand.NO) {
                continue;
            }
            List<String> crewNames = getCrewNames(info);
            List<Pair> pairResult = pairGenerator.generate(info.getLevel(), crewNames);
            history.add(info.getMission(), pairResult);
            outputView.printResult(pairResult);
            break;
        }
    }

    private List<String> getCrewNames(final Info info) {
        if (info.isBackendCourse()) {
            return CrewFileReader.readBackendCrews();
        }
        return CrewFileReader.readFrontendCrews();
    }

    private void doSearch(final PairHistory history) {
        Info info = retryOnInvalidInput(this::getInfo);
        if (info.isBackendCourse()) {
            List<Pair> pairResult = history.findByMission(info.getMission());
            outputView.printResult(pairResult);
            return;
        }
        List<Pair> pairResult = history.findByMission(info.getMission());
        outputView.printResult(pairResult);
    }

    private void doClear(final PairHistory history) {
        history.clear();
        outputView.printClearMsg();
    }

    private RematchingCommand getRematchingCommand() {
        String input = inputView.readRematching();
        return RematchingCommand.from(input);
    }

    private OptionCommand getOptionCommand() {
        String option = inputView.readOption();
        return OptionCommand.from(option);
    }

    private Info getInfo() {
        List<String> input = inputView.readInfo();
        return new Info(input);
    }
}
