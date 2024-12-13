package pairmatching.domain.command;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public enum FunctionCommand {
    // 1. 크루 추가
    //2. 크루 조회
    //3. 크루 초기화
    //4. 페어 매칭
    //5. 페어 조회
    //6. 페어 초기화
    //Q. 종료
    크루추가("1"), 크루조회("2"), 크루초기화("3"),
    페어매칭("4"), 페어조회("5"), 페어초기화("6"), 종료("Q");

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

    public boolean isRegardingCrew() {
        return addCrew() || inquireCrew() || initializeCrew();
    }

    public boolean isRegardingPair() {
        return isInitialized() || isPairMatching() || IsPairInquiry();
    }

    public boolean addCrew() {
        return this == 크루추가;
    }

    public boolean inquireCrew() {
        return this == 크루조회;
    }

    public boolean initializeCrew() {
        return this == 크루초기화;
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
