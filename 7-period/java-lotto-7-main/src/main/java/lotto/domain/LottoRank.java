package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum LottoRank {

    /**
     * 3개 일치 (5,000원) - 1개
     * 4개 일치 (50,000원) - 0개
     * 5개 일치 (1,500,000원) - 0개
     * 5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
     * 6개 일치 (2,000,000,000원) - 0개
     */
    RANK_1(1, 6, 2_000_000_000, false),
    RANK_2(2, 5, 30_000_000, true),
    RANK_3(3, 5, 1_500_000, false),
    RANK_4(4, 4, 50_000, false),
    RANK_5(5, 3, 5_000, false),
    NONE(Integer.MAX_VALUE, 0, 0, false);


    private final int rank;
    private final int matchCount;
    private final int price;
    private final boolean isMatchedBonus;

    LottoRank(final int rank, final int matchCount, final int price, final boolean isMatchedBonus) {
        this.rank = rank;
        this.matchCount = matchCount;
        this.price = price;
        this.isMatchedBonus = isMatchedBonus;
    }

    public static LottoRank findByMatchCountAndIsMatchedBonus(final int matchCount, final boolean isMatchedBonus) {
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> Objects.equals(lottoRank.isMatchedBonus, isMatchedBonus))
                .filter(lottoRank -> lottoRank.matchCount == matchCount)
                .findAny()
                .orElse(NONE);
    }

    public static List<LottoRank> findAll() {
        return Arrays.stream(LottoRank.values())
                .sorted((l1, l2) -> l2.rank - l1.rank)
                .filter(lottoRank -> !Objects.equals(lottoRank, NONE))
                .toList();
    }

    public int getRank() {
        return rank;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrice() {
        return price;
    }
}
