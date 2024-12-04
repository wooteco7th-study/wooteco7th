package subway.domain.vo;

public enum SubwayStation {
    교대역("교대역"),
    강남역("강남역"),
    역삼역("역삼역"),
    남부터미널역("남부터미널역"),
    양재역("양재역"),
    양재시민의숲역("양재시민의숲역"),
    매봉역("매봉역");


    private final String value;

    SubwayStation(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
