package store.domain.stock;

public enum StockMessage {

    EXIST("%d개"), NONE("재고 없음");

    private final String value;

    StockMessage(final String value) {
        this.value = value;
    }

    public static String makeMessage(final int quantity) {
        if (quantity == 0) {
            return NONE.value;
        }
        return String.format(EXIST.value, quantity);
    }
}
