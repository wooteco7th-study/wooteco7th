package subway.domain.edge;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import subway.domain.line.LineType;
import subway.domain.station.StationType;

public enum EdgeInfo {

    INFO_1(LineType.TWO_LINE, StationType.GYODAE, StationType.GANGNAM, 2, 3),
    INFO_2(LineType.TWO_LINE, StationType.GANGNAM, StationType.YEOKSAM, 2, 3),
    INFO_3(LineType.TREE_LINE, StationType.GYODAE, StationType.NAMBU_TERMINAL, 3, 2),
    INFO_4(LineType.TREE_LINE, StationType.NAMBU_TERMINAL, StationType.YANGJAE, 6, 5),
    INFO_5(LineType.TREE_LINE, StationType.YANGJAE, StationType.MAEBONG, 1, 1),
    INFO_6(LineType.SHINBUNDANG_LINE, StationType.GANGNAM, StationType.YANGJAE, 2, 8),
    INFO_7(LineType.SHINBUNDANG_LINE, StationType.YANGJAE, StationType.YANGJAE_CITIZEN_FOREST, 10, 3);


    private LineType lineType;
    private StationType start;
    private StationType end;
    private int distance;
    private int time;

    EdgeInfo(final LineType lineType, final StationType start, final StationType end, final int distance,
             final int time) {
        this.lineType = lineType;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.time = time;
    }

    public static List<EdgeInfo> findAll() {
        return Arrays.stream(EdgeInfo.values())
                .collect(Collectors.toList());
    }

    public LineType getLineType() {
        return lineType;
    }

    public StationType getStart() {
        return start;
    }

    public StationType getEnd() {
        return end;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
