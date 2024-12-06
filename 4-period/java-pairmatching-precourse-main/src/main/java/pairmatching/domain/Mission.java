package pairmatching.domain;

import static pairmatching.domain.Level.LEVEL1;
import static pairmatching.domain.Level.LEVEL2;
import static pairmatching.domain.Level.LEVEL4;

public enum Mission {
    RACING_CAR(LEVEL1, "자동차경주"),
    LOTTO(LEVEL1, "로또"),
    BASEBALL(LEVEL1, "숫자야구게임"),

    CART(LEVEL2, "장바구니"),
    PAY(LEVEL2, "결제"),
    SUBWAY(LEVEL2, "지하철노선도"),

    PERFOMANCE_IMPROVEMENT(LEVEL4, "성능개선"),
    DISTRIBUTION(LEVEL4, "배포"),
    ;

    private Level level;
    private String name;

    Mission(final Level level, final String name) {
        this.level = level;
        this.name = name;
    }
}
