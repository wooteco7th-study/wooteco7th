package christmas.domain.discount;

import java.util.Arrays;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타",20_000);

    private final String name;
    private final int discount;

    Badge(final String name, final int discount) {
        this.name = name;
        this.discount = discount;
    }

    public static Badge findByDiscount(final int discount) {
        return Arrays.stream(Badge.values())
                .sorted((b1, b2) -> b2.discount - b1.discount)
                .filter(badge -> badge.discount <= discount)
                .findAny()
                .orElse(NONE);

    }

    public String getName() {
        return name;
    }
}
