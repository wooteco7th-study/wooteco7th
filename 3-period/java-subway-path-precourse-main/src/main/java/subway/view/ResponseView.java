package subway.view;

import subway.dto.PathSearchResult;


public class ResponseView {
    private static final String NEW_LINE = System.lineSeparator();

    public void printPathResult(PathSearchResult result) {
        StringBuilder builder = new StringBuilder();
        builder.append("## 조회 결과").append(NEW_LINE);
        builder.append("[INFO] ---").append(NEW_LINE);
        builder.append("[INFO] 총 거리: ").append(result.getTotalDistance()).append("km").append(NEW_LINE);
        builder.append("[INFO] 총 소요 시간: ").append(result.getTotalTime()).append("분").append(NEW_LINE);
        builder.append("[INFO] ---").append(NEW_LINE);
        for (String station : result.getStations()) {
            builder.append("[INFO] ").append(station).append(NEW_LINE);
        }
        System.out.println(builder.toString());
    }

}
