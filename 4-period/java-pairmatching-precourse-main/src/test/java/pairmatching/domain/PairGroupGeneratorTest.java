package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PairGroupGeneratorTest {

    @DisplayName("프론트엔드 페어 그룹 생성 테스트")
    @Test
    void front_generateTest() {
        //should
        final PairGroup pairGroup = PairGroupGenerator.generate(CourseType.FRONT_END);
        final List<Pair> pairs = pairGroup.getPairs();
        assertThat(pairs).hasSize(15 / 2);
    }

    @Test
    @DisplayName("백엔드 페어 그룹 생성 테스트")
    void back_generateTest() throws Exception {
        //should
        final PairGroup pairGroup = PairGroupGenerator.generate(CourseType.BACK_END);
        final List<Pair> pairs = pairGroup.getPairs();
        assertThat(pairs).hasSize(20 / 2);

    }
}
