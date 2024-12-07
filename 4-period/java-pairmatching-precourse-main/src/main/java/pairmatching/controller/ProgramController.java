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
        List<String> backendCrews = CrewFileReader.readBackendCrews();
        List<String> frontendCrews = CrewFileReader.readFrontendCrews();
        PairHistory history = new PairHistory();
        PairGenerator pairGenerator = new PairGenerator(history);
        while (true) {
            OptionCommand optionCommand = retryOnInvalidInput(this::getOptionCommand);
            if (optionCommand == OptionCommand.종료) {
                break;
            }
            if (optionCommand == OptionCommand.페어매칭) {
                Info info = retryOnInvalidInput(this::getInfo);
                if (info.isBackendCourse()) {
                    boolean isExists = history.ExistsHistory(info.getMission());
                    if (isExists) {
                        RematchingCommand answer = retryOnInvalidInput(this::getRematchingCommand);
                        if (answer == RematchingCommand.NO) {
                            continue;
                        }
                    }
                    List<Pair> pairResult = pairGenerator.generate(info.getLevel(), backendCrews);
                    history.add(info.getMission(), pairResult);
                    outputView.printResult(pairResult);
                    continue;
                }
                List<Pair> pairResult = pairGenerator.generate(info.getLevel(), frontendCrews);
                history.add(info.getMission(), pairResult);
                outputView.printResult(pairResult);
                continue;
            }
            if (optionCommand == OptionCommand.페어조회) {
                Info info = retryOnInvalidInput(this::getInfo);
                if (info.isBackendCourse()) {
                    List<Pair> pairResult = history.findByMission(info.getMission());
                    outputView.printResult(pairResult);
                    continue;
                }
                List<Pair> pairResult = history.findByMission(info.getMission());
                outputView.printResult(pairResult);
            }
            if (optionCommand == OptionCommand.페어초기화) {
                history.clear();
                outputView.printClearMsg();

            }
        }
    }

    private RematchingCommand getRematchingCommand() {
        String input = inputView.readRematching();
        RematchingCommand answer = RematchingCommand.from(input);
        return answer;
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
