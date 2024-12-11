package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lotto.exception.CustomIllegalArgumentException;
import lotto.exception.ErrorMessage;

public class Lotto {

    public static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> numbers;

    public Lotto(final List<Integer> values) {
        validate(values);
        this.numbers = initializeNumbers(values);
    }

    private Set<LottoNumber> initializeNumbers(final List<Integer> numbers) {
        return new TreeSet<>(numbers.stream()
                .map(LottoNumber::new)
                .toList());
    }

    private void validate(List<Integer> numbers) {
        if (hasInvalidSize(numbers) || isDuplicated(numbers)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_LOTTO_SIZE);
        }
    }

    private boolean hasInvalidSize(final List<Integer> numbers) {
        return numbers.size() != LOTTO_SIZE;
    }

    private boolean isDuplicated(final List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
