package subway.domain;

public class StationConnection {
    private final Station startStation;
    private final Station endStation;

    public StationConnection(final Station startStation, final Station endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public Station getStartStation() {
        return startStation;
    }

    public Station getEndStation() {
        return endStation;
    }
}
