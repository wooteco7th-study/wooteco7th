package oncall.exception;

public enum ErrorMessage {

    // - 월
    //    - [ ]  문자가 아닌 경우
    //    - [ ]  1~12 사이의 숫자가 아닌 경우
    //- 요일
    //    - [ ]  월~금 사이의 문자가 아닌 경우
    //    - [ ]  해당 달의 시작 요일이 아닌 경우
    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),

    // - 닉네임
    //    - [ ]  빈 값이거나 5자 초과일 경우
    //- 근무자수
    //    - [ ]  최소 5명~최대 35명 범위가 아닐 경우
    //- 평일 비상 근무 순번
    //    - [ ]  중복 이름이 존재하는 경우
    //- 휴일 비상 근무 순번
    //    - [ ]  중복 이름이 존재하는 경우
    //- [ ]  평일 순번과 휴일 순번에 근무자가 각각 한번씩 배정되지 않는 경우
    //    - [ ]  평일 비상 근무와 휴일 비상 근무의 크기가 다른 경우
    INVALID_NICKNAME("유효한 닉네임이 아닙니다."),
    INVALID_WORKER_NUMBER("유효한 근무자 수 범위가 아닙니다."),
    INVALID_DUPLICATED_NAME("순번 내에 중복 이름이 존재합니다."),
    INVALID_TURN("근무자가 평일과 주말에 올바르게 배치되지 않았습니다."),

    ;
    
    private final String message;
    
    ErrorMessage(final String message) {
        this.message = message;
    }
    
    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
