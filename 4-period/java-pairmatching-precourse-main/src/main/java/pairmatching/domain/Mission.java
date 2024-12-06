package pairmatching.domain;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.error.AppException;
import pairmatching.error.ErrorMessage;

public enum Mission {

    /**
     * ## 미션 ### 레벨1 - 자동차경주 - 로또 - 숫자야구게임
     * <p>
     * ### 레벨2 - 장바구니 - 결제 - 지하철노선도
     * <p>
     * ### 레벨3(없음)
     * <p>
     * ### 레벨4 - 성능개선 - 배포
     */
    RACING_CAR(Level.LEVEL_1, "자동차경주"),
    LOTTO(Level.LEVEL_1, "로또"),
    BASEBALL_GAME(Level.LEVEL_1, "숫자야구게임"),
    SHOPPING_CART(Level.LEVEL_2, "장바구니"),
    PAYMENT(Level.LEVEL_2, "결제"),
    SUBWAY(Level.LEVEL_2, "지하철노선도"),
    REFACTORING(Level.LEVEL_4, "성능개선"),
    DEPLOY(Level.LEVEL_4, "배포");


    private final Level level;
    private final String name;

    Mission(final Level level, final String name) {
        this.level = level;
        this.name = name;
    }

    public static Mission findByLevelAndName(final Level level, final String name) {
        return Arrays.stream(Mission.values())
                .filter(mission -> Objects.equals(mission.level, level))
                .filter(mission -> Objects.equals(mission.name, name))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_MISSION));
    }

    public Level getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
