package store.promotion.dto;

public record NonPromotionCheckResult(
        String name,
        int normal,
        int promotion
) {
}
