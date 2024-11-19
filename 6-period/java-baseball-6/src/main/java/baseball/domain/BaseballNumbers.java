package baseball.domain;

import java.util.ArrayList;
import java.util.List;

public class BaseballNumbers {

    private final List<Integer> numbers;

    public BaseballNumbers(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(final List<Integer> numbers) {
        if (numbers.stream().distinct().count() != BaseballRules.SIZE.getValue()) {
            throw new IllegalArgumentException("숫자는 중복되지 않은 3개여야 합니다.");
        }
        if (numbers.stream().anyMatch(number -> number < BaseballRules.MIN_NUMBER.getValue()
                || number > BaseballRules.MAX_NUMBER.getValue())) {
            throw new IllegalArgumentException("숫자는 1과 9 사이여야 합니다.");
        }
    }

    public int countStrike(final BaseballNumbers compared) {
        int strike = 0;
        for (int i = 0; i < BaseballRules.SIZE.getValue(); i++) {
            if (compared.hasExactPosition(i, numbers.get(i))) {
                strike++;
            }
        }
        return strike;
    }

    public int countBall(final BaseballNumbers compared) {
        int ball = 0;
        for (int index = 0; index < BaseballRules.SIZE.getValue(); index++) {
            if (compared.hasDifferentPosition(index, numbers.get(index))) {
                ball++;
            }
        }
        return ball;
    }

    private boolean hasExactPosition(final int index, final int number) {
        return numbers.get(index).equals(number);
    }

    private boolean hasDifferentPosition(final int inputIndex, final int number) {
        for (int index = 0; index < BaseballRules.SIZE.getValue(); index++) {
            if (index == inputIndex) {
                continue;
            }
            if (numbers.get(index).equals(number)) {
                return true;
            }
        }
        return false;
    }
}
