package subway.domain.vo;


public enum SubwayLine {
    이호선("2호선"),
    삼호선("3호선"),
    신분당선("신분당선");
    private final String value;

    SubwayLine(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
