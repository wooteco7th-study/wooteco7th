package pairmatching.domain;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import pairmatching.error.AppException;
import pairmatching.error.ErrorMessage;

public class PairResult {

    private final Map<Mission, PairGroup> values;

    public PairResult(final Map<Mission, PairGroup> values) {
        this.values = values;
    }

    public void updateResult(final Mission mission, final PairGroup pairGroup) {
        values.put(mission, pairGroup);
    }

    public boolean hasPairGroup(final Mission mission) {
        return values.containsKey(mission);
    }

    public boolean hasDuplicateLevelCrews(final Mission mission, final List<Pair> pairs) {
        return getPairGroupsByLevelType(mission.getLevelType()).stream()
                .anyMatch(pairGroup -> pairGroup.hasPair(pairs));
    }

    public List<Pair> findByMission(final Mission mission) {
        return values.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getKey(), mission))
                .findAny()
                .map(entry -> entry.getValue().getPairs())
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_NOT_FOUND_PAIR_RESULT));
    }

    public void clear() {
        values.clear();
    }

    private List<PairGroup> getPairGroupsByLevelType(final LevelType levelType) {
        return values.entrySet().stream()
                .filter(entry -> entry.getKey().isSameLevelType(levelType))
                .map(Entry::getValue)
                .toList();
    }
}
