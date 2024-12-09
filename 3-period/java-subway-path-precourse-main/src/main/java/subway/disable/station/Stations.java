//package subway.domain.station;
//
//import static subway.exception.ErrorMessage.INVALID_STATION_NAME;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import subway.exception.CustomIllegalArgumentException;
//
//public class Stations {
//
//    private final List<Station> stations;
//
//    public Stations(final List<Station> stations) {
//        this.stations = new ArrayList<>(stations);
//    }
//
//    public Station findByName(final String name) {
//        return stations.stream()
//                .filter(station -> station.getName().equals(name))
//                .findFirst()
//                .orElseThrow(() -> new CustomIllegalArgumentException(INVALID_STATION_NAME));
//    }
//
//    public List<Station> getStations() {
//        return Collections.unmodifiableList(stations);
//    }
//}
