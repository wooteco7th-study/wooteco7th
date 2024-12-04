package bridge.dto;

import bridge.domain.bridge.Result;
import bridge.domain.bridge.RightWrong;

public record ResultDto(String up, String down) {

    private static final String BLANK = " ";

    public static ResultDto from(Result result) {
        if (result.isUp()) {
            return new ResultDto(result.getRightWrong().name(), BLANK);
        }
        return new ResultDto(BLANK, result.getRightWrong().name());
    }

    public boolean isSuccess() {
        return up.equals(RightWrong.O.name()) || down.equals(RightWrong.O.name());
    }
}
