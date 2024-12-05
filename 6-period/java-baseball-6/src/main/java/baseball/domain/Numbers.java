package baseball.domain;

import java.util.List;

import static baseball.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static baseball.exception.ExceptionMessage.NUMBERS_WRONG_SIZE;

public class Numbers {

    public static final int NUMBERS_SIZE = 3;

    private final List<Number> numbers;

    public Numbers(final List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicatedNumbers(numbers);
        this.numbers = createNumbers(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(Number::getValue)
                .toList();
    }

    public boolean isThreeStrike(Numbers other) {
        for (int i = 0; i < NUMBERS_SIZE; i++) {
            if (!numbers.get(i).equals(other.numbers.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<Number> createNumbers(final List<Integer> numbers) {
        return numbers.stream()
                .map(Number::new)
                .toList();
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
