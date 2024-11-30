package bridge.domain.log;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import bridge.domain.MoveCommand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BridgeLogGroupTest {

    @DisplayName("다리 건너기 성공 횟수 테스트")
    @Test
    void countTotalPassTest() {
        //given
        //when
        final BridgeLogGroup bridgeLogGroup = new BridgeLogGroup(new UpBridgeLog(), new DownBridgeLog());
        bridgeLogGroup.updateBridgeLogs(false, MoveCommand.UP);
        bridgeLogGroup.updateBridgeLogs(false, MoveCommand.UP);
        bridgeLogGroup.updateBridgeLogs(true, MoveCommand.UP);

        //then
        assertThat(bridgeLogGroup.countTotalPass()).isEqualTo(1);
    }

    @DisplayName("업데이트 로그 테스트")
    @Test
    void updateLogTest() {
        //given
        //when
        final BridgeLogGroup bridgeLogGroup = new BridgeLogGroup(new UpBridgeLog(), new DownBridgeLog());
        bridgeLogGroup.updateBridgeLogs(false, MoveCommand.UP);
        bridgeLogGroup.updateBridgeLogs(false, MoveCommand.UP);
        bridgeLogGroup.updateBridgeLogs(true, MoveCommand.UP);

        //then
        assertAll(
                () -> assertThat(bridgeLogGroup.getUpBridgeLogs()).isEqualTo(
                        List.of(BridgeLogType.FAIL, BridgeLogType.FAIL, BridgeLogType.PASS)),
                () -> assertThat(bridgeLogGroup.getDownBridgeLogs()).isEqualTo(
                        List.of(BridgeLogType.NONE, BridgeLogType.NONE, BridgeLogType.NONE)
                )
        );
    }
}
