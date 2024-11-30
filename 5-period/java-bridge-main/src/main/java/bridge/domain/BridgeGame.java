package bridge.domain;

import bridge.domain.log.BridgeLogGroup;
import bridge.domain.log.BridgeLogType;
import java.util.List;
import java.util.Objects;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private static final int INITIAL_TURN = 0;
    private static final int INITIAL_ATTEMPT = 0;

    private final Bridge bridge;
    private final BridgeLogGroup bridgeLogGroup;
    private int turn;
    private int attempt;

    public BridgeGame(final BridgeLogGroup bridgeLogGroup, final Bridge bridge) {
        this.bridge = bridge;
        this.bridgeLogGroup = bridgeLogGroup;
        turn = INITIAL_TURN;
        attempt = INITIAL_ATTEMPT;
    }

    public void move(final MoveCommand moveCommand) {
        final boolean matchedMoveCommand = bridge.isMatchedMoveCommand(turn++, moveCommand);
        bridgeLogGroup.updateBridgeLogs(matchedMoveCommand, moveCommand);
    }

    public boolean retry(final GameCommand gameCommand) {
        return Objects.equals(gameCommand, GameCommand.RETRY);
    }

    public void addAttempt() {
        attempt++;
    }

    public boolean isClear() {
        return bridgeLogGroup.countTotalPass() == turn;
    }

    public boolean isEnd() {
        return turn == bridge.getSize();
    }

    public boolean hasFail() {
        return bridgeLogGroup.hasFail();
    }

    public List<BridgeLogType> getUpBridgeLogs() {
        return bridgeLogGroup.getUpBridgeLogs();
    }

    public List<BridgeLogType> getDownBridgeLogs() {
        return bridgeLogGroup.getDownBridgeLogs();
    }

    public GameResult getGameResult() {
        if (isClear()) {
            return GameResult.CLEAR;
        }
        return GameResult.FAIL;
    }

    public int getAttempt() {
        return attempt;
    }

    public void clear() {
        turn = INITIAL_TURN;
        bridgeLogGroup.clear();
    }
}
