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

    public String getPreStation() {
        return preStation.getName();
    }

    public String getPostStation() {
        return postStation.getName();
    }

    public int getBetweenDistance() {
        return betweenDistance;
    }

    public int getBetweenMinute() {
        return betweenMinute;
    }
}
