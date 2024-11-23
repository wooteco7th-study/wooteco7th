package christmas.Domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Badge {

    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minPrice;

    Badge(String name, int minPrice) {
        this.name = name;
        this.minPrice = minPrice;
    }

    public static String getBadge(int discount) {
        List<Badge> badges = Arrays.stream(Badge.values()).sorted(Comparator.comparing(Badge::getMinPrice).reversed())
                .toList();
        for (Badge badge : badges) {
            if (discount >= badge.minPrice) {
                return badge.name;
            }
        }
        return "없음";
    }

    public int getMinPrice() {
        return minPrice;
    }
}
