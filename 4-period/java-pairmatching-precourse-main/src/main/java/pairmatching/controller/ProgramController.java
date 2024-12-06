package pairmatching.controller;

import pairmatching.domain.Info;
import pairmatching.domain.OptionCommand;
import pairmatching.domain.Pair;
import pairmatching.domain.PairGenerator;
import pairmatching.domain.PairResult;
import pairmatching.util.CrewFileReader;
import pairmatching.util.ShuffleGenerator;
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
        PairResult result = new PairResult();
        PairGenerator pairGenerator = new PairGenerator(new ShuffleGenerator(), result);
        while (true) {
            OptionCommand optionCommand = retryOnInvalidInput(this::getOptionCommand);
            if (optionCommand == OptionCommand.종료) {
                break;
            }
            if (optionCommand == OptionCommand.페어매칭) {
                Info info = retryOnInvalidInput(this::getInfo);
                if (info.isBackendCourse()) {
                    List<Pair> pairResult = pairGenerator.generate(info.getLevel(), backendCrews);
                    result.add(info.getMission(), pairResult);
                    outputView.printResult(pairResult);
                    continue;
                }
                List<Pair> pairResult = pairGenerator.generate(info.getLevel(), frontendCrews);
                result.add(info.getMission(), pairResult);
                outputView.printResult(pairResult);
            }
            if (optionCommand == OptionCommand.페어조회) {
                Info info = retryOnInvalidInput(this::getInfo);
                if (info.isBackendCourse()) {
                    List<Pair> pairResult = result.findByMission(info.getMission());
                    outputView.printResult(pairResult);
                    continue;
                }
                List<Pair> pairResult = result.findByMission(info.getMission());
                outputView.printResult(pairResult);
            }
        }
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
