package pairmatching.domain;

import java.util.List;
import java.util.Objects;
import pairmatching.error.AppException;
import pairmatching.error.ErrorMessage;

public class PairSystem {

    private static final int MAX_MATCH_COUNT = 3;
    private final PairResult pairResult;

    public PairSystem(final PairResult pairResult) {
        this.pairResult = pairResult;
    }

    public List<Pair> match(final Mission mission) {
        int count = 0;
        while (count <= MAX_MATCH_COUNT) {
            final PairGroup pairGroup = PairGroupGenerator.generate(mission.getCourseType());
            if (!pairResult.hasDuplicateLevelCrews(mission,pairGroup.getPairs())) {
                pairResult.updateResult(mission, pairGroup);
                return pairGroup.getPairs();
            }
            count++;
        }
        throw new AppException(ErrorMessage.INVALID_FAIL_MATCH);
    }

    public List<Pair> lookUp(final Mission mission) {
        return pairResult.findByMission(mission);
    }

    public List<Pair> rematch(final Mission mission) {
        pairResult.remove(mission);
        return match(mission);
    }

    public boolean hasMatchResult(final Mission mission) {
        return pairResult.hasPairGroup(mission);
    }

    public void clear() {
        pairResult.clear();
    }

    public boolean isQuit(final PairCommand pairCommand) {
        return Objects.equals(pairCommand, PairCommand.QUIT);
    }
}
