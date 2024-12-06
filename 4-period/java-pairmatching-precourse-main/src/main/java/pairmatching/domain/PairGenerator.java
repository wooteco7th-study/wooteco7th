package pairmatching.domain;

import pairmatching.util.ShuffleGenerator;

import java.util.ArrayList;
import java.util.List;

import static pairmatching.exception.ExceptionMessage.MATCHING_IMPOSSIBLE;

public class PairGenerator {
    private final ShuffleGenerator shuffleGenerator;
    private final PairResult pairResult;

    public PairGenerator(final ShuffleGenerator shuffleGenerator, final PairResult pairResult) {
        this.shuffleGenerator = shuffleGenerator;
        this.pairResult = pairResult;
    }

    public List<Pair> generate(Level level, List<String> crewNames) {
        validateLevel(level);
        int totalTrialCount = 1;
        List<Pair> result = new ArrayList<>();
        if (isEven(crewNames)) {
            while (totalTrialCount < 3) {
                List<String> shuffledNames = shuffleGenerator.shuffleNames(crewNames);
                for (int i = 0; i < shuffledNames.size() - 1; i += 2) {
                    List<String> names = new ArrayList<>();
                    names.add(shuffledNames.get(i));
                    names.add(shuffledNames.get(i + 1));
                    Pair pair = new Pair(names);
                    result.add(pair);
                }
                if (isDuplicated(pairResult.resultOfLevel(level), result)) {
                    totalTrialCount += 1;
                    continue;
                }
                return result;
            }
        }
        while (totalTrialCount < 3) {
            List<String> shuffledNames = shuffleGenerator.shuffleNames(crewNames);
            for (int i = 0; i < shuffledNames.size() - 3; i += 2) {
                List<String> names = new ArrayList<>();
                names.add(shuffledNames.get(i));
                names.add(shuffledNames.get(i + 1));
                Pair pair = new Pair(names);
                result.add(pair);
            }
            List<String> names = new ArrayList<>();
            names.add(shuffledNames.get(shuffledNames.size() - 3));
            names.add(shuffledNames.get(shuffledNames.size() - 2));
            names.add(shuffledNames.get(shuffledNames.size() - 1));
            Pair pair = new Pair(names);
            result.add(pair);
            if (isDuplicated(pairResult.resultOfLevel(level), result)) {
                totalTrialCount += 1;
                continue;
            }
            return result;
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

    private boolean isDuplicated(final List<Pair> storage, List<Pair> now) {
        return storage.stream().anyMatch(now::contains);
    }
}
