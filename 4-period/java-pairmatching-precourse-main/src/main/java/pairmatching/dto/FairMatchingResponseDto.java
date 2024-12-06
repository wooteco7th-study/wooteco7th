package pairmatching.dto;

import java.util.List;

public class FairMatchingResponseDto {
    private final List<MatchDto> matchResult;

    public FairMatchingResponseDto(final List<MatchDto> matchResult) {
        this.matchResult = matchResult;
    }

    public List<MatchDto> getMatchResult() {
        return matchResult;
    }
}

