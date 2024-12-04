package bridge.domain.bridge;

import bridge.domain.RestartCommand;
import bridge.domain.generator.BridgeNumberGenerator;
import bridge.exception.CustomIllegalArgumentException;
import bridge.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final List<String> bridge;
    private final BridgeMaker bridgeMaker;
    private final BridgeNumberGenerator numberGenerator;
    private final List<Result> results;

    public BridgeGame(final int size, final BridgeNumberGenerator numberGenerator) {
        validate(size);
        this.bridgeMaker = new BridgeMaker();
        this.numberGenerator = numberGenerator;
        this.bridge = makeBridge(size);
        this.results = new ArrayList<>();
    }

    private void validate(final int size) {
        if (size < 3 || size > 20) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_BRIDGE_LENGTH);
        }
    }

    private List<String> makeBridge(int size) {
        List<Integer> numbers = IntStream.range(0, size)
                .mapToObj(n -> numberGenerator.generate())
                .toList();
        return bridgeMaker.makeBridge(numbers);
    }

    public boolean canContinue() {
        return isInMiddle() && isRightEnd();
    }

    private boolean isRightEnd() {
        return results.getLast().isRight();
    }

    private boolean isInMiddle() {
        return bridge.size() != results.size();
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(final String direction) {
        int pos = results.size();
        boolean isRight = bridge.get(pos).equals(direction);
        results.add(Result.from(direction, isRight));
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry(RestartCommand restartCommand) {
        return restartCommand.doesContinue();
    }

    public List<Result> getResults() {
        return results;
    }

    public boolean isSuccess() {
        return !isInMiddle() && isRightEnd();
    }

    public void clear() {
        results.clear();
    }
}
