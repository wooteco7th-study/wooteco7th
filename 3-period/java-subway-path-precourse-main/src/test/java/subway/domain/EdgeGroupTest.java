package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.edge.EdgeGroup;
import subway.domain.edge.EdgeGroupGenerator;
import subway.domain.station.Station;
import subway.error.ErrorMessage;

class EdgeGroupTest {

    @DisplayName("최소 시간의 다익스트라 그래프를 가져온다.")
    @Test
    void getDijkstraShortestTimePathTest() {
        //give
        final EdgeGroup edgeGroup = EdgeGroupGenerator.generate();
        //when
        final DijkstraShortestPath dijkstraShortestTimePath = edgeGroup.getDijkstraShortestTimePath();
        final List<String> vertexList = dijkstraShortestTimePath.getPath("교대역", "역삼역").getVertexList();
        System.out.println(vertexList);
        //then
        assertThat(vertexList).hasSize(3);
    }

    @DisplayName("최단 거리의 다익스트라 그래프를 가져온다.")
    @Test
    void getDijkstraShortestDistancePathTest() {
        //give
        final EdgeGroup edgeGroup = EdgeGroupGenerator.generate();
        //when
        final DijkstraShortestPath dijkstraShortestTimePath = edgeGroup.getDijkstraShortestDistancePath();
        final List<String> vertexList = dijkstraShortestTimePath.getPath("교대역", "양재역").getVertexList();
        System.out.println(vertexList);
        //then
        assertThat(vertexList).hasSize(3);
    }

    @Test
    @DisplayName("시작점, 도착점 기준으로 시간,거리를 가져온다.")
    void getDistanceAndTimeTest() throws Exception {
        //given
        final EdgeGroup edgeGroup = EdgeGroupGenerator.generate();

        //when
        final int distanceByStartAndEnd = edgeGroup.getDistanceByStartAndEnd(new Station("교대역"), new Station("강남역"));
        final int timeByStartAndEnd = edgeGroup.getTimeByStartAndEnd(new Station("교대역"), new Station("강남역"));

        //then
        assertAll(
                () -> assertThat(distanceByStartAndEnd).isEqualTo(2),
                () -> assertThat(timeByStartAndEnd).isEqualTo(3)
        );
    }

    @Test
    @DisplayName("시작점, 도착점이 연결되어 있지 않아 예외가 발생한다.")
    void notFoundStartAndEndTest() throws Exception {
        //given
        final EdgeGroup edgeGroup = EdgeGroupGenerator.generate();

        //should
        assertAll(
                () -> assertThatIllegalStateException().isThrownBy(
                                () -> edgeGroup.getDistanceByStartAndEnd(new Station("교대역"), new Station("역삼역")))
                        .withMessageContaining(
                                ErrorMessage.INVALID_NOT_LINKED_STATION.getMessage()),
                () -> assertThatIllegalStateException().isThrownBy(
                                () -> edgeGroup.getTimeByStartAndEnd(new Station("남부터미널역"), new Station("매봉역")))
                        .withMessageContaining(ErrorMessage.INVALID_NOT_LINKED_STATION.getMessage())
        );
    }
}
