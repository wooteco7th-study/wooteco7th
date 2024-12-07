package pairmatching.dto;

import java.util.List;

public class MatchDto {
    private final List<String> matchNames;

    public MatchDto(final List<String> matchNames) {
        this.matchNames = matchNames;
    }

    public List<String> getMatchNames() {
        return matchNames;
    }

}
