package pairmatching.domain;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.error.AppException;
import pairmatching.error.ErrorMessage;

public enum MissionType {

    /**
     * ## 미션 ### 레벨1 - 자동차경주 - 로또 - 숫자야구게임
     * <p>
     * ### 레벨2 - 장바구니 - 결제 - 지하철노선도
     * <p>
     * ### 레벨3(없음)
     * <p>
     * ### 레벨4 - 성능개선 - 배포
     */
    RACING_CAR(LevelType.LEVEL_1, "자동차경주"),
    LOTTO(LevelType.LEVEL_1, "로또"),
    BASEBALL_GAME(LevelType.LEVEL_1, "숫자야구게임"),
    SHOPPING_CART(LevelType.LEVEL_2, "장바구니"),
    PAYMENT(LevelType.LEVEL_2, "결제"),
    SUBWAY(LevelType.LEVEL_2, "지하철노선도"),
    REFACTORING(LevelType.LEVEL_4, "성능개선"),
    DEPLOY(LevelType.LEVEL_4, "배포");


    private final LevelType levelType;
    private final String name;

    MissionType(final LevelType levelType, final String name) {
        this.levelType = levelType;
        this.name = name;
    }

    public static MissionType findByLevelAndName(final LevelType levelType, final String name) {
        return Arrays.stream(MissionType.values())
                .filter(missionType -> Objects.equals(missionType.levelType, levelType))
                .filter(missionType -> Objects.equals(missionType.name, name))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_MISSION));
    }

    public LevelType getLevel() {
        return levelType;
    }

    public String getName() {
        return name;
    }
}
