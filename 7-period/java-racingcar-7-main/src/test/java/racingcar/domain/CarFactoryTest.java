package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.constant.ErrorMessage;

class CarFactoryTest {

    @Test
    @DisplayName("자동차 이름에 중복이 존재하여 예외가 발생한다.")
    void validateDuplicateTest() throws Exception {
        //given
        final List<String> carNames = List.of("아주작은자동차", "아주작은자동차");
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new CarFactory(new RandomNumberGenerator(), carNames))
                .withMessageContaining(ErrorMessage.INVALID_DUPLICATE_CARS_NAME.getMessage());
    }
}
