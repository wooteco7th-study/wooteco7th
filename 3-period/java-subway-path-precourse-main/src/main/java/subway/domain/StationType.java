package subway.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum StationType {

    GANGNAM("강남역"),
    GYODAE("교대역"),
    NAMBU_TERMINAL("남부터미널역"),
    YANGJAE("양재역"),
    MAEBONG("매봉역"),
    YEOKSAM("역삼역"),
    YANGJAE_CITIZEN_FOREST("양재시민의숲역");

    private final String name;

    StationType(final String name) {
        this.name = name;
    }

    public static List<String> findAll() {
        return Arrays.stream(StationType.values())
                .map(stationType -> stationType.name)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }
}
