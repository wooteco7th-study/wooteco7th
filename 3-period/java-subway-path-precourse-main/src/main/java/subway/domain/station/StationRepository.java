package subway.domain.station;

import static subway.exception.ErrorMessage.INVALID_STATION_NAME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import subway.exception.CustomIllegalArgumentException;

public class StationRepository {

    private static final List<Station> stations = new ArrayList<>();

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static Station findByName(final String name) {
        return stations.stream()
                .filter(station -> station.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(INVALID_STATION_NAME));
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }
}
