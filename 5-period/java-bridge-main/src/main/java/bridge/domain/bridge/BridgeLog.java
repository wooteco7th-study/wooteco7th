package bridge.domain.bridge;

import bridge.domain.command.UpDown;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// 사용자가 움직인 결과
public class BridgeLog {

    final List<LogType> upBridge;
    final List<LogType> downBridge;

    public BridgeLog() {
        this.upBridge = new ArrayList<>();
        this.downBridge = new ArrayList<>();
    }

    public void add(final UpDown upDown, boolean isPass) {
        updateUpBridge(upDown, isPass);
        updateDownBridge(upDown, isPass);
    }

    private void updateUpBridge(final UpDown upDown, final boolean isPass) {
        if (upDown == UpDown.UP) {
            upBridge.add(LogType.from(isPass));
            return;
        }
        upBridge.add(LogType.NONE);
    }

    private void updateDownBridge(final UpDown upDown, final boolean isPass) {
        if (upDown == UpDown.DOWN) {
            downBridge.add(LogType.from(isPass));
            return;
        }
        downBridge.add(LogType.NONE);
    }

    public boolean isSuccess(int size) {
        return size == Stream.concat(upBridge.stream(), downBridge.stream())
                .filter(LogType::isRight)
                .count();
    }

    public int getSize() {
        return upBridge.size();
    }

    public boolean isRightEnd() {
        return upBridge.getLast().isRight() || downBridge.getLast().isRight();
    }

    public void clear() {
        upBridge.clear();
        downBridge.clear();
    }

    public List<LogType> getUpBridge() {
        return upBridge;
    }

    public List<LogType> getDownBridge() {
        return downBridge;
    }
}
