package christmas.domain;

public class BenefitEvent extends Discount implements Benefit {

    private static final Menu BENEFIT_MENU = Menu.CHAMPAGNE;
    private static final int BENEFIT_MENU_QUANTITY = 1;

    public BenefitEvent(final DiscountType discountType, final OrderGroup orderGroup) {
        super(discountType, orderGroup);
    }

    @Override
    public boolean canReceiveDiscount() {
        return isExceedsBenefitEventCondition();
    }

    @Override
    public int calculateDiscount() {
        return BENEFIT_MENU.getPrice() * BENEFIT_MENU_QUANTITY;
    }

    public String getBenefitMenuName() {
        return BENEFIT_MENU.getName();
    }

    public int getBenefitMenuQuantity() {
        return BENEFIT_MENU_QUANTITY;
    }
}
