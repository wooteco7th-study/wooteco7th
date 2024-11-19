package baseball.domain;

import baseball.constant.GameRule;
import baseball.constant.ScoreType;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class BaseBallGame {

    private GameScore gameScore;

    public BaseBallGame(final GameScore gameScore) {
        this.gameScore = gameScore;
    }

    public boolean isAllStrike() {
        return gameScore.getStrike() == GameRule.NUMBERS_SIZE;
    }

    public void updateGameScore(final GameScore gameScore) {
        this.gameScore = gameScore;
    }

    public ComputerNumber generateComputerNumber() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < GameRule.NUMBERS_SIZE) {
            int randomNumber = Randoms.pickNumberInRange(GameRule.MIN_NUMBER, GameRule.MAX_NUMBER);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return new ComputerNumber(computer);
    }

    public ScoreType getScoreType() {
        final int ball = gameScore.getBall();
        final int strike = gameScore.getStrike();
        return calculateScoreType(ball, strike);
    }

    private static ScoreType calculateScoreType(final int ball, final int strike) {
        if (ball != 0 && strike != 0) {
            return ScoreType.BALL_AND_STRIKE;
        }
        if (ball == 0 && strike != 0) {
            return ScoreType.STRIKE;
        }
        if (ball != 0) {
            return ScoreType.BALL;
        }
        return ScoreType.NOTHING;
    }
}
