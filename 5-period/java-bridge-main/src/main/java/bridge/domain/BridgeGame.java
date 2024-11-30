package bridge.domain;

import java.util.List;
import java.util.Objects;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private static final int INITIAL_TURN = 0;
    private static final int INITIAL_ATTEMPT = 0;

    private final Bridge bridge;
    private final UpBridgeLog upBridgeLog;
    private final DownBridgeLog downBridgeLog;
    private int turn;
    private int attempt;

    public BridgeGame(final DownBridgeLog downBridgeLog, final UpBridgeLog upBridgeLog, final Bridge bridge) {
        this.downBridgeLog = downBridgeLog;
        this.upBridgeLog = upBridgeLog;
        this.bridge = bridge;
        turn = INITIAL_TURN;
        attempt = INITIAL_ATTEMPT;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(final MoveCommand moveCommand) {
        final boolean matchedMoveCommand = bridge.isMatchedMoveCommand(turn++, moveCommand);
        upBridgeLog.updateLog(moveCommand, matchedMoveCommand);
        downBridgeLog.updateLog(moveCommand, matchedMoveCommand);
    }

    public List<BridgeLogType> getUpBridgeLogs() {
        return upBridgeLog.getValues();
    }

    public List<BridgeLogType> getDownBridgeLogs() {
        return downBridgeLog.getValues();
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry(final GameCommand gameCommand) {
        attempt++;
        return Objects.equals(gameCommand, GameCommand.RETRY);
    }

    public GameResult getGameResult() {
        if (isClear()) {
            return GameResult.CLEAR;
        }
        return GameResult.FAIL;
    }

    public boolean isClear() {
        return upBridgeLog.countPass() + downBridgeLog.countPass() == turn;
    }

    public boolean isEnd() {
        return turn == bridge.getSize();
    }
}
