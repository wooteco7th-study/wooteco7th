package subway.domain.repository;

import subway.domain.Station;
import subway.domain.pathconnection.StationTimeConnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static subway.exception.ExceptionMessage.CONNECTION_NOT_FOUND;

public class ShortestTimeRepository {
    private static final List<StationTimeConnection> connections = new ArrayList<>();

    public static List<StationTimeConnection> connections() {
        return Collections.unmodifiableList(connections);
    }

    public static void addTime(StationTimeConnection stationConnection) {
        connections.add(stationConnection);
    }

    public static int getTotalDistance(List<Station> stations) {
        int totalDistance = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            StationTimeConnection connection = findByStationNames(stations.get(i).getName(), stations.get(i + 1).getName());
            totalDistance += connection.getDistance();
        }
        return totalDistance;
    }

    public static int getTotalTime(List<Station> stations) {
        int totalTime = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            StationTimeConnection connection = findByStationNames(stations.get(i).getName(), stations.get(i + 1).getName());
            totalTime += connection.getTime();
        }
        return totalTime;
    }

    private static StationTimeConnection findByStationNames(String startStationName, String endStationName) {
        return connections().stream()
                .filter(connection -> connection.getStartStationName().equals(startStationName) && connection.getEndStationName().equals(endStationName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(CONNECTION_NOT_FOUND.getMessage()));
    }
}
