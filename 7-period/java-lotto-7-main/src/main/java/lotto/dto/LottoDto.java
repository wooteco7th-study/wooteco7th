package lotto.dto;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

public record LottoDto(List<List<Integer>> numbers) {

    public static LottoDto of(final List<Lotto> lottos) {
        return new LottoDto(lottos.stream()
                .map(Lotto::getNumbers)
                .map(LottoDto::toNumbers)
                .toList());
    }

    private static List<Integer> toNumbers(List<LottoNumber> numbers) {
        return numbers.stream()
                .map(LottoNumber::getValue)
                .toList();
    }
}
