package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathFinderTest {

    @Test
    @DisplayName("두 정점이 연결되지 않으면 예외를 발생한다.")
    void validatePathConnected() {
        // Given
        List<Station> stations = getStations();
        StationRepository stationRepository = new StationRepository();
        for (Station station : stations) {
            stationRepository.addStation(station);
        }
        List<Route> routes = getRoutes(stations);
        RoutesRepository routesRepository = new RoutesRepository(routes);
        PathFinder pathFinder = new PathFinder(routes);

        // When & Then
        assertThatThrownBy(
                () -> pathFinder.validatePathConnected(routesRepository, stations.get(3), stations.get(6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("두 정점간의 최단 시간을 구한다.")
    void getShortestTime() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        PathFinder pathFinder = new PathFinder(routes);

        // When
        Integer shortestTime = pathFinder.getShortestTime(stations.get(2), stations.get(1));

        // Then
        assertThat(shortestTime).isEqualTo(7);
    }

    @Test
    @DisplayName("두 정점간의 최단 거리를 구한다.")
    void getShortestDistance() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        PathFinder pathFinder = new PathFinder(routes);

        // When
        Integer shortestTime = pathFinder.getShortestDistance(stations.get(2), stations.get(1));

        // Then
        assertThat(shortestTime).isEqualTo(4);
    }

    @Test
    @DisplayName("최소시간 경로를 구한다.")
    void getShortestTimePath() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        PathFinder pathFinder = new PathFinder(routes);

        // When
        List<String> shortestTimePath = pathFinder.getShortestTimePath(stations.get(2), stations.get(1));

        // Then
        assertThat(shortestTimePath).containsExactly("교대역", "남부터미널역", "양재역");
    }

    @Test
    @DisplayName("최단경로를 구한다.")
    void getShortestDistancePath() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        PathFinder pathFinder = new PathFinder(routes);

        // When
        List<String> shortestTimePath = pathFinder.getShortestDistancePath(stations.get(2), stations.get(1));

        // Then
        assertThat(shortestTimePath).containsExactly("교대역", "강남역", "양재역");
    }

    private List<Route> getRoutes(final List<Station> stations) {
        return List.of(
                new Route(stations.get(0), stations.get(1), 8, 2),
                new Route(stations.get(2), stations.get(0), 3, 2),
                new Route(stations.get(0), stations.get(3), 3, 2),
                new Route(stations.get(2), stations.get(4), 2, 3),
                new Route(stations.get(4), stations.get(1), 5, 6),
                new Route(stations.get(1), stations.get(5), 1, 1),
                new Route(stations.get(0), stations.get(1), 8, 2),
                new Route(stations.get(1), stations.get(6), 3, 10)
        );
    }

    private List<Station> getStations() {
        return List.of(new Station("강남역"), new Station("양재역"), new Station("교대역"),
                new Station("역삼역"), new Station("남부터미널역"), new Station("매봉역"), new Station("양재시민의숲역"));
    }
}
