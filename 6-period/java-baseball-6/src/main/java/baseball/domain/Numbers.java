package baseball.domain;

import java.util.List;

import static baseball.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static baseball.exception.ExceptionMessage.NUMBERS_WRONG_SIZE;

public class Numbers {

    public static final int NUMBERS_SIZE = 3;

    private final List<Integer> numbers;

    public Numbers(final List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicatedNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException(NUMBERS_WRONG_SIZE.getMessage());
        }
    }

    private void validateDuplicatedNumbers(List<Integer> numbers) {
        long distinctSize = numbers.stream()
                .distinct()
                .count();
        if (numbers.size() != distinctSize) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        }
    }
}
