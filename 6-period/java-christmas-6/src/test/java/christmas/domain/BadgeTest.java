package christmas.domain;

import static christmas.domain.Badge.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {

    @DisplayName("2만원이상 일때 산타 뱃지 반환할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {30000,20000})
    void 산타_뱃지_받기(int totalDiscountAmount){

        // when
        Badge myBadge = of(totalDiscountAmount);

        // then
        Assertions.assertThat(myBadge).isEqualTo(SANTA);
    }

    @DisplayName("2만원이상이 아니면 산타 뱃지가 아니다.")
    @ParameterizedTest
    @ValueSource(ints = {19000,0})
    void 산타_뱃지가_아닌_다른뱃지(int totalDiscountAmount){

        // when
        Badge myBadge = of(totalDiscountAmount);

        // then
        Assertions.assertThat(myBadge).isNotEqualTo(SANTA);
    }

    @DisplayName("1만원이상 ~2만원미만  일때 트리 뱃지 반환할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {10_000,19_999})
    void 트리_뱃지_받기(int totalDiscountAmount){

        // when
        Badge myBadge = of(totalDiscountAmount);

        // then
        Assertions.assertThat(myBadge).isEqualTo(TREE);
    }

    @DisplayName("1만원이상 ~2만원미만이 아니면 산타 뱃지가 아니다.")
    @ParameterizedTest
    @ValueSource(ints = {9_999,20_001})
    void 트리_뱃지가_아닌_다른뱃지(int totalDiscountAmount){

        // when
        Badge myBadge = of(totalDiscountAmount);

        // then
        Assertions.assertThat(myBadge).isNotEqualTo(TREE);
    }

    @DisplayName("5_000원이상 ~10_000원미만  일때 트리 뱃지 반환할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {5_000,9_999})
    void 별_뱃지_받기(int totalDiscountAmount){

        // when
        Badge myBadge = of(totalDiscountAmount);

        // then
        Assertions.assertThat(myBadge).isEqualTo(STAR);
    }

    @DisplayName("5_000원이상 ~10_000원미만 아니면 산타 뱃지가 아니다.")
    @ParameterizedTest
    @ValueSource(ints = {0,4_999,10_000})
    void 별_뱃지가_아닌_다른뱃지(int totalDiscountAmount){

        // when
        Badge myBadge = of(totalDiscountAmount);

        // then
        Assertions.assertThat(myBadge).isNotEqualTo(STAR);
    }

    @DisplayName("5000원 미만이면 NONE 반환")
    @ParameterizedTest
    @ValueSource(ints = {0,4_999})
    void NONE_뱃지_받기(int totalDiscountAmount){

        // when
        Badge myBadge = of(totalDiscountAmount);

        // then
        Assertions.assertThat(myBadge).isEqualTo(NONE);
    }


}
