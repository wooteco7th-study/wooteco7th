package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.path.PathFinder;
import subway.domain.route.Section;
import subway.domain.route.SectionRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;

class PathFinderTest {

    @Test
    @DisplayName("두 정점이 연결되지 않으면 예외를 발생한다.")
    void validatePathConnected() {
        // Given
        List<Station> stations = getStations();
        for (Station station : stations) {
            StationRepository.addStation(station);
        }
        initializeRouteRepository(stations);
        PathFinder pathFinder = new PathFinder();

        // When & Then
        assertThatThrownBy(
                () -> pathFinder.validatePathConnected(stations.get(3).getName(), stations.get(6).getName()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("최소시간 경로를 구한다.")
    void getShortestTimePath() {
        // Given
        List<Station> stations = getStations();
        initializeRouteRepository(stations);
        PathFinder pathFinder = new PathFinder();

        // When
        List<String> shortestTimePath = pathFinder.calculateShortestTimePath(stations.get(2).getName(),
                stations.get(1).getName());

        // Then
        assertThat(shortestTimePath).containsExactly("교대역", "남부터미널역", "양재역");
    }

    @Test
    @DisplayName("최단경로를 구한다.")
    void getShortestDistancePath() {
        // Given
        List<Station> stations = getStations();
        initializeRouteRepository(stations);
        PathFinder pathFinder = new PathFinder();

        // When
        List<String> shortestTimePath = pathFinder.calculateShortestDistancePath(stations.get(2).getName(),
                stations.get(1).getName());

        // Then
        assertThat(shortestTimePath).containsExactly("교대역", "강남역", "양재역");
    }

    private void initializeRouteRepository(final List<Station> stations) {
        List<Section> sections = List.of(
                new Section(stations.get(0), stations.get(1), 8, 2),
                new Section(stations.get(2), stations.get(0), 3, 2),
                new Section(stations.get(0), stations.get(3), 3, 2),
                new Section(stations.get(2), stations.get(4), 2, 3),
                new Section(stations.get(4), stations.get(1), 5, 6),
                new Section(stations.get(1), stations.get(5), 1, 1),
                new Section(stations.get(0), stations.get(1), 8, 2),
                new Section(stations.get(1), stations.get(6), 3, 10)
        );
        for (Section section : sections) {
            SectionRepository.addRoute(section);
        }
    }

    private List<Station> getStations() {
        return List.of(new Station("강남역"), new Station("양재역"), new Station("교대역"),
                new Station("역삼역"), new Station("남부터미널역"), new Station("매봉역"), new Station("양재시민의숲역"));
    }
}
