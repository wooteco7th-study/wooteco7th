package lotto.dto;

import java.util.Map.Entry;
import lotto.domain.LottoRank;

public record WinningLottoRecipe(
        int rank,
        int matchNumberCount,
        int prizePrice,
        int prizeCount
) {

    public static WinningLottoRecipe of(final Entry<LottoRank, Integer> entry) {
        final LottoRank lottoRank = entry.getKey();
        final Integer prizeCount = entry.getValue();
        return new WinningLottoRecipe(
                lottoRank.getRank(),
                lottoRank.getMatchNumberCount(),
                lottoRank.getPrizePrice(),
                prizeCount
        );
    }

}
