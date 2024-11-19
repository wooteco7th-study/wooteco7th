package baseball.domain;

import java.util.HashSet;
import java.util.Set;

public class BaseballNumbers {

    private final Set<Integer> numbers;

    public BaseballNumbers(final Set<Integer> numbers) {
        validate(numbers);
        this.numbers = new HashSet<>(numbers);
    }

    private void validate(final Set<Integer> numbers) {
        if (numbers.size() != 3) {
            throw new IllegalArgumentException("숫자는 중복되지 않은 3개여야 합니다.");
        }
    }
}
