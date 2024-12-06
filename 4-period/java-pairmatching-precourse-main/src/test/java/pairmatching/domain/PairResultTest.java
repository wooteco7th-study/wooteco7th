package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PairResultTest {

    @DisplayName("같은 레벨에서 만난 페어가 존재한다.")
    @Test
    void hasDuplicateLevelCrewsTest() {
        //given
        final Mission mission = new Mission(LevelType.LEVEL_1, CourseType.BACK_END, MissionType.LOTTO);
        final PairGroup pairGroup = new PairGroup(List.of(new Pair(List.of("테스트1", "테스트2"))));

        //when
        final PairResult pairResult = new PairResult(new HashMap<>());
        pairResult.updateResult(mission, pairGroup);
        final boolean result = pairResult.hasDuplicateLevelCrews(
                new Mission(LevelType.LEVEL_1, CourseType.BACK_END, MissionType.BASEBALL_GAME),
                List.of(new Pair(List.of("테스트1", "테스트2", "테스트3"))));

        //then
        assertThat(result).isTrue();

    }
}
