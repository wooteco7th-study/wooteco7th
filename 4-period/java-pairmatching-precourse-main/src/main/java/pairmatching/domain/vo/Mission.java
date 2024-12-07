package pairmatching.domain.vo;

import static pairmatching.domain.vo.Level.LEVEL1;
import static pairmatching.domain.vo.Level.LEVEL2;
import static pairmatching.domain.vo.Level.LEVEL4;

import java.util.Arrays;
import pairmatching.constant.ExceptionMessage;

/*
 - 레벨1: 자동차경주 | 로또 | 숫자야구게임
  - 레벨2: 장바구니 | 결제 | 지하철노선도
  - 레벨3:
  - 레벨4: 성능개선 | 배포
  - 레벨5:
 */
public enum Mission {
    RACING_CAR("자동차경주", LEVEL1),
    LOTTO("로또", LEVEL1),
    NUMBER_BASEBALL_GAME("숫자야구게임", LEVEL1),
    SHOPPING_CART("장바구니", LEVEL2),
    PAYMENT("결제", LEVEL2),
    SUBWAY_MAP("지하철노선도", LEVEL2),
    PERFORMANCE_IMPROVEMENT("성능개선", LEVEL4),
    DISTRIBUTION("배포", LEVEL4);

    private final String displayName;
    private final Level level;

    Mission(final String displayName, final Level level) {
        this.displayName = displayName;
        this.level = level;
    }

    public static Mission toMission(String value) {
        return Arrays.stream(Mission.values())
                .filter(option -> option.getDisplayName().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_INPUT_FORM.getMessage()));
    }

    public String getDisplayName() {
        return displayName;
    }

    public Level getLevel() {
        return level;
    }
}
