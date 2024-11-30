package bridge.domain;

import java.util.ArrayList;
import java.util.List;

import static bridge.domain.GameCommand.DOWN;
import static bridge.domain.GameCommand.UP;

public class CurrentMap {
    private static final String RIGHT_MARK = "O";
    private static final String WRONG_MARK = "X";
    private static final String BLANK = " ";

    private final List<String> upMap = new ArrayList<>();
    private final List<String> downMap = new ArrayList<>();

    public void addMap(String moving, boolean isSame) {
        if (isSame) {
            putRightMark(moving);
        }
        if (!isSame) {
            putWrongMark(moving);
        }
    }

    public void clearMap() {
        upMap.clear();
        downMap.clear();
    }

    public List<String> getUpMap() {
        return upMap;
    }

    public List<String> getDownMap() {
        return downMap;
    }

    private void putWrongMark(final String moving) {
        if (moving.equals(UP.getCommand())) {
            upMap.add(WRONG_MARK);
            downMap.add(BLANK);
        }
        if (moving.equals(DOWN.getCommand())) {
            upMap.add(BLANK);
            downMap.add(WRONG_MARK);
        }
    }

    private void putRightMark(final String moving) {
        if (moving.equals(UP.getCommand())) {
            upMap.add(RIGHT_MARK);
            downMap.add(BLANK);
        }
        if (moving.equals(DOWN.getCommand())) {
            upMap.add(BLANK);
            downMap.add(RIGHT_MARK);
        }
    }
}
