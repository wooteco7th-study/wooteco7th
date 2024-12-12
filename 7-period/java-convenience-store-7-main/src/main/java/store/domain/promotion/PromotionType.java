package store.domain.promotion;

public enum PromotionType {

    EXIST, NONE;

    public boolean hasPromotion() {
        return this == EXIST;
    }
}
