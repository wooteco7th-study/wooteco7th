package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WinningResult {

    private final LottoGroup lottoGroup;
    private final WinningNumber winningNumber;

    public WinningResult(final LottoGroup lottoGroup, final WinningNumber winningNumber) {
        this.lottoGroup = lottoGroup;
        this.winningNumber = winningNumber;
    }

    public Map<LottoRank, Integer> getWinningReceipt() {
        final Map<LottoRank, Integer> winningReceipt = initializeWinningReceipt();
        putLottoRankCount(winningReceipt);
        winningReceipt.remove(LottoRank.NONE);
        return winningReceipt;
    }

    private void putLottoRankCount(final Map<LottoRank, Integer> winningReceipt) {
        for (Lotto lotto : lottoGroup.getLottos()) {
            final List<Integer> numbers = lotto.getNumbers();
            final int count = winningNumber.countMatchedNumberCount(numbers);
            final boolean matchedBonus = winningNumber.isMatchedBonus(numbers);
            final LottoRank lottoRank = LottoRank.findByMatchCountAndIsMatchedBonus(count,
                    matchedBonus);
            winningReceipt.merge(lottoRank, 1, Integer::sum);
        }
    }

    private Map<LottoRank, Integer> initializeWinningReceipt() {
        return LottoRank.findAll().stream()
                .collect(Collectors.toMap(
                        lottoRank -> lottoRank,
                        lottoRank -> 0
                ));
    }

}
