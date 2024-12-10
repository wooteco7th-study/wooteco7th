package pairmatching.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pairmatching.domain.vo.Level;

public class Team {
    private final Level level;
    private final List<String> crewNames;

    public Team(final Level level, final List<String> crewNames) {
        this.level = level;
        this.crewNames = crewNames;
    }

    public Level getLevel() {
        return level;
    }

    public List<String> getCrewNames() {
        return crewNames;
    }

    public boolean hasDuplicatePair(List<String> pair) {
        Set<String> existingSet = new HashSet<>(this.crewNames);
        Set<String> newPairSet = new HashSet<>(pair);
        return existingSet.equals(newPairSet);
    }

}
