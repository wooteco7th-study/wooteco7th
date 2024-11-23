package christmas.domain;

import java.util.Arrays;

public enum Promotion {
    Champagne("샴페인", 1, 120_000, 25_000),
    NONE("없음", 0, 0, 0);
    private final String promotionName;
    private final int giveAmount;
    private final int appliabeCriteriaMoney;
    private final int price;

    Promotion(final String promotionName, final int giveAmount, final int appliabeCriteriaMoney, final int price) {
        this.promotionName = promotionName;
        this.giveAmount = giveAmount;
        this.appliabeCriteriaMoney = appliabeCriteriaMoney;
        this.price = price;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public int getPrice() {
        return price;
    }

    public int getGiveAmount() {
        return giveAmount;
    }

    public int getAppliabeCriteriaMoney() {
        return appliabeCriteriaMoney;
    }

    public static Promotion of(final int priceBeforeDiscount) {
        return Arrays.stream(values())
                .filter(promotion -> promotion.appliabeCriteriaMoney <= priceBeforeDiscount)
                .findFirst()
                .orElse(NONE);

    }

}
