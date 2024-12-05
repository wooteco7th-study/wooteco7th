package subway.domain;

import subway.domain.line.Line;
import subway.domain.line.LineRepository;
import subway.domain.line.LineType;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;
import subway.domain.station.StationType;

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
