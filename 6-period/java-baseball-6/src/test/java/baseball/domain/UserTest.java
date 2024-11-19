package baseball.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void 정상_생성(){
        // given
        List<Integer> nums = List.of(1, 2, 3);

        // expect
        Assertions.assertThatCode(() -> new User(nums))
                .doesNotThrowAnyException();
    }

}
