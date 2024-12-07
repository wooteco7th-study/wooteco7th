package pairmatching.domain.pair;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pairmatching.domain.order.Course;
import pairmatching.domain.order.Level;
import pairmatching.domain.order.Mission;
import pairmatching.domain.order.PairOrder;

class PairHistoryTest {

    @Test
    void add() {
    }

    @Test
    void findSamePairOrderResult() {
    }

    @Test
    void isExists() {
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("같은 레벨에서 Pair가 존재할 경우 true가 반환된다.")
    void hasSameLevelPair(Pair pair, boolean expected) {
        // Given
        Pair pair1 = new Pair(List.of("러키", "밍트"));
        Pair pair2 = new Pair(List.of("진수", "수달", "혜민"));
        PairResult pairResultLevel1 = new PairResult(
                new PairOrder(Course.백엔드.name(), Level.레벨1.name(), Mission.로또.name()),
                List.of(pair1, pair2));
        Pair pair3 = new Pair(List.of("혜민", "밍트"));
        Pair pair4 = new Pair(List.of("수달", "러키", "혜민"));
        PairResult pairResultLevel2 = new PairResult(
                new PairOrder(Course.백엔드.name(), Level.레벨2.name(), Mission.로또.name()),
                List.of(pair3, pair4));
        PairHistory pairHistory = new PairHistory(List.of(pairResultLevel1, pairResultLevel2));

        // When & Then
        assertThat(pairHistory.hasSameLevelPair(pair, Level.레벨1)).isEqualTo(expected);
    }

    private static Stream<Arguments> hasSameLevelPair() {
        return Stream.of(
                Arguments.of(new Pair(List.of("혜민", "밍트")), false),
                Arguments.of(new Pair(List.of("혜민", "러키")), false),
                Arguments.of(new Pair(List.of("수달", "진수")), true)
        );
    }

    @Test
    void inquire() {
    }

    @Test
    void clear() {
    }
}
