package bridge.domain.bridge;

import bridge.domain.command.RestartCommand;
import bridge.domain.command.UpDown;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final List<String> bridge; // 다리 길이
    private final BridgeLog bridgeLog; // 다리를 사용자가 움직인 기록
    private int attempt;

    public BridgeGame(final List<String> bridge, final BridgeLog bridgeLog) {
        this.bridge = bridge;
        this.bridgeLog = bridgeLog;
        this.attempt = 0;
    }

    public boolean canContinue() {
        return isInMiddle() && isRightEnd();
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(final UpDown direction) {
        int pos = bridgeLog.getSize();
        boolean isRight = bridge.get(pos).equals(direction.getDirection());
        bridgeLog.add(direction, isRight);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry(RestartCommand restartCommand) {
        return restartCommand.doesContinue();
    }

    public boolean isSuccess() {
        return bridgeLog.isSuccess(bridge.size());
    }

    public void clear() {
        bridgeLog.clear();
        attempt++;
    }

    private boolean isRightEnd() {
        return bridgeLog.isRightEnd();
    }

    private boolean isInMiddle() {
        return bridge.size() != bridgeLog.getSize();
    }

    public int getAttempt() {
        return attempt;
    }

    public BridgeLog getBridgeLog() {
        return bridgeLog;
    }
}
