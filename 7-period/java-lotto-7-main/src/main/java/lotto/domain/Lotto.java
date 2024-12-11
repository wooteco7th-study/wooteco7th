package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lotto.exception.CustomIllegalArgumentException;
import lotto.exception.ErrorMessage;

public class Lotto {

    public static final int LOTTO_SIZE = 6;
    private final Set<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = new TreeSet<>(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_LOTTO_SIZE);
        }
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
