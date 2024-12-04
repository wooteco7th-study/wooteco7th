package subway.view;

import java.util.List;

public class OutputView {

    private static final String LINE = System.lineSeparator();

    private static final String WELCOME = """
            ## 메인 화면
            1. 경로 조회
            Q. 종료
                        
            ## 원하는 기능을 선택하세요.""";

    private static final String SELECT_ROUTE_CRITERIA = """
            ## 경로 기준
            1. 최단 거리
            2. 최소 시간
            B. 돌아가기

            ## 원하는 기능을 선택하세요.""";
    private static final String TOTAL_RESULT = """
            ## 조회 결과
            [INFO] ---
            [INFO] 총 거리: %dkm
            [INFO] 총 소요 시간: %d분
            [INFO] ---""";

    private static final String STATION_RESULT = "[INFO] %s";

    private static final String ASK_DEPARTURE_STATION = "## 출발역을 입력하세요.";
    private static final String ASK_ARRIVAL_STATION = "## 도착역을 입력하세요.";

    public void showTotalResult(int totalDistance, int totalTime, List<String> results) {
        showln(LINE + format(TOTAL_RESULT, totalDistance, totalTime));
        results.stream()
                .map(result -> format(STATION_RESULT, result))
                .forEach(this::showln);
    }

    public void welcome() {
        showln(WELCOME);
    }

    public void askDepartureStation() {
        showln(LINE + ASK_DEPARTURE_STATION);
    }

    public void askArrivalStation() {
        showln(LINE + ASK_ARRIVAL_STATION);
    }


    public void selectRouteCriteria() {
        showln(LINE + SELECT_ROUTE_CRITERIA);
    }

    public void showException(Exception exception) {
        showln(exception.getMessage());
    }

    private String format(String format, Object... args) {
        return String.format(format, args);
    }

    public void showBlank() {
        System.out.println();
    }

    private void showln(String message) {
        System.out.println(message);
    }
}
