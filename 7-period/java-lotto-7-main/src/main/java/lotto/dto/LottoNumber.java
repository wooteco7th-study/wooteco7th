package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;

public record LottoNumber(
        List<Integer> numbers
) {
    public static LottoNumber of(final Lotto lotto) {
        return new LottoNumber(lotto.getNumbers());
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
