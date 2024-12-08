package subway.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.Order;
import subway.domain.path.PathFinder;
import subway.domain.station.StationType;
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
        Order order = new Order(StationType.교대역,
                StationType.양재역);

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
        Order order = new Order(StationType.교대역, StationType.양재역);

        // When
        ResultDto resultDto = subwayService.processShortestDistance(order, new PathFinder());

        // Then
        assertAll(
                () -> assertThat(resultDto.totalDistance()).isEqualTo(4),
                () -> assertThat(resultDto.totalTime()).isEqualTo(11),
                () -> assertThat(resultDto.path()).containsExactly("교대역", "강남역", "양재역")
        );

    }
}
