package subway.domain;

public class Initializer {

    private Initializer() {

    }

    public static void initialize() {
        for (String stationName : StationType.findAll()) {
            StationRepository.addStation(new Station(stationName));
        }
        for (String lineName : LineType.findAll()) {
            LineRepository.addLine(new Line(lineName));
        }
    }
}
