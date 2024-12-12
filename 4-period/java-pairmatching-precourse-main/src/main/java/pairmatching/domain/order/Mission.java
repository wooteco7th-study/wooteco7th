package pairmatching.domain.order;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public enum Mission {
    //   - 레벨1: 자동차경주 | 로또 | 숫자야구게임
    //  - 레벨2: 장바구니 | 결제 | 지하철노선도
    //  - 레벨3:
    //  - 레벨4: 성능개선 | 배포
    //  - 레벨5:
    자동차경주(Level.레벨1), 로또(Level.레벨1), 숫자야구게임(Level.레벨1),
    장바구니(Level.레벨2), 결제(Level.레벨2), 지하철노선도(Level.레벨2),
    성능개선(Level.레벨4), 배포(Level.레벨4);

    private final Level level;

    Mission(final Level level) {
        this.level = level;
    }

    public static Mission from(String input) {
        return Arrays.stream(Mission.values())
                .filter(mission -> Objects.equals(mission.name(), input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_LEVEL));
    }
}
