package menu.dto;

import java.util.List;
import java.util.Map;

public class RecommendDto {
    private final Map<String, List<String>> recommendResult;

    public RecommendDto(final Map<String, List<String>> recommendResult) {
        this.recommendResult = recommendResult;
    }

    public Map<String, List<String>> getRecommendResult() {
        return recommendResult;
    }
}
