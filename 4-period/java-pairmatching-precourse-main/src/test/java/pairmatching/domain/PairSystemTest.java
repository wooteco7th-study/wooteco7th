package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PairSystemTest {

    @DisplayName("매칭 테스트")
    @Test
    void matchTest() {
        //given
        final Mission mission = new Mission(LevelType.LEVEL_1, CourseType.BACK_END, MissionType.LOTTO);
        final PairResult pairResult = new PairResult(new HashMap<>());
        //when
        final PairSystem pairSystem = new PairSystem(pairResult);
        final List<Pair> match = pairSystem.match(mission);
        //then
        assertThat(match).isNotEmpty();
    }

    @Test
    @DisplayName("매칭 결과가 존재한다.")
    void hasMatchResult() throws Exception {
        //given
        final Mission mission = new Mission(LevelType.LEVEL_1, CourseType.BACK_END, MissionType.LOTTO);
        final PairResult pairResult = new PairResult(Map.of(mission, new PairGroup(List.of(new Pair(List.of("테스트"))))));
        //when
        final PairSystem pairSystem = new PairSystem(pairResult);
        //then
        assertThat(pairSystem.hasMatchResult(mission)).isTrue();
    }

}
