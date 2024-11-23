package christmas.domain.vo;

import christmas.domain.Badge;
import christmas.domain.Promotion;

public class MyPromotion {
    private final Promotion promotion;

    public MyPromotion(final Promotion promotion) {
        this.promotion = promotion;
    }

    public Promotion getPromotion() {
        return promotion;
    }

}
