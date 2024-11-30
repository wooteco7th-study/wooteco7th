package bridge.domain.bridge;

import bridge.domain.Direction;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final Bridge bridge;

    public BridgeGame(final Bridge bridge) {
        this.bridge = bridge;
    }

    public boolean isNotEnd(final int pos) {
        return !bridge.isEnd(pos);
    }

    public boolean canContinue(final int pos, final boolean isRightMove) {
        return !bridge.isEnd(pos) && isRightMove;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(final Direction direction, final int pos) {
        return bridge.getDirection(pos).equals(direction);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }
}
