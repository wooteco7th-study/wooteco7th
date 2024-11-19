package baseball.domain;

public class BaseballResult {

    private final int strike;
    private final int ball;

    public BaseballResult(final int strike, final int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    public boolean isWin() {
        return strike == BaseballRules.SIZE.getValue();
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }
}
