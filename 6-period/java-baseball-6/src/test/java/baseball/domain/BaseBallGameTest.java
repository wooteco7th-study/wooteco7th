package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import baseball.constant.ScoreType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BaseBallGameTest {

    @Test
    @DisplayName("스코어 타입을 가져온다.")
    void getScoreTypeTest() throws Exception {
        //given
        final GameScore gameScore1 = new GameScore(1, 2);
        final GameScore gameScore2 = new GameScore(0, 0);
        final BaseBallGame baseBallGame1 = new BaseBallGame();
        final BaseBallGame baseBallGame2 = new BaseBallGame();

        //when
        baseBallGame1.updateGameScore(gameScore1);
        baseBallGame2.updateGameScore(gameScore2);
        final ScoreType scoreType1 = baseBallGame1.getScoreType();
        final ScoreType scoreType2 = baseBallGame2.getScoreType();

        //then
        assertAll(
                () -> assertThat(scoreType1).isEqualTo(ScoreType.BALL_AND_STRIKE),
                () -> assertThat(scoreType2).isEqualTo(ScoreType.NOTHING)
        );
    }

    @Test
    @DisplayName("올 스트라이크를 확인한다.")
    void isAllStrikeTest() throws Exception {
        //given
        final GameScore gameScore1 = new GameScore(0, 3);
        final GameScore gameScore2 = new GameScore(1, 2);
        final BaseBallGame baseBallGame1 = new BaseBallGame();
        final BaseBallGame baseBallGame2 = new BaseBallGame();


        //when
        baseBallGame1.updateGameScore(gameScore1);
        baseBallGame2.updateGameScore(gameScore2);
        final boolean allStrike1 = baseBallGame1.isAllStrike();
        final boolean allStrike2 = baseBallGame2.isAllStrike();

        //then
        assertAll(
                () -> assertThat(allStrike1).isTrue(),
                () -> assertThat(allStrike2).isFalse()
        );
    }
}
