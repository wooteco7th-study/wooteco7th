package lotto.domain;

import java.util.Map;

public class WinningBenefit {

    private static final int PERCENTAGE = 100;

    private final Map<LottoRank, Integer> winningReceipt;

    public WinningBenefit(final Map<LottoRank, Integer> winningReceipt) {
        this.winningReceipt = winningReceipt;
    }

    public double getWinningRatio(final Money money) {
        final double value = money.getValue();
        return (calculateWinningPrice() / value) * PERCENTAGE;
    }

    private double calculateWinningPrice() {
        return winningReceipt.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

}
