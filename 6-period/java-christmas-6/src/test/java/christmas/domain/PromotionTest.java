package christmas.domain;

import static christmas.domain.Promotion.Champagne;
import static christmas.domain.Promotion.of;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PromotionTest {

    @DisplayName("120_000원 이상일 때 프로모션 샴페인을 받을 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {120_000})
    void 샴페인_받기_성공(int priceBeforeDiscount) {

        // when
        Promotion myPromotion = of(priceBeforeDiscount);

        // then
        Assertions.assertThat(myPromotion).isEqualTo(Champagne);
    }

    @DisplayName("120_000원 미만 때 프로모션 샴페인을 받을 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 119_999})
    void 샴페인_받기_실패(int priceBeforeDiscount) {

        // when
        Promotion myPromotion = of(priceBeforeDiscount);

        // then
        Assertions.assertThat(myPromotion).isNotEqualTo(Champagne);
    }

    @DisplayName("5000원 미만이면 NONE 반환")
    @ParameterizedTest
    @ValueSource(ints = {0, 119_999})
    void NONE_프로모션_받기(int priceBeforeDiscount) {

        // when
        Promotion myPromotion = of(priceBeforeDiscount);

        // then
        Assertions.assertThat(myPromotion).isEqualTo(Promotion.NONE);
    }

}
