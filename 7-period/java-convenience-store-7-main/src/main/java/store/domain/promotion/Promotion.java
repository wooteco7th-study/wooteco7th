package store.domain.promotion;

import java.time.LocalDate;

public class Promotion {

    private final String name;
    private final int buyQuantity;
    private final int getQuantity;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(final String name, final int buyQuantity, final int getQuantity, final LocalDate startDate,
                     final LocalDate endDate) {
        this.name = name;
        this.buyQuantity = buyQuantity;
        this.getQuantity = getQuantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean hasSameName(final String input) {
        return this.name.equals(input);
    }

    public String getName() {
        return name;
    }
}
