package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.path.DistancePathFinder;
import subway.domain.path.TimePathFinder;
import subway.domain.route.SectionType;
import subway.domain.route.Sections;
import subway.domain.station.StationType;

class TimePathFinderTest {

    @Test
    @DisplayName("두 정점이 연결되지 않으면 예외를 발생한다.")
    void validatePathConnected() {
        // Given
        Sections sections = new Sections(SectionType.findAll());
        TimePathFinder timePathFinder = new TimePathFinder(sections);

        // When & Then
        assertThatThrownBy(
                () -> timePathFinder.validatePath(StationType.역삼역.name(), StationType.양재시민의숲역.name()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("최소시간 경로를 구한다.")
    void getShortestTimePath() {
        // Given
        Sections sections = new Sections(SectionType.findAll());
        TimePathFinder timePathFinder = new TimePathFinder(sections);

        // When
        List<String> shortestTimePath = timePathFinder.calculateShortestPath(StationType.교대역.name(),
                StationType.양재역.name());

        // Then
        assertThat(shortestTimePath).containsExactly("교대역", "남부터미널역", "양재역");
    }

    @Test
    @DisplayName("최단경로를 구한다.")
    void getShortestDistancePath() {
        // Given
        Sections sections = new Sections(SectionType.findAll());
        DistancePathFinder timePathFinder = new DistancePathFinder(sections);

        // When
        List<String> shortestTimePath = timePathFinder.calculateShortestPath(StationType.교대역.name(),
                StationType.양재역.name());

        // Then
        assertThat(shortestTimePath).containsExactly("교대역", "강남역", "양재역");
    }
}
