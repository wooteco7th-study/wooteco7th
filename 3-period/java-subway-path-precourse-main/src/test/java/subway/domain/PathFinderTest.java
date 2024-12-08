package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.path.DistancePathFinder;
import subway.domain.path.TimePathFinder;
import subway.domain.route.SectionType;
import subway.domain.route.Sections;
import subway.domain.station.StationType;

class PathFinderTest {

    @Test
    @DisplayName("두 정점이 연결되지 않으면 예외를 발생한다.")
    void validatePathConnected() {
        // Given
        TimePathFinder timePathFinder = makeTimePathFinder();

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
        TimePathFinder timePathFinder = makeTimePathFinder();

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
        DistancePathFinder timePathFinder = makeDistancePathFinder();

        // When
        List<String> shortestTimePath = timePathFinder.calculateShortestPath(StationType.교대역.name(),
                StationType.양재역.name());

        // Then
        assertThat(shortestTimePath).containsExactly("교대역", "강남역", "양재역");
    }

    // NOTE : 외부 라이브러리 테스트라 제한적
    @Test
    @DisplayName("그래프 초기화한다.")
    void 그래프_초기화() {
        // Given
        TimePathFinder timePathFinder = new TimePathFinder(makeSections());

        // When & Then
        assertThatCode(() -> {
            timePathFinder.initializeGraph(SectionType::getDistance);
        }).doesNotThrowAnyException();
    }

    private TimePathFinder makeTimePathFinder() {
        Sections sections = makeSections();
        return new TimePathFinder(sections);
    }

    private Sections makeSections() {
        return new Sections(SectionType.findAll());
    }

    private DistancePathFinder makeDistancePathFinder() {
        Sections sections = makeSections();
        return new DistancePathFinder(sections);
    }
}
