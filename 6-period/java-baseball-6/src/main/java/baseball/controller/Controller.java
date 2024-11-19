package baseball.controller;

import baseball.domain.BaseballMatcher;
import baseball.domain.BaseballNumbers;
import baseball.domain.BaseballResult;
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
        //        ### ✅ 랜덤 숫자 생성하기
//        1에서 9까지 **서로 다른 임의의 수 3개**를 선택한다.
        BaseballNumbers numbers = makeNumbers();
//        ### ✅ 숫자 입력하기
//        -  게임 플레이어는 컴퓨터가 생각하고 있는 **서로 다른 3개의 숫자**를 입력한다.
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
//        ### ✅ 결과 출력하기
//        - 입력한 수에 대한 결과를 볼, 스트라이크 개수로 표시한다.
//        - 같은 수가 같은 자리에 있으면 스트라이크,
//                - 다른 자리에 있으면 볼,
//                - 같은 수가 전혀 없으면 낫싱
//        - 맞추지 못할 경우 다시 숫자를 입력받는다.
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
