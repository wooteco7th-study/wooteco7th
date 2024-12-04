package subway.domain;

public class StationTimeConnection {
    private final Station startStation;
    private final Station endStation;
    private final int time;

    public StationTimeConnection(final Station startStation, final Station endStation, final int time) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.time = time;
    }

    public boolean isSameStationName(final String stationName) {
        return startStation.getName().equals(stationName);
    }

    public Station getStartStation() {
        return startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public int getTime() {
        return time;
    }
}
