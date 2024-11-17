package lotto.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinningResult {

    private final List<Lotto> lottos;
    private final WinningNumber winningNumber;

    public WinningResult(final List<Lotto> lottos, final WinningNumber winningNumber) {
        this.lottos = lottos;
        this.winningNumber = winningNumber;
    }

    public Map<LottoRank, Integer> getLottoRankResult() {
        final Map<LottoRank, Integer> lottoRankResult = initializeLottoRankResult();
        for (Lotto lotto : lottos) {
            final LottoRank lottoRank = winningNumber.calculateLottoRank(lotto);
            lottoRankResult.put(lottoRank, lottoRankResult.getOrDefault(lottoRank, 0) + 1);
        }
        lottoRankResult.remove(LottoRank.RANK_NONE);
        return lottoRankResult;
    }

    private Map<LottoRank, Integer> initializeLottoRankResult() {
        final Map<LottoRank, Integer> lottoRankResult = new LinkedHashMap<>();
        final List<LottoRank> lottoRanks = LottoRank.findAll();
        for (LottoRank lottoRank : lottoRanks) {
            lottoRankResult.put(lottoRank, 0);
        }
        return lottoRankResult;
    }
}
