package subway.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LineInfoRepository {

    private static final List<LineInfo> lineInfos = new ArrayList<>();

    public static void addLineInfo(LineInfo lineInfo) {
        lineInfos.add(lineInfo);
    }

    private static LineInfo find(String start, String end) {
        return lineInfos.stream()
                .filter(lineInfo -> lineInfo.getPreStation().equals(start))
                .filter(lineInfo -> lineInfo.getPostStation().equals(end))
                .collect(Collectors.toList()).get(0);
    }

    public static int findDistance(String start, String end) {
        return find(start, end).getBetweenDistance();
    }

    public static int findMinute(String start, String end) {
        return find(start, end).getBetweenMinute();
    }
}
