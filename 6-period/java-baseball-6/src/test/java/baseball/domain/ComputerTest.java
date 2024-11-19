package baseball.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ComputerTest {

    @Test
    void 정상_작동(){
        // given
        List<Integer> nums = List.of(1, 2, 3);

        // expect
        Assertions.assertThatCode(() -> new Computer(nums))
                .doesNotThrowAnyException();
    }



}
