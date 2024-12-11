package lotto.dto;

import java.util.Map.Entry;
import lotto.domain.LottoRank;

public record WinningResultReceipt(
        int rank,
        int matchNumberCount,
        int price,
        int matchCount
) {
    public static WinningResultReceipt of(final Entry<LottoRank, Integer> entry) {
        final LottoRank lottoRank = entry.getKey();
        return new WinningResultReceipt(
                lottoRank.getRank(),
                lottoRank.getMatchCount(),
                lottoRank.getPrice(),
                entry.getValue()
        );
    }
}
