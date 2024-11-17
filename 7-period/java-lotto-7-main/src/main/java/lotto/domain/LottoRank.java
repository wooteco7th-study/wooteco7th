package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum LottoRank {
    RANK_1(1, 6, 2_000_000_000, false),
    RANK_2(2, 5, 30_000_000, true),
    RANK_3(3, 5, 1_500_000, false),
    RANK_4(4, 4, 50_000, false),
    RANK_5(5, 3, 5_000, false);

    private final int rank;
    private final int matchNumberCount;
    private final int prizePrice;
    private final boolean isMatchedBonus;

    LottoRank(final int rank, final int matchNumberCount, final int prizePrice, final boolean isMatchedBonus) {
        this.rank = rank;
        this.matchNumberCount = matchNumberCount;
        this.prizePrice = prizePrice;
        this.isMatchedBonus = isMatchedBonus;
    }

    public static LottoRank findByMatchNumberCountAndIsMatchedBonus(final int matchNumberCount, final boolean isMatchedBonus) {
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> Objects.equals(lottoRank.isMatchedBonus, isMatchedBonus))
                .filter(lottoRank -> Objects.equals(lottoRank.matchNumberCount, matchNumberCount))
                .findAny()
                .orElse(null);

    }

    public static List<LottoRank> findAll() {
        return Arrays.stream(LottoRank.values())
                .toList();
    }

    public int getRank() {
        return rank;
    }

    public int getMatchNumberCount() {
        return matchNumberCount;
    }

    public int getPrizePrice() {
        return prizePrice;
    }

    public boolean isMatchedBonus() {
        return isMatchedBonus;
    }
}
