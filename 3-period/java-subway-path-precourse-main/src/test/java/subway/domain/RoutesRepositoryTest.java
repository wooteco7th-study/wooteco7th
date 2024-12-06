package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoutesRepositoryTest {

    @Test
    @DisplayName("두 인접 정점간의 시간을 구한다.")
    void getTime() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        RoutesRepository routesRepository = new RoutesRepository(routes, stations);

        // When
        int time = routesRepository.getTime(stations.get(0).getName(), stations.get(1).getName());

        // Then
        assertThat(time).isEqualTo(8);
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

    @Test
    @DisplayName("두 인접 정점간의 거리를 구한다.")
    void getDistance() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        RoutesRepository routesRepository = new RoutesRepository(routes, stations);

        // When
        int distance = routesRepository.getDistance(stations.get(0).getName(), stations.get(1).getName());

        // Then
        assertThat(distance).isEqualTo(2);
    }

    @Test
    @DisplayName("두 정점간의 최단 시간을 구한다.")
    void getShortestTime() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        RoutesRepository routesRepository = new RoutesRepository(routes, stations);

        // When
        Integer shortestTime = routesRepository.getShortestTime(stations.get(2), stations.get(1));

        // Then
        assertThat(shortestTime).isEqualTo(7);
    }

    @Test
    @DisplayName("두 정점간의 최단 거리를 구한다.")
    void getShortestDistance() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        RoutesRepository routesRepository = new RoutesRepository(routes, stations);

        // When
        Integer shortestTime = routesRepository.getShortestDistance(stations.get(2), stations.get(1));

        // Then
        assertThat(shortestTime).isEqualTo(4);
    }

    @Test
    @DisplayName("최소시간 경로를 구한다.")
    void getShortestTimePath() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        RoutesRepository routesRepository = new RoutesRepository(routes, stations);

        // When
        List<String> shortestTimePath = routesRepository.getShortestTimePath(stations.get(2), stations.get(1));

        // Then
        assertThat(shortestTimePath).containsExactly("교대역", "남부터미널역", "양재역");
    }

    @Test
    @DisplayName("최단경로를 구한다.")
    void getShortestDistancePath() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        RoutesRepository routesRepository = new RoutesRepository(routes, stations);

        // When
        List<String> shortestTimePath = routesRepository.getShortestDistancePath(stations.get(2), stations.get(1));

        // Then
        assertThat(shortestTimePath).containsExactly("교대역", "강남역", "양재역");
    }

    @Test
    @DisplayName("원소를 추가한다.")
    void add() {
        // Given
        RoutesRepository routesRepository = new RoutesRepository(new ArrayList<>(), new ArrayList<>());

        // When
        Route route = new Route(new Station("강남역"), new Station("양재역"), 2, 8);
        routesRepository.add(route);

        // Then
        assertThat(routesRepository).extracting("routes").isEqualTo(List.of(route));
    }

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
        RoutesRepository routesRepository = new RoutesRepository(routes, stations);

        // When & Then
        assertThatThrownBy(
                () -> routesRepository.validatePathConnected(stations.get(3), stations.get(6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("총 시간을 구한다.")
    void getTotalTime() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        RoutesRepository routesRepository = new RoutesRepository(routes, stations);

        // When
        int totalTime = routesRepository.getTotalTime(List.of("교대역", "강남역", "양재역"));

        // Then
        assertThat(totalTime).isEqualTo(11);
    }

    @Test
    @DisplayName("총 거리를 구한다.")
    void getTotalDistance() {
        // Given
        List<Station> stations = getStations();
        List<Route> routes = getRoutes(stations);
        RoutesRepository routesRepository = new RoutesRepository(routes, stations);

        // When
        int totalTime = routesRepository.getTotalDistance(List.of("교대역", "강남역", "양재역"));

        // Then
        assertThat(totalTime).isEqualTo(4);
    }
}
