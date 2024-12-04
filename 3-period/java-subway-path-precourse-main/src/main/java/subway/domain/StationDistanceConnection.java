package subway.domain;

public class StationDistanceConnection {
    private final Station startStation;
    private final Station endStation;
    private final int distance;

    public StationDistanceConnection(final Station startStation, final Station endStation, final int distance) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.distance = distance;
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

    public int getDistance() {
        return distance;
    }
}
