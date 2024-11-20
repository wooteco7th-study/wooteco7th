package baseball.controller;

import baseball.command.Answer;
import baseball.domain.baseball.BaseballNumber;
import baseball.domain.baseball.BaseballNumbers;
import baseball.domain.baseball.BaseballResult;
import baseball.util.Converter;
import baseball.util.Formatter;
import baseball.util.InputValidator;
import baseball.util.RandomGenerator;
import baseball.view.InputView;
import baseball.view.OutputView;
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
        String input = inputView.readLine();
        InputValidator.validateNotNullOrBlank(input);
        if (Answer.from(input).isYes()) {
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
        BaseballResult result = numbers.match(inputNumbers);
        outputView.showResult(formatter.makeResult(result));
        return result;
    }

    private BaseballNumbers makeInputNumbers() {
        outputView.showCommentForInputNumbers();
        String input = inputView.readLine();
        return makeBaseballNumbers(input);
    }

    private BaseballNumbers makeBaseballNumbers(final String input) {
        InputValidator.validateNotNullOrBlank(input);
        return new BaseballNumbers(convertToBaseNumbers(input));
    }

    private static List<BaseballNumber> convertToBaseNumbers(final String input) {
        return input.chars()
                .map(c -> Converter.convertToInteger((char) c))
                .mapToObj(BaseballNumber::new)
                .toList();
    }

    private BaseballNumbers makeNumbers() {
        List<Integer> pickNumbers = RandomGenerator.makeRandomNumbers();
        return BaseballNumbers.from(pickNumbers);
    }
}
