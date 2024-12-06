package pairmatching.domain;

import pairmatching.util.ShuffleGenerator;

import java.util.ArrayList;
import java.util.List;

import static pairmatching.exception.ExceptionMessage.MATCHING_IMPOSSIBLE;

public class PairGenerator {
    private static final int TOTAL_TRIAL_COUNT = 3;

    private final PairHistory pairHistory;

    public PairGenerator(final PairHistory pairHistory) {
        this.pairHistory = pairHistory;
    }

    public List<Pair> generate(Level level, List<String> crewNames) {
        validateLevel(level);
        int totalTrialCount = 1;
        List<Pair> pairResult = new ArrayList<>();
        if (isEven(crewNames)) {
            while (totalTrialCount < TOTAL_TRIAL_COUNT) {
                addResultWhenCountOfCrewEven(crewNames, pairResult);
                if (isDuplicated(pairHistory.resultOfLevel(level), pairResult)) {
                    totalTrialCount += 1;
                    crewNames = ShuffleGenerator.shuffleNames(crewNames);
                    continue;
                }
                return pairResult;
            }
        }
        while (totalTrialCount < TOTAL_TRIAL_COUNT) {
            addResultWhenCountOfCrewOdd(crewNames, pairResult);
            if (isDuplicated(pairHistory.resultOfLevel(level), pairResult)) {
                totalTrialCount += 1;
                crewNames = ShuffleGenerator.shuffleNames(crewNames);
                continue;
            }
            return pairResult;
        }
        throw new IllegalArgumentException(MATCHING_IMPOSSIBLE.getMessage());
    }

    private void validateLevel(final Level level) {
        if (level.isLevel3OrLevel5()) {
            throw new IllegalArgumentException(MATCHING_IMPOSSIBLE.getMessage());
        }
    }

    private boolean isEven(final List<String> crewNames) {
        return crewNames.size() % 2 == 0;
    }

    private void addResultWhenCountOfCrewEven(final List<String> shuffledNames, final List<Pair> pairResult) {
        for (int i = 0; i < shuffledNames.size() - 1; i += 2) {
            List<String> names = new ArrayList<>();
            names.add(shuffledNames.get(i));
            names.add(shuffledNames.get(i + 1));
            Pair pair = new Pair(names);
            pairResult.add(pair);
        }
    }

    private static void addResultWhenCountOfCrewOdd(final List<String> shuffledNames, final List<Pair> pairResult) {
        for (int i = 0; i < shuffledNames.size() - 3; i += 2) {
            List<String> names = new ArrayList<>();
            names.add(shuffledNames.get(i));
            names.add(shuffledNames.get(i + 1));
            Pair pair = new Pair(names);
            pairResult.add(pair);
        }
        List<String> names = new ArrayList<>();
        names.add(shuffledNames.get(shuffledNames.size() - 3));
        names.add(shuffledNames.get(shuffledNames.size() - 2));
        names.add(shuffledNames.get(shuffledNames.size() - 1));
        Pair pair = new Pair(names);
        pairResult.add(pair);
    }

    private boolean isDuplicated(final List<Pair> storage, List<Pair> now) {
        return storage.stream().anyMatch(now::contains);
    }
}
