package pairmatching.domain;

import java.util.List;
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
        return crewNames.stream()
                .filter(i -> pair.contains(i))
                .count() >= 2;
    }

}
