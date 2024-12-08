package subway.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.Order;
import subway.domain.path.PathFinder;
import subway.domain.route.Section;
import subway.domain.route.SectionRepository;
import subway.domain.station.Station;
import subway.domain.station.StationName;
import subway.domain.station.StationRepository;
import subway.dto.ResultDto;

class SubwayServiceTest {

    private SubwayService subwayService;

    @BeforeEach
    void setUp() {
        subwayService = new SubwayService();
    }

    @Test
    @DisplayName("최소시간 경로를 구하여 결과를 반환한다.")
    void processMinimumTime() {
        // Given
        List<Station> stations = initializeStations();
        initializeSectionRepository(stations);
        Order order = new Order(new Station(StationName.교대역.name()), new Station(StationName.양재역.name()));

        // When
        ResultDto resultDto = subwayService.processMinimumTime(order, new PathFinder());

        // Then
        assertAll(
                () -> assertThat(resultDto.totalDistance()).isEqualTo(9),
                () -> assertThat(resultDto.totalTime()).isEqualTo(7),
                () -> assertThat(resultDto.path()).containsExactly("교대역", "남부터미널역", "양재역")
        );

    }

    @Test
    @DisplayName("최단경로를 구하여 결과를 반환한다.")
    void processShortestDistance() {
        // Given
        List<Station> stations = initializeStations();
        initializeSectionRepository(stations);
        Order order = new Order(new Station(StationName.교대역.name()), new Station(StationName.양재역.name()));

        // When
        ResultDto resultDto = subwayService.processShortestDistance(order, new PathFinder());

        // Then
        assertAll(
                () -> assertThat(resultDto.totalDistance()).isEqualTo(4),
                () -> assertThat(resultDto.totalTime()).isEqualTo(11),
                () -> assertThat(resultDto.path()).containsExactly("교대역", "강남역", "양재역")
        );

    }

    private void initializeSectionRepository(final List<Station> stations) {
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

    private List<Station> initializeStations() {
        List<Station> stations = List.of(new Station("강남역"), new Station("양재역"), new Station("교대역"),
                new Station("역삼역"), new Station("남부터미널역"), new Station("매봉역"), new Station("양재시민의숲역"));
        for (Station station : stations) {
            StationRepository.addStation(station);
        }
        return stations;
    }
}
