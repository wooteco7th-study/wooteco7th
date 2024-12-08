package subway.support;

import subway.domain.line.Line;
import subway.domain.line.LineType;
import subway.domain.line.LineRepository;
import subway.domain.route.Section;
import subway.domain.route.SectionType;
import subway.domain.route.SectionRepository;
import subway.domain.station.Station;
import subway.domain.station.StationType;
import subway.domain.station.StationRepository;

public class Initializer {

    public static void initialize() {
        initializeStations();
        initializeLines();
        initializeSections();
    }

    private static void initializeStations() {
        for (StationType stationType : StationType.findAll()) {
            StationRepository.addStation(new Station(stationType.name()));
        }
    }

    private static void initializeLines() {
        for (LineType lineType : LineType.findAll()) {
            LineRepository.addLine(new Line(lineType.name()));
        }
    }

    private static void initializeSections() {
        for (SectionType section : SectionType.findAll()) {
            SectionRepository.addRoute(makeRoute(section));
        }
    }

    private static Section makeRoute(final SectionType section) {
        return new Section(StationRepository.findByName(section.getDepartureStationName()),
                StationRepository.findByName(section.getArrivalStationName()), section.getTakenTime(),
                section.getDistance());
    }
}
