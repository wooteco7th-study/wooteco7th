package pairmatching.domain.command;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public enum FunctionCommand {
    // 1. 페어 매칭
    //2. 페어 조회
    //3. 페어 초기화
    //Q. 종료

    페어매칭("1"), 페어조회("2"), 페어초기화("3"), 종료("Q");

    private final String value;

    FunctionCommand(final String value) {
        this.value = value;
    }

    public static FunctionCommand from(String input) {
        return Arrays.stream(FunctionCommand.values())
                .filter(command -> Objects.equals(command.value, input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_COMMAND));
    }

    public boolean isQuit() {
        return this == 종료;
    }

    public boolean isInitialized() {
        return this == 페어초기화;
    }

    public boolean isPairMatching() {
        return this == 페어매칭;
    }


    public boolean IsPairInquiry() {
        return this == 페어조회;
    }
}
