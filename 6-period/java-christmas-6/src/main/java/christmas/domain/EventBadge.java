package christmas.domain;

import java.util.Arrays;

public enum EventBadge {

    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int benefitValue;

    EventBadge(final String name, final int benefitValue) {
        this.name = name;
        this.benefitValue = benefitValue;
    }

    public static EventBadge from(int benefitValue) {
        return Arrays.stream(EventBadge.values())
                .filter(element -> element.benefitValue >= benefitValue)
                .findFirst()
                .orElse(NONE);
    }
}
