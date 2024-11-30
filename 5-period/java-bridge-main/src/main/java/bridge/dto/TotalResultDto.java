package bridge.dto;

import java.util.List;

public record TotalResultDto(int pos, List<MoveResultDto> finalResult, boolean isSuccess) {
    public static TotalResultDto from(int pos, List<MoveResultDto> finalResult) {
        return new TotalResultDto(pos, finalResult, finalResult.getLast().isRightMove());
    }
}
