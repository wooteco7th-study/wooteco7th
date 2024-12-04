package subway.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShortestDistanceRepository {
    private static final Map<StationConnection, Integer> connections = new HashMap<>();

    public static Map<StationConnection, Integer> connections() {
        return new LinkedHashMap<>(connections);
    }

    public static void addDistance(StationConnection stationConnection, int distance) {
        connections.put(stationConnection, distance);
    }
}
