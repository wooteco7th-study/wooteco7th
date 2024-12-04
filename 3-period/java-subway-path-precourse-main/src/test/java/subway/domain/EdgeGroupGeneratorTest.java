package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EdgeGroupGeneratorTest {

    @DisplayName("정의된 간선의 정보를 이용하여 간선 그룹을 생성한다.")
    @Test
    void generateTest() {
        //given
        //when
        final EdgeGroup edgeGroup = EdgeGroupGenerator.generate();
        final List<Edge> edges = edgeGroup.getEdges();
        //then
        assertThat(edges).hasSize(EdgeInfo.findAll().size() * 2);
    }
}
