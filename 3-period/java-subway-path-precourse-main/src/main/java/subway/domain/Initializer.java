package subway.domain;

import subway.domain.line.Line;
import subway.domain.line.LineName;
import subway.domain.line.LineRepository;
import subway.domain.route.Section;
import subway.domain.route.SectionEdge;
import subway.domain.route.SectionRepository;
import subway.domain.station.Station;
import subway.domain.station.StationName;
import subway.domain.station.StationRepository;

public class Initializer {

    public static void initialize() {
        initializeStations();
        initializeLines();
        initializeSections();
    }

    private static void initializeStations() {
        for (StationName stationName : StationName.findAll()) {
            StationRepository.addStation(new Station(stationName.name()));
        }
    }

    private static void initializeLines() {
        for (LineName lineName : LineName.findAll()) {
            LineRepository.addLine(new Line(lineName.name()));
        }
    }

    private static void initializeSections() {
        for (SectionEdge section : SectionEdge.findAll()) {
            SectionRepository.addRoute(makeRoute(section));
        }
    }

    private static Section makeRoute(final SectionEdge section) {
        return new Section(StationRepository.findByName(section.getDepartureStationName()),
                StationRepository.findByName(section.getArrivalStationName()), section.getTakenTime(),
                section.getDistance());
    }
}
