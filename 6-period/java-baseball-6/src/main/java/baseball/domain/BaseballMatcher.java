package baseball.domain;

public class BaseballMatcher {

    private final BaseballNumbers baseballNumbers;

    public BaseballMatcher(final BaseballNumbers baseballNumbers) {
        this.baseballNumbers = baseballNumbers;
    }

    public BaseballResult match(BaseballNumbers compared) {
        int strike = baseballNumbers.countStrike(compared);
        int ball = baseballNumbers.countBall(compared);
        return new BaseballResult(strike, ball);
    }
}
