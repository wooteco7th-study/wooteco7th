package baseball.domain;

import java.util.List;

public class ComputerNumber {

    private final List<Integer> values;

    public ComputerNumber(final List<Integer> values) {
        this.values = values;
    }

    public boolean hasNumber(final int number) {
        return values.contains(number);
    }

    public boolean isMatchedNumber(final int idx, final int number) {
        return values.get(idx) == number;
    }
}
