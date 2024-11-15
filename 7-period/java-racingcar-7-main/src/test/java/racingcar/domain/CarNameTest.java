package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.constant.ErrorMessage;

class CarNameTest {

    @Test
    @DisplayName("자동차 이름이 5글자가 초과하여 예외가 발생한다.")
    void validateCarNameLengthTest() throws Exception {
        //given
        final String carName = "아주작은자동차";
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new CarName(carName))
                .withMessageContaining(ErrorMessage.INVALID_EXCEEDS_CAR_NAME_LENGTH.getMessage());

    }

    @Test
    @DisplayName("자동차 이름이 빈문자열 혹은 공백으로만 이루어져 있어 예외가 발생한다.")
    void validateCarNameFormatTest() throws Exception {
        //given
        final String carName1 = "";
        final String carName2 = "  ";
        //should
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new CarName(carName1))
                        .withMessageContaining(ErrorMessage.INVALID_WRONG_NAME_FORMAT.getMessage()),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> new CarName(carName2))
                        .withMessageContaining(ErrorMessage.INVALID_WRONG_NAME_FORMAT.getMessage())
        );

    }

}
