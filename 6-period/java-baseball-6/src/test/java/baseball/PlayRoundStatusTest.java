package baseball;

import static baseball.PlayRoundStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayRoundStatusTest {
    /**
     *     THREE_STRIKE(3,0),
     *     TWO_STRIKE(2,0),
     *     ONE_STRIKE_TWO_BALL(1,2),
     *     ONE_STRIKE_ONE_BALL(1,1),
     *     ZERO_STRIKE_THREE_BALL(0,3),
     *     ZERO_STRIKE_TWO_BALL(0,2),
     *     ZERO_STRIKE_ONE_BALL(0,1),
     *     NOTHING(0,0);
     */

    @Test
    void 낫싱_테스트(){
        // given
        List<Integer> computer = List.of(1, 2, 3);
        List<Integer> user = List.of(4, 5, 6);

        // when
        PlayRoundStatus status = of(computer, user);

        // then
        Assertions.assertThat(status).isEqualTo(NOTHING);
    }


    @Test
    void Three_스트라이크(){
        // given
        List<Integer> computer = List.of(1, 2, 3);
        List<Integer> user = List.of(1, 2, 3);

        // when
        PlayRoundStatus status = of(computer, user);

        // then
        Assertions.assertThat(status).isEqualTo(THREE_STRIKE);
    }

    @Test
    void Two스트라이크(){
        // given
        List<Integer> computer = List.of(1, 2, 4);
        List<Integer> user = List.of(1, 2, 3);

        // when
        PlayRoundStatus status = of(computer, user);

        // then
        Assertions.assertThat(status).isEqualTo(TWO_STRIKE);
    }

    @Test
    void One스트라이크_Two볼(){
        // given
        List<Integer> computer = List.of(1, 3, 4);
        List<Integer> user = List.of(1, 4, 3);

        // when
        PlayRoundStatus status = of(computer, user);

        // then
        Assertions.assertThat(status).isEqualTo(ONE_STRIKE_TWO_BALL);
    }

    @Test
    void One스트라이크_One볼(){
        // given
        List<Integer> computer = List.of(1, 3, 4);
        List<Integer> user = List.of(1, 2, 3);

        // when
        PlayRoundStatus status = of(computer, user);

        // then
        Assertions.assertThat(status).isEqualTo(ONE_STRIKE_ONE_BALL);
    }

    @Test
    void One스트라이크_Zero볼(){
        // given
        List<Integer> computer = List.of(1, 3, 4);
        List<Integer> user = List.of(1, 7, 8);

        // when
        PlayRoundStatus status = of(computer, user);

        // then
        Assertions.assertThat(status).isEqualTo(ONE_STRIKE_ZERO_BALL);
    }

    @Test
    void Zero스트라이크_Three볼(){
        // given
        List<Integer> computer = List.of(1, 3, 4);
        List<Integer> user = List.of(4, 1, 3);

        // when
        PlayRoundStatus status = of(computer, user);

        // then
        Assertions.assertThat(status).isEqualTo(ZERO_STRIKE_THREE_BALL);
    }

    @Test
    void Zero스트라이크_Two볼(){
        // given
        List<Integer> computer = List.of(1, 3, 4);
        List<Integer> user = List.of(4, 1, 9);

        // when
        PlayRoundStatus status = of(computer, user);

        // then
        Assertions.assertThat(status).isEqualTo(ZERO_STRIKE_TWO_BALL);
    }

    @Test
    void Zero스트라이크_One볼(){
        // given
        List<Integer> computer = List.of(1, 3, 4);
        List<Integer> user = List.of(3, 8, 9);

        // when
        PlayRoundStatus status = of(computer, user);

        // then
        Assertions.assertThat(status).isEqualTo(ZERO_STRIKE_ONE_BALL);
    }

}
