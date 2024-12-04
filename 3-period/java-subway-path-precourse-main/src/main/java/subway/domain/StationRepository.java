package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static Station findStation(String name) {
        return stations.stream()
                .distinct()
                .filter(station -> station.getName().equals(name))
                .collect(Collectors.toList()).get(0);
    }

    public static void addStation(Station station) {
        if (checkDuplicationStation(station)) {
            return;
        }
        stations.add(station);
    }

    private static boolean checkDuplicationStation(Station station) {
        return stations.contains(station);
    }

    public static boolean notContain(String name) {
        return stations.stream().noneMatch(station -> station.getName().equals(name));
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }
}
