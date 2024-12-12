package oncall.domain.turn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import oncall.domain.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {

    @Test
    @DisplayName("다음 이름을 조회한다.")
    void getNextName() {
        // Given
        Turn weekdayTurn = makeTurn(List.of("a", "b", "c", "d", "e"));

        // When & Then
        assertAll(
                () -> assertThat(weekdayTurn.calculateNextName("")).isEqualTo(new Name("a")),
                () -> assertThat(weekdayTurn.calculateNextName("a")).isEqualTo(new Name("b"))
        );
    }

    @Test
    @DisplayName("바로 직전 이름과 동일한 경우 다음 이름을 조회한다.")
    void 바로_직전_이름과_동일한_경우_다음_이름을_조회() {
        // Given
        Turn weekdayTurn = makeTurn(List.of("a", "b", "c", "d", "e"));

        // When & Then
        assertAll(
                () -> assertThat(weekdayTurn.calculateNextName("a")).isEqualTo(new Name("b")),
                () -> assertThat(weekdayTurn.calculateNextName("b")).isEqualTo(new Name("a"))
        );
    }

    @Test
    @DisplayName("순서를 변경하더라도 순번은 유지되는지 확인한다.")
    void 순서를_변경하더라도_순번은_유지되는지_확인() {
        // Given
        Turn weekdayTurn = makeTurn(List.of("a", "b", "c", "d", "e"));

        // When & Then
        assertAll(
                () -> assertThat(weekdayTurn.calculateNextName("a")).isEqualTo(new Name("b")),
                () -> assertThat(weekdayTurn.calculateNextName("b")).isEqualTo(new Name("a")),
                () -> assertThat(weekdayTurn.calculateNextName("a")).isEqualTo(new Name("c")),
                () -> assertThat(weekdayTurn.calculateNextName("c")).isEqualTo(new Name("d")),
                () -> assertThat(weekdayTurn.calculateNextName("d")).isEqualTo(new Name("e")),

                () -> assertThat(weekdayTurn.calculateNextName("e")).isEqualTo(new Name("a")),
                () -> assertThat(weekdayTurn.calculateNextName("a")).isEqualTo(new Name("b")),
                () -> assertThat(weekdayTurn.calculateNextName("b")).isEqualTo(new Name("c"))
        );
    }

    private Turn makeTurn(List<String> input) {
        return new Turn(input);
    }
}
