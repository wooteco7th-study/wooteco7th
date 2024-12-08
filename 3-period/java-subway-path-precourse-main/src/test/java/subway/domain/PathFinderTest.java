package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.path.PathFinder;
import subway.domain.station.StationType;

class PathFinderTest {

    @Test
    @DisplayName("두 정점이 연결되지 않으면 예외를 발생한다.")
    void validatePathConnected() {
        // Given
        PathFinder pathFinder = new PathFinder();

        // When & Then
        assertThatThrownBy(
                () -> pathFinder.validatePathConnected(StationType.역삼역.name(), StationType.양재시민의숲역.name()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("최소시간 경로를 구한다.")
    void getShortestTimePath() {
        // Given
        PathFinder pathFinder = new PathFinder();

        // When
        List<String> shortestTimePath = pathFinder.calculateShortestTimePath(StationType.교대역.name(),
                StationType.양재역.name());

        // Then
        assertThat(shortestTimePath).containsExactly("교대역", "남부터미널역", "양재역");
    }

    @Test
    @DisplayName("최단경로를 구한다.")
    void getShortestDistancePath() {
        // Given
        PathFinder pathFinder = new PathFinder();

        // When
        List<String> shortestTimePath = pathFinder.calculateShortestDistancePath(StationType.교대역.name(),
                StationType.양재역.name());

        // Then
        assertThat(shortestTimePath).containsExactly("교대역", "강남역", "양재역");
    }
}
