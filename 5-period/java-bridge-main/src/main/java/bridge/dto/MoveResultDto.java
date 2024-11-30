package bridge.dto;

import bridge.domain.Direction;

public record MoveResultDto(boolean isRightMove, char direction) {
    public static MoveResultDto of(boolean isRightMove, Direction direction) {
        return new MoveResultDto(isRightMove, direction.getDirection());
    }
}
