package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private List<LineInfo> lineInfos;

    public Line(String name) {
        this.name = name;
        this.lineInfos = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<LineInfo> getLineInfos() {
        return lineInfos;
    }
}
