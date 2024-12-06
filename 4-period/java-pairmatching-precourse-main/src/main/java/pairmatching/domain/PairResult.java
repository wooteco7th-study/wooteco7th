package pairmatching.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PairResult {
    private final Map<Mission, List<Pair>> pairResult;

    public PairResult() {
        this.pairResult = new LinkedHashMap<>();
    }

    public void add(final Mission mission, final List<Pair> pair) {
        pairResult.put(mission, pair);
    }

    public List<Pair> resultOfLevel(Level level) {
        return pairResult.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isSameLevel(level))
                .flatMap(entry -> entry.getValue().stream())
                .toList();
    }
}
