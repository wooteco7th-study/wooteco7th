package lotto.domain;

import java.util.List;
import lotto.error.ErrorMessage;
import lotto.util.ListValidator;
import lotto.util.NumberValidator;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .toList();
    }

    public boolean hasNumber(final int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(final List<Integer> numbers) {
        ListValidator.validateSize(numbers, LOTTO_SIZE, ErrorMessage.INVALID_WRONG_LOTTO_NUMBER_SIZE);
        ListValidator.validateRange(numbers, number -> NumberValidator.validateRange(number, MIN_NUMBER, MAX_NUMBER, ErrorMessage.INVALID_EXCEEDS_LOTTO_NUMBER_RANGE));
        ListValidator.validateDuplicate(numbers, ErrorMessage.INVALID_DUPLICATED_LOTTO_NUMBER);
    }

}
