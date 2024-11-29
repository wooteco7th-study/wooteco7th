package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BallStrikeCheckerTest {

    @DisplayName("볼의 갯수를 계산한다.")
    @Test
    void calculateBallTest() {
        //given
        final UserNumber userNumber = new UserNumber(List.of(1, 2, 3));
        final ComputerNumber computerNumber = new ComputerNumber(List.of(2, 1, 4));
        final BallStrikeChecker ballStrikeChecker = new BallStrikeChecker(computerNumber, userNumber);
        //when
        final int ball = ballStrikeChecker.calculateBall();
        //given
        assertThat(ball).isEqualTo(2);

    }

    @DisplayName("스트라이크의 갯수를 계산한다.")
    @Test
    void calculateStrikeTest() {
        //given
        final UserNumber userNumber = new UserNumber(List.of(1, 2, 3));
        final ComputerNumber computerNumber = new ComputerNumber(List.of(2, 1, 4));
        final BallStrikeChecker ballStrikeChecker = new BallStrikeChecker(computerNumber, userNumber);
        //when
        final int strike = ballStrikeChecker.calculateStrike();
        //given
        assertThat(strike).isZero();
    }
}
