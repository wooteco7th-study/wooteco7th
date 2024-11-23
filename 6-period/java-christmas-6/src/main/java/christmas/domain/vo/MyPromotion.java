package christmas.domain.vo;

import christmas.domain.Badge;

public class MyPromotion {
    private final Badge badge;

    public MyPromotion(final Badge badge) {
        this.badge = badge;
    }

    public Badge getBadge() {
        return badge;
    }
}
