package pairmatching.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static pairmatching.exception.ExceptionMessage.MATCHING_HISTORY_NOT_FOUND;

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

    public List<Pair> findByMission(final Mission mission) {
        List<Pair> result = pairResult.get(mission);
        validatePairExists(result);
        return result;
    }

    private static void validatePairExists(final List<Pair> result) {
        if (result == null) {
            throw new IllegalArgumentException(MATCHING_HISTORY_NOT_FOUND.getMessage());
        }
    }
}
