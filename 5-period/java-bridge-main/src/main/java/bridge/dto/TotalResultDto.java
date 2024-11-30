package bridge.dto;

import java.util.List;

public record TotalResultDto(int pos, List<MoveResultDto> finalResult, boolean isSuccess) {
}
