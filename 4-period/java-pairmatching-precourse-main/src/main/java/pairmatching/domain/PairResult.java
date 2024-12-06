package pairmatching.domain;

import java.util.List;

public record PairResult(
        List<Pair> pairResult
) {
    @Override
    public String toString() {
        return "PairResult{" +
                "pairResult=" + pairResult +
                '}';
    }
}
