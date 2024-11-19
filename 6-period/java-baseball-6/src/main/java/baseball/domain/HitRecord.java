package baseball.domain;

import baseball.view.PrintMessage;

public class HitRecord {

    private static final Integer NOTHING = 0;
    private static final Integer COUNT = 1;
    private static final Integer THREE_STRIKE = 3;
    private static final String SPACE = " ";

    private Integer strike;
    private Integer ball;

    public HitRecord() {
        this.strike = 0;
        this.ball = 0;
    }

    public void addStrike() {
        strike += COUNT;
    }

    public void addBall() {
        ball += COUNT;
    }

    public boolean isThreeStrike() {
        return strike.equals(THREE_STRIKE);
    }

    @Override
    public String toString() {
        if (strike + ball == NOTHING) {
            return PrintMessage.NOTHING.getMessage();
        }

        StringBuilder stringBuilder = new StringBuilder();
        if (ball > NOTHING) {
            stringBuilder.append(ball);
            stringBuilder.append(PrintMessage.BALL.getMessage());
        }
        if (ball > NOTHING && strike > NOTHING) {
            stringBuilder.append(SPACE);
        }
        if (strike > NOTHING) {
            stringBuilder.append(strike);
            stringBuilder.append(PrintMessage.STRIKE.getMessage());
        }
        return stringBuilder.toString();
    }
}
