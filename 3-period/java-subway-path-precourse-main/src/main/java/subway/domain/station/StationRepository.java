package subway.domain.station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import subway.error.CustomIllegalArgumentException;
import subway.error.ErrorMessage;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    private StationRepository() {

    }

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static Station findByName(final String name) {
        return stations().stream()
                .filter(station -> Objects.equals(station.getName(), name))
                .findAny()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_NOT_FOUND_STATION));
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }
}
