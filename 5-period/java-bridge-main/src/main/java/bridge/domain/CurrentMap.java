package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public class CurrentMap {
    private final List<String> upMap = new ArrayList<>();
    private final List<String> downMap = new ArrayList<>();

    public void addMap(String moving, boolean isSame) {
        if (isSame) {
            if (moving.equals("U")) {
                upMap.add("O");
                downMap.add(" ");
            }
            if (moving.equals("D")) {
                upMap.add(" ");
                downMap.add("O");
            }
        }

        if (!isSame) {
            if (moving.equals("U")) {
                upMap.add("X");
                downMap.add(" ");
            }
            if (moving.equals("D")) {
                upMap.add(" ");
                downMap.add("X");
            }
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
}
