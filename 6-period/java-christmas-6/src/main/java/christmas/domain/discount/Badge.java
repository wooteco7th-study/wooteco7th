package christmas.domain.discount;

import java.util.Arrays;

public enum Badge {

    NONE(0, "없음"),
    STAR(5_000, "별"),
    TREE(10_000, "트리"),
    SANTA(20_000, "산타");

    private final int discount;
    private final String name;

    Badge(final int discount, final String name) {
        this.discount = discount;
        this.name = name;
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
