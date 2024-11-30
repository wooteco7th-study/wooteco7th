package bridge.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final List<String> bridgeAnswer;
    private final List<String> bridgeInput = new ArrayList<>();

    public BridgeGame(final List<String> bridgeAnswer) {
        this.bridgeAnswer = bridgeAnswer;
    }

    public boolean keepGame() {
        return bridgeInput.size() < bridgeAnswer.size();
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(String moving) {
        bridgeInput.add(moving);
    }

    public boolean compare() {
        int index = bridgeInput.size() - 1;
        return bridgeInput.get(index).equals(bridgeAnswer.get(index));
    }

    public List<String> getBridgeInput() {
        return bridgeInput;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }
}
