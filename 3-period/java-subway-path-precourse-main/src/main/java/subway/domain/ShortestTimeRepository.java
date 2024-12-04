package subway.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShortestTimeRepository {
    private static final Map<StationConnection, Integer> connections = new HashMap<>();

    public static Map<StationConnection, Integer> connections() {
        return new LinkedHashMap<>(connections);
    }

    public static void addTime(StationConnection stationConnection, int time) {
        connections.put(stationConnection, time);
    }
}
