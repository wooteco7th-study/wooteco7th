package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BridgeTest {

    @DisplayName("인덱스에 해당하는 무브 커멘드 일치 테스트")
    @Test
    void isMatchedMoveCommandTest() {
        //given
        final List<MoveCommand> moveCommands = List.of(MoveCommand.UP, MoveCommand.DOWN, MoveCommand.UP);

        //when
        final Bridge bridge = new Bridge(moveCommands);

        //then
        assertAll(
                () -> assertThat(bridge.isMatchedMoveCommand(0, MoveCommand.UP)).isTrue(),
                () -> assertThat(bridge.isMatchedMoveCommand(1, MoveCommand.UP)).isFalse(),
                () -> assertThat(bridge.isMatchedMoveCommand(2, MoveCommand.UP)).isTrue()
        );
    }
}
