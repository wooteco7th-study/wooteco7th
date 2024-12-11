package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;

public record LottoDto(List<List<Integer>> numbers) {

    public static LottoDto of(final List<Lotto> lottos) {
        return new LottoDto(lottos.stream()
                .map(Lotto::getNumbers)
                .toList());
    }
}
