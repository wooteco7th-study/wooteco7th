package lotto.domain;

import java.util.Arrays;

public enum Winning {

    NONE(0, false, 0),

    FIFTH(3, false, 5_000),

    FORTH(4, false, 50_000),

    THIRD(5, false, 1_500_000),

    SECOND(5, true, 30_000_000),

    FIRST(6, false, 2_000_000_000);

    private final int matchCount;
    private final boolean hasBonus;
    private final int price;

    Winning(final int matchCount, final boolean hasBonus, final int price) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.price = price;
    }


    public static Winning compile(final int matchCount, final boolean matchBonus) {
        return Arrays.stream(values())
                .filter(winning -> winning.matchCount == matchCount)
                .filter(winning -> {
                    if (winning.matchCount == 5) {
                        return winning.hasBonus == matchBonus;
                    }
                    return true;
                })
                .findFirst()
                .orElse(NONE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isHasBonus() {
        return hasBonus;
    }

    public int getPrice() {
        return price;
    }
}
