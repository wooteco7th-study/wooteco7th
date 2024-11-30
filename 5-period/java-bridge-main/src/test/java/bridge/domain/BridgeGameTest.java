package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BridgeGameTest {
    
    @Test
    @DisplayName("다리길이가 3, 모두 위 다리로 이동 가능 할때 turn 3회 모두 위 다리로 이동하여 게임이 끝난다.")
    void true_endTest() throws Exception {
        //given
        final Bridge bridge = new Bridge(List.of(MoveCommand.UP, MoveCommand.UP, MoveCommand.UP));
        final UpBridgeLog upBridgeLog = new UpBridgeLog();
        final DownBridgeLog downBridgeLog = new DownBridgeLog();

        //when
        final BridgeGame bridgeGame = new BridgeGame(downBridgeLog, upBridgeLog, bridge);
        bridgeGame.move(MoveCommand.UP);
        bridgeGame.move(MoveCommand.UP);
        bridgeGame.move(MoveCommand.UP);

        //then
        assertThat(bridgeGame.isEnd()).isTrue();
        
    }
    @Test
    @DisplayName("다리길이가 3, 모두 위 다리로 이동 가능 할때 turn 3회 모두 위 다리로 이동하지 않아 게임이 끝나지 않는다.")
    void false_endTest() throws Exception {
        //given
        final Bridge bridge = new Bridge(List.of(MoveCommand.UP, MoveCommand.UP, MoveCommand.UP));
        final UpBridgeLog upBridgeLog = new UpBridgeLog();
        final DownBridgeLog downBridgeLog = new DownBridgeLog();

        //when
        final BridgeGame bridgeGame = new BridgeGame(downBridgeLog, upBridgeLog, bridge);
        bridgeGame.move(MoveCommand.DOWN);
        bridgeGame.move(MoveCommand.UP);
        bridgeGame.move(MoveCommand.DOWN);

        //then
        assertThat(bridgeGame.isEnd()).isFalse();

    }
}
