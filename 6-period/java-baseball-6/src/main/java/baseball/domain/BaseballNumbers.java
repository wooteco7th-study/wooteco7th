package baseball.domain;

import java.util.HashSet;
import java.util.Set;

public class BaseballNumbers {

    private final Set<Integer> numbers;

    public BaseballNumbers(final Set<Integer> numbers) {
        this.numbers = new HashSet<>(numbers);
    }
}
