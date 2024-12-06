package pairmatching.domain.pair;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static pairmatching.support.CustomAssert.assertIllegalArgument;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.order.Course;
import pairmatching.domain.order.Level;
import pairmatching.domain.order.Mission;
import pairmatching.domain.order.PairOrder;
import pairmatching.domain.random.Shuffle;
import pairmatching.exception.ErrorMessage;

class PairMatcherTest {

    @Test
    @DisplayName("크루들을 2명씩 매칭한다.")
    void matchCrewUntilCount() {
        // Given
        List<Crew> crews = makeCrews();
        Shuffle shuffle = tokens -> List.of("러키", "밍트", "진수", "수달");
        PairMatcher pairMatcher = new PairMatcher(crews, shuffle);

        // When
        List<Pair> pairs = pairMatcher.matchCrewUntilCount(new PairHistory(new ArrayList<>()), Level.레벨1);

        // Then
        assertAll(
                () -> assertThat(pairs.get(0).getCrews()).containsExactly("러키", "밍트"),
                () -> assertThat(pairs.get(1).getCrews()).containsExactly("진수", "수달")
        );
    }

    @Test
    @DisplayName("크루가 홀수일 경우 마지막 그룹에 3명이 페어가 된다.")
    void 크루가_홀수일_경우_마지막_그룹에_3_명이_페어가_된다() {
        // Given
        List<Crew> crews = makeCrews();
        crews.add(new Crew(Course.백엔드, "가나디"));
        Shuffle shuffle = tokens -> List.of("러키", "밍트", "진수", "수달", "가나디");
        PairMatcher pairMatcher = new PairMatcher(crews, shuffle);

        // When
        List<Pair> pairs = pairMatcher.matchCrewUntilCount(new PairHistory(new ArrayList<>()), Level.레벨1);

        // Then
        assertAll(
                () -> assertThat(pairs.get(0).getCrews()).containsExactly("러키", "밍트"),
                () -> assertThat(pairs.get(1).getCrews()).containsExactly("진수", "수달", "가나디")
        );
    }

    @Test
    @DisplayName("페어가 이미 존재하여 4번 이상의 매칭을 반복할 경우 예외를 발생한다.")
    void 네번_이상의_매칭은_예외를_발생한다() {
        // Given
        List<Crew> crews = makeCrews();
        Pair pair1 = new Pair(List.of("러키", "밍트"));
        Pair pair2 = new Pair(List.of("진수", "수달"));
        Shuffle shuffle = tokens -> List.of("러키", "밍트", "진수", "수달");
        PairMatcher pairMatcher = new PairMatcher(crews, shuffle);
        PairResult pairResult = new PairResult(new PairOrder(Course.백엔드.name(), Level.레벨1.name(), Mission.로또.name()),
                List.of(pair1, pair2));

        // When
        assertIllegalArgument(() -> pairMatcher.matchCrewUntilCount(new PairHistory(List.of(pairResult)), Level.레벨1),
                ErrorMessage.PAIR_MATCH_FAILED);
    }

    private List<Crew> makeCrews() {
        List<Crew> crews = new ArrayList<>();
        crews.add(makeBackendCrew("밍트"));
        crews.add(makeBackendCrew("러키"));
        crews.add(makeBackendCrew("수달"));
        crews.add(makeBackendCrew("진수"));
        return crews;
    }

    private Crew makeBackendCrew(final String name) {
        return new Crew(Course.백엔드, name);
    }
}
