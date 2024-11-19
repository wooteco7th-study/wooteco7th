package baseball;

import baseball.rule.BaseBallValidation;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BaseBallValidationTest {

    @Test
    void ThreeSize_정상_검증(){
        // given
        List<Integer> numbers = List.of(1,2,3);

        // expect
        Assertions.assertThatCode(() -> BaseBallValidation.validateThreeSize(numbers))
                .doesNotThrowAnyException();
    }
    @Test
    void ThreeSize검증_예외반환(){
        // given
        List<Integer> numbers = List.of(1,2,3,4);

        // expect
        Assertions.assertThatThrownBy(() -> BaseBallValidation.validateThreeSize(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 야구숫자_유효범위_정상_검증(){
        // given
        List<Integer> numbers = List.of(1,2,3);

        // expect
        Assertions.assertThatCode(() -> BaseBallValidation.validateInRangeNumber(numbers))
                .doesNotThrowAnyException();
    }

    @Test
    void 야구숫자_유효범위_예외반환(){
        // given
        List<Integer> numbers = List.of(0,11,2);

        // expect
        Assertions.assertThatCode(() -> BaseBallValidation.validateInRangeNumber(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
