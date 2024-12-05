package bridge.domain.bridge;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import bridge.domain.command.UpDown;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BridgeLogTest {

    @Test
    @DisplayName("원소를 추가한다.")
    void add() {
        // Given
        BridgeLog bridgeLog = new BridgeLog(new ArrayList<>(List.of(LogType.PASS)),
                new ArrayList<>(List.of(LogType.NONE)));

        // When
        bridgeLog.add(UpDown.UP, true);

        // Then
        assertAll(
                () -> assertThat(bridgeLog.upBridge).isEqualTo(List.of(LogType.PASS, LogType.PASS)),
                () -> assertThat(bridgeLog.downBridge).isEqualTo(List.of(LogType.NONE, LogType.NONE))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("성공 여부를 판단한다.")
    void isSuccess(final List<LogType> upBridge, final List<LogType> downBridge, boolean isSuccess) {
        // Given
        BridgeLog bridgeLog = new BridgeLog(upBridge, downBridge);

        // When & Then
        assertThat(bridgeLog.isSuccess(3)).isEqualTo(isSuccess);
    }

    private static Stream<Arguments> isSuccess() {
        return Stream.of(
                Arguments.of(new ArrayList<>(List.of(LogType.PASS)),
                        new ArrayList<>(List.of(LogType.NONE)), false),
                Arguments.of(new ArrayList<>(List.of(LogType.PASS, LogType.PASS, LogType.PASS)),
                        new ArrayList<>(List.of(LogType.NONE, LogType.NONE, LogType.NONE)), true)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("마지막에 성공으로 끝나면 true를 반환한다.")
    void isRightEnd(final List<LogType> upBridge, final List<LogType> downBridge, boolean isSuccess) {
        // Given
        BridgeLog bridgeLog = new BridgeLog(upBridge, downBridge);

        // When & Then
        assertThat(bridgeLog.isRightEnd()).isEqualTo(isSuccess);
    }

    private static Stream<Arguments> isRightEnd() {
        return Stream.of(
                Arguments.of(new ArrayList<>(List.of(LogType.PASS)),
                        new ArrayList<>(List.of(LogType.NONE)), true),
                Arguments.of(new ArrayList<>(List.of(LogType.PASS, LogType.PASS, LogType.FAIL)),
                        new ArrayList<>(List.of(LogType.NONE, LogType.NONE, LogType.NONE)), false)
        );
    }

    @Test
    @DisplayName("upBridge와 downBridge를 초기화한다.")
    void clear() {
        // Given
        BridgeLog bridgeLog = new BridgeLog(new ArrayList<>(List.of(LogType.PASS)),
                new ArrayList<>(List.of(LogType.NONE)));

        // When
        bridgeLog.clear();

        // Then
        assertAll(
                () -> assertThat(bridgeLog.upBridge).isEqualTo(new ArrayList<>()),
                () -> assertThat(bridgeLog.downBridge).isEqualTo(new ArrayList<>())
        );
    }
}
