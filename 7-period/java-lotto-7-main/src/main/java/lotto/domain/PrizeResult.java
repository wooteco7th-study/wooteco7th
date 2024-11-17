package lotto.domain;

import java.util.Map;
import java.util.Map.Entry;

public class PrizeResult {

    private static final double PERCENTAGE_NUMBER = 100.0;
    private final Map<LottoRank, Integer> lottoRankResult;
    private final Money money;

    public PrizeResult(final Map<LottoRank, Integer> lottoRankResult, final Money money) {
        this.lottoRankResult = lottoRankResult;
        this.money = money;
    }

    public double getPrizeResultRatio() {
        final int value = money.getValue();
        final long totalPrizePrice = calculateTotalPrizePrice();
        return (totalPrizePrice / (double) value) * PERCENTAGE_NUMBER;
    }

    private long calculateTotalPrizePrice() {
        return lottoRankResult.entrySet().stream()
                .mapToLong(this::calculatePrizePrice)
                .sum();
    }

    private long calculatePrizePrice(final Entry<LottoRank, Integer> entry) {
        final LottoRank lottoRank = entry.getKey();
        final int prizePrice = lottoRank.getPrizePrice();
        return (long) prizePrice * entry.getValue();
    }
}
