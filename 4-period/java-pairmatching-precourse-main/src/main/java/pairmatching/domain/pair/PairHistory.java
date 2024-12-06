package pairmatching.domain.pair;

import java.util.List;

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
}
