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

    private boolean isNothing() {
        return strike + ball == NOTHING;
    }

    private boolean isNotBallNothing() {
        return ball > NOTHING;
    }

    private boolean isNotStrikeNothing() {
        return strike > NOTHING;
    }

    private void addBallMessage(StringBuilder stringBuilder) {
        stringBuilder.append(ball);
        stringBuilder.append(PrintMessage.BALL.getMessage());
    }

    private void addStrikeMessage(StringBuilder stringBuilder) {
        stringBuilder.append(strike);
        stringBuilder.append(PrintMessage.STRIKE.getMessage());
    }

    private void addSpace(StringBuilder stringBuilder) {
        if (isNotBallNothing() && isNotStrikeNothing()) {
            stringBuilder.append(SPACE);
        }
    }

    @Override
    public String toString() {
        if (isNothing()) {
            return PrintMessage.NOTHING.getMessage();
        }

        StringBuilder stringBuilder = new StringBuilder();
        if (isNotBallNothing()) {
            addBallMessage(stringBuilder);
        }
        addSpace(stringBuilder);
        if (isNotStrikeNothing()) {
            addStrikeMessage(stringBuilder);
        }
        return stringBuilder.toString();
    }
}
