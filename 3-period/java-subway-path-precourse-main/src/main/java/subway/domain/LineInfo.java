package subway.domain;

public class LineInfo {

    private final Station preStation;
    private final Station postStation;
    private final int betweenDistance;
    private final int betweenMinute;

    public LineInfo(final Station preStation, final Station postStation, final int betweenDistance, final int betweenMinute) {
        this.preStation = preStation;
        this.postStation = postStation;
        this.betweenDistance = betweenDistance;
        this.betweenMinute = betweenMinute;
    }
}
