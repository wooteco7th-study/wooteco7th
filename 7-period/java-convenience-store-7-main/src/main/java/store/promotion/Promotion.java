package store.promotion;

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

    public String getName() {
        return name;
    }
}
