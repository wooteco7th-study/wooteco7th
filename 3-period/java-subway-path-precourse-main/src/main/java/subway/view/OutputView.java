package subway.view;

import subway.domain.Station;

import java.util.List;

public class OutputView {
    private static final String RESULT_MSG = """
            
            ## 조회 결과
            [INFO] ---
            [INFO] 총 거리: %dkm
            [INFO] 총 소요 시간: %d분
            [INFO] ---
            """;

    public void printResult(final List<Station> stations, final int totalDistance, final int totalTime) {
        System.out.printf(RESULT_MSG, totalDistance, totalTime);
        stations.forEach(station -> System.out.println(station.getName()));
    }

    public void printError(String error) {
        System.out.println(error);
    }
}
