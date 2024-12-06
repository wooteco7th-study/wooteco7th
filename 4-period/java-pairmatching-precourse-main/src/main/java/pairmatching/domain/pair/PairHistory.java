package pairmatching.domain.pair;

import java.util.List;
import pairmatching.domain.Level;

public class PairHistory {

    private final List<PairResult> results;

    public PairHistory(final List<PairResult> results) {
        this.results = results;
    }

    public void add(final PairResult pairResult) {
        results.add(pairResult);
    }

    public boolean isExists(final PairOrder pairOrder) {
        // 이미 존재할 경우
        return false;
    }

    public boolean hasSameLevelPair(final Pair pair, final Level level) {
        // 같은 레벨의 PairResult 찾기
        return results.stream()
                .filter(result -> result.hasSameLevel(level))
                .anyMatch(result -> result.hasSamePair(pair));
    }


}
