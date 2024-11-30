package bridge.domain.log;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import bridge.domain.MoveCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DownBridgeLogTest {

    @DisplayName("다리 건너기를 성공 한다.")
    @Test
    void pass_updateLogTest() {
        //given
        final boolean isPassed = true;
        final MoveCommand moveCommand = MoveCommand.DOWN;

        //when
        final DownBridgeLog downBridgeLog = new DownBridgeLog();
        downBridgeLog.updateLog(moveCommand, isPassed);

        //then
        assertThat(downBridgeLog.getValues().getFirst()).isEqualTo(BridgeLogType.PASS);
    }

    @Test
    @DisplayName("다리 건너기를 실패 한다.")
    void fail_updateLogTest() throws Exception {
        //given
        final boolean isPassed = false;
        final MoveCommand moveCommand = MoveCommand.DOWN;

        //when
        final DownBridgeLog downBridgeLog = new DownBridgeLog();
        downBridgeLog.updateLog(moveCommand, isPassed);

        //then
        assertThat(downBridgeLog.getValues().getFirst()).isEqualTo(BridgeLogType.FAIL);
    }

    @Test
    @DisplayName("해당 다리를 건너지 않는다.")
    void none_updateLogTest() throws Exception {
        //given
        final boolean isPassed = false;
        final MoveCommand moveCommand = MoveCommand.UP;

        //when
        final DownBridgeLog downBridgeLog = new DownBridgeLog();
        downBridgeLog.updateLog(moveCommand, isPassed);

        //then
        assertThat(downBridgeLog.getValues().getFirst()).isEqualTo(BridgeLogType.NONE);
    }
}
