package store.domain.promotion;

public enum ProcessType {

    MIXED, CAN_GIFT, REGULAR, ONLY_PROMOTION;

    public boolean doesGuideNeeded() {
        return this == MIXED || this == CAN_GIFT;
    }
}
