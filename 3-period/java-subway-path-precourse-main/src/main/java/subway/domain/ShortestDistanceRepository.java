package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestDistanceRepository {
    private static final List<StationDistanceConnection> connections = new ArrayList<>();

    public static List<StationDistanceConnection> connections() {
        return Collections.unmodifiableList(connections);
    }

    public static void addDistance(StationDistanceConnection stationConnection) {
        connections.add(stationConnection);
    }
}
