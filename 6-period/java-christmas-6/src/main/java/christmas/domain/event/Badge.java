package christmas.domain.event;

import java.util.Arrays;

public enum Badge {
    없음(0), 별(5_000), 트리(10_000), 산타(20_000);
    // - 5천 원 이상: 별
    //- 1만 원 이상: 트리
    //- 2만 원 이상: 산타
    private final int threshold;

    Badge(final int threshold) {
        this.threshold = threshold;
    }

    public static Badge calculateBadge(int input) {
        return Arrays.stream(Badge.values())
                .sorted((b1, b2) -> Integer.compare(b2.threshold, b1.threshold))
                .filter(badge -> badge.threshold < input)
                .findFirst()
                .orElse(없음);
    }
}
