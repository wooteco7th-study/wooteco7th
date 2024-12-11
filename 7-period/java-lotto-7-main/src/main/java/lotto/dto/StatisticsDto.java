package lotto.dto;

import java.util.List;
import java.util.Map;
import lotto.domain.lotto.LottoAward;

public record StatisticsDto(List<Integer> values) {
    public static StatisticsDto of(final Map<LottoAward, Integer> map) {
        return new StatisticsDto(LottoAward.getValuedLottoAward().stream()
                .map(map::get)
                .toList());
    }
}
