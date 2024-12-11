package lotto.domain;

import java.util.Collections;
import java.util.List;
import lotto.error.ErrorMessage;
import lotto.util.ListValidator;
import lotto.util.NumberValidator;

public class Lotto {

    public static final int NUMBER_MIN = 1;
    public static final int NUMBER_MAX = 45;
    public static final int NUMBERS_SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .toList();
    }

    public boolean hasNumber(final int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validate(List<Integer> numbers) {
        ListValidator.validateDuplicate(numbers, ErrorMessage.DUPLICATED_NUMBER);
        ListValidator.validateSize(numbers, NUMBERS_SIZE, ErrorMessage.EXCEEDS_NUMBERS_SIZE);
        ListValidator.validateRange(numbers,
                number -> NumberValidator.validateRange(number, NUMBER_MIN, NUMBER_MAX, ErrorMessage.EXCEEDS_NUMBER));
    }
}
