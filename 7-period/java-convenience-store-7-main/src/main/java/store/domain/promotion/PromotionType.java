package store.domain.promotion;

public enum PromotionType {

    EXIST, NONE;

    private static final String NULL = "null";

    public boolean hasPromotion() {
        return this == EXIST;
    }

    public static PromotionType from(String input) {
        if (input.equals(NULL)) {
            return NONE;
        }
        return EXIST;
    }
}
