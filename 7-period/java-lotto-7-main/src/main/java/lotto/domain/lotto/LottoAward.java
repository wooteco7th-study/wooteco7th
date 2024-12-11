package lotto.domain.lotto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum LottoAward {

    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchingCount;
    private final boolean matchBonus;
    private final int prize;

    private static final List<LottoAward> VALUED_LOTTO_AWARD = Arrays.stream(LottoAward.values())
            .filter(award -> award != NONE)
            .sorted(Comparator.comparingInt(award -> award.matchingCount))
            .toList();

    LottoAward(final int matchingCount, final boolean matchBonus, final int prize) {
        this.matchingCount = matchingCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    public static LottoAward from(int matchingCount, boolean matchBonus) {
        return VALUED_LOTTO_AWARD.stream()
                .filter(lottoAward -> lottoAward.matchingCount == matchingCount && lottoAward.matchBonus == matchBonus)
                .findFirst()
                .orElse(NONE);
    }

    public static List<LottoAward> getValuedLottoAward() {
        return VALUED_LOTTO_AWARD;
    }

    public int getPrize() {
        return prize;
    }
}
