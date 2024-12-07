package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.route.Section;
import subway.domain.route.SectionRepository;
import subway.domain.station.Station;

class SectionRepositoryTest {

    @Test
    @DisplayName("두 인접 정점간의 거리를 구한다.")
    void getDistance() {
        // Given
        List<Station> stations = getStations();
        initializeRouteRepository(stations);

        // When
        int distance = SectionRepository.getDistance(stations.get(0).getName(), stations.get(1).getName());

        // Then
        assertThat(distance).isEqualTo(2);
    }

    // NOTE : static이므로 테스트 불가
//    @Test
//    @DisplayName("원소를 추가한다.")
//    void add() {
//        // Given
//
//        // When
//        Route route = new Route(new Station("강남역"), new Station("양재역"), 2, 8);
//        RoutesRepository.addRoute(route);
//
//        // Then
//        assertThat(RoutesRepository.routes()).isEqualTo(List.of(route));
//    }

    @Test
    @DisplayName("총 시간을 구한다.")
    void getTotalTime() {
        // Given
        List<Station> stations = getStations();
        initializeRouteRepository(stations);

        // When
        int totalTime = SectionRepository.getTotalTime(List.of("교대역", "강남역", "양재역"));

        // Then
        assertThat(totalTime).isEqualTo(11);
    }

    @Test
    @DisplayName("총 거리를 구한다.")
    void getTotalDistance() {
        // Given
        List<Station> stations = getStations();
        initializeRouteRepository(stations);

        // When
        int totalTime = SectionRepository.getTotalDistance(List.of("교대역", "강남역", "양재역"));

        // Then
        assertThat(totalTime).isEqualTo(4);
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
