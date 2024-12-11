package christmas.domain.event;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public enum Badge {

    없음(new BigDecimal(0)),
    별(new BigDecimal(5000)),
    트리(new BigDecimal(10000)),
    산타(new BigDecimal(20000));

    private static final List<Badge> VALUED_BADGES = Arrays.stream(Badge.values())
            .filter(badge -> badge != 없음)
            .sorted((b1, b2) -> b2.minimumValue.compareTo(b1.minimumValue))
            .toList();

    private final BigDecimal minimumValue;

    Badge(final BigDecimal atLeastPrice) {
        this.minimumValue = atLeastPrice;
    }

    public static String showName(BigDecimal totalPrice) {
        return VALUED_BADGES.stream()
                .filter(badge -> totalPrice.compareTo(badge.minimumValue) >= 0)
                .findFirst()
                .map(Badge::name)
                .orElse(없음.name());
    }
}
