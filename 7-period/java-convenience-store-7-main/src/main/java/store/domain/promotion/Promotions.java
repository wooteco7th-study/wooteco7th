package store.domain.promotion;

import java.util.ArrayList;
import java.util.List;

public class Promotions {

    private final List<Promotion> promotions;

    public Promotions(final List<Promotion> promotions) {
        this.promotions = new ArrayList<>(promotions);
    }

    public Promotion findByName(final String name) {
        return promotions.stream()
                .filter(promotion -> promotion.hasSameName(name))
                .findAny()
                .orElse(null);
    }
}
