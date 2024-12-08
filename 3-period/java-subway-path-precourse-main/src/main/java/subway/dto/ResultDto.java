package subway.dto;

import java.util.List;

public record ResultDto(int totalDistance, int totalTime, List<String> path) {
}
