package store.promotion.domain;

import java.time.LocalDate;

public class Promotion {

    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate start;
    private final LocalDate end;

    public Promotion(final String name, final int buy, final int get, final LocalDate start, final LocalDate end) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.start = start;
        this.end = end;
    }

    public int getBuy() {
        return buy;
    }

    public int calculateNonPromotionQuantity(final int quantity) {
        return quantity % (buy + get);
    }

    public int calculatePromotionQuantity(final int quantity) {
        if (quantity % (buy + get) >= buy) {
            return get;
        }
        return 0;
    }

    public int calculateBenefitQuantity(final int quantity) {
        return quantity / (buy  + get);
    }

    public boolean isIn() {
        final LocalDate now = LocalDate.now();
        return (now.isAfter(start) && now.isBefore(end)) || now.isEqual(end) || now.isEqual(start);
    }

    public String getName() {
        return name;
    }
}
