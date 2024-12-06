package pairmatching.domain;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PairGroupGeneratorTest {

    @DisplayName("페어 그룹 생성 테스트")
    @Test
    void generateTest() {
        //given
        final PairGroup pairGroup = PairGroupGenerator.generate(CourseType.FRONT_END);
        final List<Pair> pairs = pairGroup.getPairs();
        for (Pair pair : pairs) {
            System.out.println(pair.getCrews());
        }
    }
}
