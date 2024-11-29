package baseball.domain;

import baseball.constant.GameRule;
import java.util.stream.IntStream;

public class BallStrikeChecker {

    private final ComputerNumber computerNumber;
    private final UserNumber userNumber;

    public BallStrikeChecker(final ComputerNumber computerNumber, final UserNumber userNumber) {
        this.computerNumber = computerNumber;
        this.userNumber = userNumber;
    }

    public int calculateBall() {
        return (int) IntStream.range(0, GameRule.NUMBERS_SIZE)
                .filter(idx -> isBall(idx, userNumber.getNumberByIdx(idx)))
                .count();
    }

    public int calculateStrike() {
        return (int) IntStream.range(0, GameRule.NUMBERS_SIZE)
                .filter(idx -> computerNumber.isMatchedNumber(idx, userNumber.getNumberByIdx(idx)))
                .count();
    }

    private boolean isBall(final int idx, final int number) {
        return computerNumber.hasNumber(number) && !computerNumber.isMatchedNumber(idx, number);
    }

}
