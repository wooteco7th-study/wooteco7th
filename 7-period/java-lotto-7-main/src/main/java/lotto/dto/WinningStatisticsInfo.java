package lotto.dto;

import java.util.Map;
import lotto.domain.Winning;

public class WinningStatisticsInfo {
    private final Map<Winning, Integer> winningInfoRepository;
    private final double profitPercent;

    public WinningStatisticsInfo(final Map<Winning, Integer> winningInfoRepository, final double profitPercent) {
        this.winningInfoRepository = winningInfoRepository;
        this.profitPercent = profitPercent;
    }

    public Map<Winning, Integer> getWinningInfoRepository() {
        return winningInfoRepository;
    }

    public double getProfitPercent() {
        return profitPercent;
    }
}
