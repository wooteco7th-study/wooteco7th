package subway.view;

public enum PrintMessage {

    MAIN("메인 화면\n1. 경로 조회\nQ. 종료"),
    CHOICE("원하는 기능을 선택하세요."),
    FIND_WAY("경로 기준\n1. 최단 거리\n2. 최소 시간\nB. 돌아가기"),
    START_SUBWAY("출발역을 입력하세요."),
    END_SUBWAY("도착역을 입력하세요."),
    RESULT("조회 결과");

    private final String message;

    PrintMessage(final String message) {
        this.message = "## " + message;
    }

    public String getMessage() {
        return message;
    }
}
