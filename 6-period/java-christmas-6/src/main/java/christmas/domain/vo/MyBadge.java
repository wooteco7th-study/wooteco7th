package christmas.domain.vo;

import christmas.domain.Badge;

public class MyBadge {
    private final Badge badge;

    public MyBadge(final Badge badge) {
        this.badge = badge;
    }

    public Badge getBadge() {
        return badge;
    }
}
