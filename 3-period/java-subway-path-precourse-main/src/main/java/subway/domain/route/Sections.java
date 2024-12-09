package subway.domain.route;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
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

    public int calculateTotalTime(final List<String> shortestDistancePath) {
        return IntStream.range(0, shortestDistancePath.size() - 1)
                .map(i -> calculateTime(shortestDistancePath.get(i), shortestDistancePath.get(i + 1)))
                .sum();
    }

    public int calculateTotalDistance(final List<String> shortestDistancePath) {
        return IntStream.range(0, shortestDistancePath.size() - 1)
                .map(i -> calculateDistance(shortestDistancePath.get(i), shortestDistancePath.get(i + 1)))
                .sum();
    }

    private int calculateDistance(final String start, final String end) {
        return sections.stream()
                .filter(sectionType -> sectionType.getDepartureStationName().equals(start))
                .filter(sectionType -> sectionType.getArrivalStationName().equals(end))
                .map(SectionType::getDistance)
                .findFirst()
                .orElse(0);
    }

    private int calculateTime(final String start, final String end) {
        return sections.stream()
                .filter(route -> route.getDepartureStationName().equals(start))
                .filter(route -> route.getArrivalStationName().equals(end))
                .map(SectionType::getTime)
                .findFirst()
                .orElse(0);
    }
}
