package lotto.dto;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lotto.domain.LottoRank;

public record WinningReceiptGroup(
        List<WinningReceipt> winningReceipts
) {
    public static WinningReceiptGroup of(final Map<LottoRank, Integer> winningResult) {
        return new WinningReceiptGroup(
                winningResult.entrySet().stream()
                        .map(WinningReceipt::of)
                        .toList()
        );
    }

    public record WinningReceipt(
            int rank,
            int matchNumberCount,
            int price,
            int matchCount
    ) {
        public static WinningReceipt of(final Entry<LottoRank, Integer> entry) {
            final LottoRank lottoRank = entry.getKey();
            return new WinningReceipt(
                    lottoRank.getRank(),
                    lottoRank.getMatchCount(),
                    lottoRank.getPrice(),
                    entry.getValue()
            );
        }
    }
}
