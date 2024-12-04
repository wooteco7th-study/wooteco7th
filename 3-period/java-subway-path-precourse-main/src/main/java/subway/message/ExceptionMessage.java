package subway.message;


public enum ExceptionMessage {
    DEPARTURE_STATION_CANT_BE_FOUND("찾을 수 없는 출발역 입니다."),
    ARRIVAL_STATION_CANT_BE_FOUND("찾을 수 없는 도착역 입니다."),
    INVALID_FORMAT("잘못된 형식 입니다");


    private final String message;

    ExceptionMessage(final String message) {
        this.message = String.format("%s %s %s", "[ERROR]", message, "다시 입력해 주세요.");
    }

    public String getMessage() {
        return message;
    }
}
