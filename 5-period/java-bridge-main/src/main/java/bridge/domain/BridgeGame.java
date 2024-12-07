package bridge.domain;

import java.util.ArrayList;
import java.util.List;

import static bridge.domain.BridgeCommand.from;
import static bridge.domain.GameResult.FAIL;
import static bridge.domain.GameResult.SUCCESS;
import static bridge.domain.RetryCommand.RESTART;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final List<String> bridgeAnswer;
    private final List<String> bridgeInput = new ArrayList<>();
    private int totalTrialCount = 1;
    private GameResult gameResult = SUCCESS;

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
        BridgeCommand bridgeCommand = from(moving);
        bridgeInput.add(bridgeCommand.getCommand());
    }

    public boolean compare() {
        int index = bridgeInput.size() - 1;
        return bridgeInput.get(index).equals(bridgeAnswer.get(index));
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry(final String retryOrNot) {
        RetryCommand retryCommand = RetryCommand.from(retryOrNot);
        if (retryCommand == RESTART) {
            totalTrialCount++;
            bridgeInput.clear();
            return true;
        }
        gameResult = FAIL;
        return false;
    }

    public int getTotalTrialCount() {
        return totalTrialCount;
    }

    public String getResult() {
        return gameResult.getDescription();
    }
}
