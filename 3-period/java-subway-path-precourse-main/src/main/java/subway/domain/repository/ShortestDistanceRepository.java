package subway.domain.repository;

import subway.domain.Station;
import subway.domain.pathconnection.StationDistanceConnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static subway.exception.ExceptionMessage.CONNECTION_NOT_FOUND;

public class ShortestDistanceRepository {
    private static final List<StationDistanceConnection> connections = new ArrayList<>();

    public static List<StationDistanceConnection> connections() {
        return Collections.unmodifiableList(connections);
    }

    public static int getTotalDistance(List<Station> stations) {
        int totalDistance = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            StationDistanceConnection connection = findByStationNames(stations.get(i).getName(), stations.get(i + 1).getName());
            totalDistance += connection.getDistance();
        }
        return totalDistance;
    }

    public static int getTotalTime(List<Station> stations) {
        int totalTime = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            StationDistanceConnection connection = findByStationNames(stations.get(i).getName(), stations.get(i + 1).getName());
            totalTime += connection.getTime();
        }
        return totalTime;
    }

    public static void addDistance(StationDistanceConnection stationConnection) {
        connections.add(stationConnection);
    }

    private static StationDistanceConnection findByStationNames(String startStationName, String endStationName) {
        return connections().stream()
                .filter(connection -> connection.getStartStationName().equals(startStationName) && connection.getEndStationName().equals(endStationName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(CONNECTION_NOT_FOUND.getMessage()));
    }
}
