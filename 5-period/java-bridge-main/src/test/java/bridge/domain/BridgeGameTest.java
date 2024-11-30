package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.domain.bridgeLog.BridgeLogGroup;
import bridge.domain.bridgeLog.BridgeLogsGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BridgeGameTest {

    @Test
    @DisplayName("다리길이가 3, 모두 위 다리로 이동 가능 할때 turn 3회 모두 위 다리로 이동하여 게임이 끝난다.")
    void true_endTest() throws Exception {
        //given
        final Bridge bridge = new Bridge(List.of(MoveCommand.UP, MoveCommand.UP, MoveCommand.UP));
        final BridgeLogGroup bridgeLogGroup = BridgeLogsGenerator.generate();

        //when
        final BridgeGame bridgeGame = new BridgeGame(bridgeLogGroup, bridge);
        bridgeGame.move(MoveCommand.UP);
        bridgeGame.move(MoveCommand.UP);
        bridgeGame.move(MoveCommand.UP);

        //then
        assertThat(bridgeGame.isClear()).isTrue();

    }

    @Test
    @DisplayName("다리길이가 3, 모두 위 다리로 이동 가능 할때 turn 3회 모두 위 다리로 이동하지 않아 게임이 끝나지 않는다.")
    void false_endTest() throws Exception {
        //given
        final Bridge bridge = new Bridge(List.of(MoveCommand.UP, MoveCommand.UP, MoveCommand.UP));
        final BridgeLogGroup bridgeLogGroup = BridgeLogsGenerator.generate();

        //when
        final BridgeGame bridgeGame = new BridgeGame(bridgeLogGroup, bridge);
        bridgeGame.move(MoveCommand.DOWN);
        bridgeGame.move(MoveCommand.UP);
        bridgeGame.move(MoveCommand.DOWN);

        //then
        assertThat(bridgeGame.isClear()).isFalse();

    }
}
