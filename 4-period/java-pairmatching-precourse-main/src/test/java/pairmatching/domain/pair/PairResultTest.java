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

class PairResultTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("같은 레벨이면 true를 반환한다.")
    void hasSameLevel(Level level, boolean expected) {
        // Given
        Pair pair1 = new Pair(List.of("러키", "밍트"));
        Pair pair2 = new Pair(List.of("진수", "수달"));
        PairResult pairResult = new PairResult(new PairOrder(Course.백엔드.name(), Level.레벨1.name(), Mission.로또.name()),
                List.of(pair1, pair2));

        // When & Then
        assertThat(pairResult.hasSameLevel(level)).isEqualTo(expected);
    }

    private static Stream<Arguments> hasSameLevel() {
        return Stream.of(
                Arguments.of(Level.레벨1, true),
                Arguments.of(Level.레벨2, false)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("존재하는 페어가 있는지 확인한다.")
    void hasSamePair(Pair pair, boolean expected) {
        // Given
        Pair pair1 = new Pair(List.of("러키", "밍트"));
        Pair pair2 = new Pair(List.of("진수", "수달"));
        PairResult pairResult = new PairResult(new PairOrder(Course.백엔드.name(), Level.레벨1.name(), Mission.로또.name()),
                List.of(pair1, pair2));

        // When & Then
        assertThat(pairResult.hasSamePair(pair)).isEqualTo(expected);
    }

    private static Stream<Arguments> hasSamePair() {
        return Stream.of(
                Arguments.of(new Pair(List.of("러키", "밍트")), true),
                Arguments.of(new Pair(List.of("pack", "고로케")), false)
        );
    }
}
