package subway.domain;

import java.util.List;

public class Result {

    private final List<String> ways;

    public Result(List<String> ways) {
        this.ways = ways;
    }

    private int calculateDistance() {
        int distance = 0;
        for (int count = 0; count < ways.size() - 1; count++) {
            distance += LineInfoRepository.findDistance(ways.get(count), ways.get(count+1));
        }
        return distance;
    }

    private int calculateMinute() {
        int minute = 0;
        for (int count = 0; count < ways.size() - 1; count++) {
            minute += LineInfoRepository.findMinute(ways.get(count), ways.get(count+1));
        }
        return minute;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("## 조회 결과\n");
        stringBuilder.append("[INFO] ---\n");
        stringBuilder.append("[INFO] 총 거리: ").append(calculateDistance()).append("km\n");
        stringBuilder.append("[INFO] 총 소요 시간: ").append(calculateMinute()).append("분\n");
        stringBuilder.append("[INFO] ---\n");

        for (String way : ways) {
            stringBuilder.append("[INFO] ").append(way).append("\n");
        }
        return stringBuilder.toString();
    }
}
