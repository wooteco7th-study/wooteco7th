package baseball.domain;

import baseball.view.PrintMessage;

public class HitRecord {

    private Integer strike;
    private Integer ball;

    public HitRecord() {
        this.strike = 0;
        this.ball = 0;
    }

    public void addStrike() {
        strike += 1;
    }

    public void addBall() {
        ball += 1;
    }

    public boolean isThreeStrike() {
        return strike.equals(3);
    }

    @Override
    public String toString() {
        if (strike + ball == 0) {
            return PrintMessage.NOTHING.getMessage();
        }

        StringBuilder stringBuilder = new StringBuilder();
        if (ball > 0) {
            stringBuilder.append(ball);
            stringBuilder.append(PrintMessage.BALL.getMessage());
        }
        if (ball > 0 && strike > 0) {
            stringBuilder.append(" ");
        }
        if (strike > 0) {
            stringBuilder.append(strike);
            stringBuilder.append(PrintMessage.STRIKE.getMessage());
        }
        return stringBuilder.toString();
    }
}
