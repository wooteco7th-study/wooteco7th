package baseball.domain.baseball;

import baseball.exception.ExceptionMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BaseballNumbers {

    public static final int SIZE = 3;

    private final List<BaseballNumber> numbers;

    public BaseballNumbers(final List<BaseballNumber> numbers) {
        validateUnique(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public static BaseballNumbers from(final List<Integer> numbers) {
        return new BaseballNumbers(numbers.stream()
                .map(BaseballNumber::new)
                .toList());
    }

    public BaseballResult match(BaseballNumbers compared) {
        int strike = countStrike(compared);
        int ball = countBall(compared);
        return new BaseballResult(strike, ball);
    }

    public int countStrike(final BaseballNumbers compared) {
        int strike = 0;
        for (int i = 0; i < SIZE; i++) {
            if (compared.hasExactPosition(i, numbers.get(i))) {
                strike++;
            }
        }
        return strike;
    }

    public int countBall(final BaseballNumbers compared) {
        int ball = 0;
        for (int index = 0; index < SIZE; index++) {
            if (compared.hasDifferentPosition(index, numbers.get(index))) {
                ball++;
            }
        }
        return ball;
    }

    private void validateUnique(final List<BaseballNumber> numbers) {
        if (numbers.stream().distinct().count() != SIZE) {
            throw new IllegalArgumentException(
                    ExceptionMessage.INVALID_LENGTH.getMessage(SIZE));
        }
    }

    private boolean hasExactPosition(final int index, final BaseballNumber number) {
        return numbers.get(index).equals(number);
    }

    private boolean hasDifferentPosition(final int inputIndex, final BaseballNumber number) {
        return IntStream.range(0, SIZE)
                .filter(index -> index != inputIndex)
                .anyMatch(index -> numbers.get(index).equals(number));
    }
}
