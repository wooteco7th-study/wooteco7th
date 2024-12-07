package pairmatching.domain;

import pairmatching.util.ShuffleGenerator;

import java.util.ArrayList;
import java.util.Collections;
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
        List<Pair> pairResult = getPairResult(level, crewNames);
        if (pairResult.isEmpty()) {
            throw new IllegalArgumentException(MATCHING_IMPOSSIBLE.getMessage());
        }
        return pairResult;
    }

    private void validateLevel(final Level level) {
        if (level.isLevel3OrLevel5()) {
            throw new IllegalArgumentException(MATCHING_IMPOSSIBLE.getMessage());
        }
    }

    private List<Pair> getPairResult(final Level level, final List<String> crewNames) {
        List<String> shuffleNames = ShuffleGenerator.shuffleNames(crewNames);
        int totalTrialCount = 1;
        while (totalTrialCount < TOTAL_TRIAL_COUNT) {
            List<Pair> pairResult = generatePairResult(shuffleNames);
            if (!isDuplicated(pairHistory.resultOfLevel(level), pairResult)) {
                return pairResult;
            }
            shuffleNames = ShuffleGenerator.shuffleNames(shuffleNames);
            totalTrialCount += 1;
        }
        return Collections.emptyList();
    }

    private List<Pair> generatePairResult(final List<String> shuffleNames) {
        List<Pair> pairResult = new ArrayList<>();
        if (isEven(shuffleNames)) {
            addEvenPair(shuffleNames, pairResult);
        }
        addOddPair(shuffleNames, pairResult);
        return pairResult;
    }

    private boolean isEven(final List<String> crewNames) {
        return crewNames.size() % 2 == 0;
    }

    private void addEvenPair(final List<String> shuffledNames, final List<Pair> pairResult) {
        for (int i = 0; i < shuffledNames.size(); i += 2) {
            List<String> names = List.of(shuffledNames.get(i), shuffledNames.get(i + 1));
            pairResult.add(new Pair(names));
        }
    }

    private void addOddPair(final List<String> shuffledNames, final List<Pair> pairResult) {
        for (int i = 0; i < shuffledNames.size() - 3; i += 2) {
            List<String> names = List.of(shuffledNames.get(i), shuffledNames.get(i + 1));
            pairResult.add(new Pair(names));
        }
        int lastIndex = shuffledNames.size() - 3;
        List<String> names = List.of(shuffledNames.get(lastIndex), shuffledNames.get(lastIndex + 1), shuffledNames.get(lastIndex + 2));
        pairResult.add(new Pair(names));
    }

    private boolean isDuplicated(final List<Pair> storage, List<Pair> now) {
        return storage.stream().anyMatch(now::contains);
    }
}
