package lotto.domain.lotto;

import static lotto.exception.ErrorMessage.INVALID_LOTTO_NUMBER_DUPLICATED;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public boolean contains(final LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public int countMatchingNumber(final Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public boolean doesMatchBonus(final LottoNumber bonusNumber) {
        return numbers.stream()
                .anyMatch(number -> number.equals(bonusNumber));
    }

    private Set<LottoNumber> initializeNumbers(final List<Integer> numbers) {
        return new TreeSet<>(numbers.stream()
                .map(LottoNumber::new)
                .toList());
    }

    private void validate(List<Integer> numbers) {
        if (hasInvalidSize(numbers)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_LOTTO_SIZE);
        }
        if (isDuplicated(numbers)) {
            throw new CustomIllegalArgumentException(INVALID_LOTTO_NUMBER_DUPLICATED);
        }
    }

    private boolean hasInvalidSize(final List<Integer> numbers) {
        return numbers.size() != LOTTO_SIZE;
    }

    private boolean isDuplicated(final List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lotto lotto)) {
            return false;
        }
        return Objects.equals(getNumbers(), lotto.getNumbers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumbers());
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
