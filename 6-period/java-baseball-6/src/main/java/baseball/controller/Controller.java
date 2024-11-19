package baseball.controller;

import baseball.util.RandomGenerator;
import baseball.domain.BaseballNumbers;
import baseball.util.Converter;
import baseball.view.InputView;
import baseball.view.OutputView;
import java.util.HashSet;
import java.util.Set;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void process() {
        //        ### ✅ 랜덤 숫자 생성하기
//        1에서 9까지 **서로 다른 임의의 수 3개**를 선택한다.
        BaseballNumbers numbers = makeNumbers();
//        ### ✅ 숫자 입력하기
//        -  게임 플레이어는 컴퓨터가 생각하고 있는 **서로 다른 3개의 숫자**를 입력한다.
        outputView.showStartMessage();
        BaseballNumbers inputNumbers = makeInputNumbers();
    }

    private BaseballNumbers makeInputNumbers() {
        outputView.showCommentForInputNumbers();
        String input = inputView.readLine();
        return makeBaseballNumbers(input);
    }

    private BaseballNumbers makeBaseballNumbers(final String input) {
        Set<Integer> inputNumbers = new HashSet<>();
        for (char c : input.toCharArray()) {
            int number = Converter.convertToInteger(c);
            inputNumbers.add(number);
        }
        return new BaseballNumbers(inputNumbers);
    }

    private BaseballNumbers makeNumbers() {
        Set<Integer> pickNumbers = RandomGenerator.makeRandomNumbers();
        return new BaseballNumbers(pickNumbers);
    }
}
