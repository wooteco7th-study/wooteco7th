package bridge.domain.bridge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import bridge.domain.command.RestartCommand;
import bridge.domain.command.UpDown;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BridgeGameTest {

    @Test
    @DisplayName("지금까지 성공했을 경우 true를 반환한다.")
    void canContinue() {
        // Given
        BridgeGame bridgeGame = makeBridgeGame();

        // When & Then
        assertThat(bridgeGame.canContinue()).isTrue();
    }

    private BridgeGame makeBridgeGame() {
        return new BridgeGame(new ArrayList<>(List.of("U", "D", "D")),
                new BridgeLog(new ArrayList<>(List.of(LogType.PASS)), new ArrayList<>(List.of(LogType.NONE))));
    }

    @Test
    @DisplayName("사용자가 칸을 이동한다.")
    void move() {
        // Given
        BridgeGame bridgeGame = makeBridgeGame();

        // When
        assertThatCode(() -> {
            bridgeGame.move(UpDown.DOWN);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임을 재시도하는지 확인한다.")
    void retry() {
        // Given
        BridgeGame bridgeGame = makeBridgeGame();

        // When & Then
        assertThat(bridgeGame.retry(RestartCommand.RESTART)).isTrue();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("게임이 성공하였는지 판단한다.")
    void isSuccess(List bridge, BridgeLog bridgeLog, boolean isSuccess) {
        // Given
        BridgeGame bridgeGame = new BridgeGame(bridge, bridgeLog);

        // When & Then
        assertThat(bridgeGame.isSuccess()).isEqualTo(isSuccess);
    }

    private static Stream<Arguments> isSuccess() {
        return Stream.of(
                Arguments.of(new ArrayList<>(List.of("U", "D", "D")),
                        new BridgeLog(new ArrayList<>(List.of(LogType.PASS)), new ArrayList<>(List.of(LogType.NONE))),
                        false),
                Arguments.of(new ArrayList<>(List.of("U", "D", "D")),
                        new BridgeLog(new ArrayList<>(List.of(LogType.PASS, LogType.NONE, LogType.NONE)),
                                new ArrayList<>(List.of(LogType.NONE, LogType.PASS, LogType.PASS))),
                        true));
    }

    @Test
    @DisplayName("게임을 초기화한다.")
    void clear() {
        // Given
        BridgeGame bridgeGame = makeBridgeGame();

        // When
        bridgeGame.clear();

        // Then
        assertAll(
                () -> assertThat(bridgeGame.getBridgeLog()).extracting("upBridge", "downBridge")
                        .containsExactly(new ArrayList<>(), new ArrayList<>()),
                () -> assertThat(bridgeGame.getAttempt()).isEqualTo(1)
        );
    }
}
