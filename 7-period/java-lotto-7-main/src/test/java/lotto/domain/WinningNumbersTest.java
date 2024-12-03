package lotto.domain;

import static lotto.constant.ExceptionMessage.NULL_OR_EMPTY_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningNumbersTest {

    @Test
    void 생성시_파라미터가_널이면_예외를_발생한다() {

        // expect
        assertThatThrownBy(() -> new WinningNumbers(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NULL_OR_EMPTY_MESSAGE.getMessage());
    }

    // TODO: 테스트 시작

    @ParameterizedTest
    @CsvSource({
            "1,2,3,4,5,6,7",
    })
    void 당첨번호_생성_성공(
            int num1, int num2, int num3, int num4, int num5, int num6,
            int bonusNum
    ) {

        // expect
        assertThatCode(() -> new WinningNumbers(
                new Lotto(List.of(num1, num2, num3, num4, num5, num6)),
                new BonusNumber(bonusNum))
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource({
            "1,2,3,4,5,5,6",
            "1,2,3,5,5,5,8"

    })
    void 당첨_번호에_중복된_숫자가_있으면_예외를_발생한다(
            int num1, int num2, int num3, int num4, int num5, int num6,
            int bonusNum) {
        {

            // expect
            assertThatThrownBy(() -> new WinningNumbers(
                    new Lotto(List.of(num1, num2, num3, num4, num5, num6)),
                    new BonusNumber(bonusNum))
            ).isInstanceOf(IllegalArgumentException.class);
        }
    }


    @ParameterizedTest
    @CsvSource({
            "1,2,3,4,5,6,6"
    })
    void 당첨_로또와_보너스번호가_중복되면_예외를_발생한다(
            int num1, int num2, int num3, int num4, int num5, int num6,
            int bonusNum) {
        {

            // expect
            assertThatThrownBy(() -> new WinningNumbers(
                    new Lotto(List.of(num1, num2, num3, num4, num5, num6)),
                    new BonusNumber(bonusNum))
            ).isInstanceOf(IllegalArgumentException.class);
        }
    }

}
