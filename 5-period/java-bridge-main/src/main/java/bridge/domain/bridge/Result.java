package bridge.domain.bridge;

import bridge.domain.command.UpDown;

public class Result {

    private final UpDown upDown;
    private final RightWrong rightWrong;

    Result(final UpDown upDown, final RightWrong rightWrong) {
        this.upDown = upDown;
        this.rightWrong = rightWrong;
    }

    public static Result from(UpDown upDown, boolean isRight) {
        return new Result(upDown, RightWrong.from(isRight));
    }

    public boolean isRight() {
        return rightWrong.isRight();
    }

    public boolean isUp() {
        return upDown.isUp();
    }

    public RightWrong getRightWrong() {
        return rightWrong;
    }
}
