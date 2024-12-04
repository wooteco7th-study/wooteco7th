package subway.domain;

public class Info {

    private final String line;
    private final String startStation;
    private final String endStation;
    private final int distance;
    private final int minute;

    public Info(final String line, final  String startStation, final String endStation, final int distance, final int minute) {
        this.line = line;
        this.startStation = startStation;
        this.endStation = endStation;
        this.distance = distance;
        this.minute = minute;
    }

    public String getLine() {
        return line;
    }

    public String getStartStation() {
        return startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public int getDistance() {
        return distance;
    }

    public int getMinute() {
        return minute;
    }
}
