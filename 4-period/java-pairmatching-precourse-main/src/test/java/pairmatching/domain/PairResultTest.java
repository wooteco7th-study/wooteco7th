package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.error.ErrorMessage;

class PairResultTest {

    @DisplayName("같은 레벨에서 만난 페어가 존재한다.")
    @Test
    void true_hasDuplicateLevelCrewsTest() {
        //given
        final Mission mission1 = new Mission(LevelType.LEVEL_1, CourseType.BACK_END, MissionType.LOTTO);
        final Mission mission2 = new Mission(LevelType.LEVEL_1, CourseType.BACK_END, MissionType.BASEBALL_GAME);
        final PairGroup pairGroup = new PairGroup(List.of(new Pair(List.of("테스트1", "테스트2"))));

        //when
        final PairResult pairResult = new PairResult(new HashMap<>());
        pairResult.updateResult(mission1, pairGroup);
        final boolean result = pairResult.hasDuplicateLevelCrews(mission2,
                List.of(new Pair(List.of("테스트1", "테스트2", "테스트3"))));

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("같은 레벨에서 만난 페어가 존재하지 않는다.")
    void false_hasDuplicateLevelCrewsTest() throws Exception {
        //given
        final Mission mission1 = new Mission(LevelType.LEVEL_1, CourseType.BACK_END, MissionType.LOTTO);
        final Mission mission2 = new Mission(LevelType.LEVEL_2, CourseType.BACK_END, MissionType.PAYMENT);
        final PairGroup pairGroup = new PairGroup(List.of(new Pair(List.of("테스트1", "테스트2"))));

        //when
        final PairResult pairResult = new PairResult(new HashMap<>());
        pairResult.updateResult(mission1, pairGroup);
        final boolean result = pairResult.hasDuplicateLevelCrews(mission2,
                List.of(new Pair(List.of("테스트1", "테스트2", "테스트3"))));

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("매칭 결과 이력이 없어 예외가 발생한다.")
    void fail_findByMissionTest() throws Exception {
        //given
        final Mission mission = new Mission(LevelType.LEVEL_1, CourseType.BACK_END, MissionType.LOTTO);

        //when
        final PairResult pairResult = new PairResult(new HashMap<>());

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> pairResult.findByMission(mission))
                .withMessageContaining(ErrorMessage.INVALID_NOT_FOUND_PAIR_RESULT.getMessage());
    }

    @Test
    @DisplayName("매칭 결과 이력이 존재하여 조회에 성공한다.")
    void success_findByMissionTest() throws Exception {
        //given
        final Mission mission = new Mission(LevelType.LEVEL_1, CourseType.BACK_END, MissionType.LOTTO);
        final PairGroup pairGroup = new PairGroup(List.of(new Pair(List.of("테스트1", "테스트2"))));

        //when
        final PairResult pairResult = new PairResult(new HashMap<>());
        pairResult.updateResult(mission, pairGroup);
        final List<Pair> byMission = pairResult.findByMission(mission);

        //then
        assertThat(byMission).isNotEmpty();

    }
}
