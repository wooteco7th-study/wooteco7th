package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestTimeRepository {
    private static final List<StationTimeConnection> connections = new ArrayList<>();

    public static List<StationTimeConnection> connections() {
        return Collections.unmodifiableList(connections);
    }

    public static void addTime(StationTimeConnection stationConnection) {
        connections.add(stationConnection);
    }
}
