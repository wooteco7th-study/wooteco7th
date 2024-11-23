package christmas.dto;

import christmas.domain.Benefit;
import christmas.domain.BenefitEvent;

public record BenefitMenuReceipt(
        String name,
        int quantity
) {
    public static BenefitMenuReceipt of(final Benefit benefit) {
        return new BenefitMenuReceipt(benefit.getBenefitMenuName(), benefit.getBenefitMenuQuantity());
    }
}
