package baseball.controller;

import baseball.domain.baseball.BaseballMatcher;
import baseball.domain.baseball.BaseballNumbers;
import baseball.domain.baseball.BaseballResult;
import baseball.util.Converter;
import baseball.util.Formatter;
import baseball.util.RandomGenerator;
import baseball.domain.Answer;
import baseball.view.InputView;
import baseball.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final Formatter formatter;

    public Controller(final InputView inputView, final OutputView outputView, final Formatter formatter) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.formatter = formatter;
    }

    public void process() {
        outputView.showStartMessage();
        startGame();
        askRestart();
    }

    private void askRestart() {
        outputView.showCommentForRestart();
        if (Answer.sayYes(inputView.readLine())) {
            startGame();
        }
    }

    private void startGame() {
        BaseballNumbers numbers = makeNumbers();
        while (true) {
            BaseballResult result = playGame(numbers);
            if (result.isWin()) {
                outputView.showEndMessage();
                break;
            }
        }
    }

    private BaseballResult playGame(final BaseballNumbers numbers) {
        BaseballNumbers inputNumbers = makeInputNumbers();
        BaseballMatcher matcher = new BaseballMatcher(numbers);
        BaseballResult result = matcher.match(inputNumbers);
        outputView.showResult(formatter.makeResult(result));
        return result;
    }

    private BaseballNumbers makeInputNumbers() {
        outputView.showCommentForInputNumbers();
        String input = inputView.readLine();
        return makeBaseballNumbers(input);
    }

    private BaseballNumbers makeBaseballNumbers(final String input) {
        List<Integer> inputNumbers = new ArrayList<>();
        for (char c : input.toCharArray()) {
            int number = Converter.convertToInteger(c);
            inputNumbers.add(number);
        }
        return new BaseballNumbers(inputNumbers);
    }

    private BaseballNumbers makeNumbers() {
        List<Integer> pickNumbers = RandomGenerator.makeRandomNumbers();
        return new BaseballNumbers(pickNumbers);
    }
}
