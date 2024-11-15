package racingcar.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void 이름_길이_초과시_예외반환(){
        String overName = "aaaaaa";
        assertThatThrownBy(()-> Car.of(overName))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
