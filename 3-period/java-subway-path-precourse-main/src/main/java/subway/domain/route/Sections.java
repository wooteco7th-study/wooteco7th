package subway.domain.route;

import java.util.ArrayList;
import java.util.List;
import subway.domain.station.StationType;

public class Sections {

    private final List<SectionType> sections;

    public Sections(final List<SectionType> sections) {
        this.sections = new ArrayList<>(sections);
    }

    public boolean isConnected(final String start, final String end) {
        return sections.stream()
                .anyMatch(route -> SectionType.existsStartAndEnd(StationType.of(start),
                        StationType.of(end)));
    }

    public int getDistance(final String start, final String end) {
        return sections.stream()
                .filter(sectionType -> sectionType.getDepartureStationName().equals(start))
                .filter(sectionType -> sectionType.getArrivalStationName().equals(end))
                .map(SectionType::getDistance)
                .findFirst()
                .orElse(0);
    }

    private int getTime(final String start, final String end) {
        return sections.stream()
                .filter(route -> route.getDepartureStationName().equals(start))
                .filter(route -> route.getArrivalStationName().equals(end))
                .map(SectionType::getTakenTime)
                .findFirst()
                .orElse(0);
    }

    public int getTotalTime(final List<String> shortestDistancePath) {
        int total = 0;
        for (int i = 0; i < shortestDistancePath.size() - 1; i++) {
            String start = shortestDistancePath.get(i);
            String end = shortestDistancePath.get(i + 1);
            total += getTime(start, end);
        }
        return total;
    }

    public int getTotalDistance(final List<String> shortestDistancePath) {
        int total = 0;
        for (int i = 0; i < shortestDistancePath.size() - 1; i++) {
            String start = shortestDistancePath.get(i);
            String end = shortestDistancePath.get(i + 1);
            total += getDistance(start, end);
        }
        return total;
    }
}
