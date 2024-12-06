package pairmatching.domain;

import pairmatching.util.ShuffleGenerator;

import java.util.ArrayList;
import java.util.List;

public class PairGenerator {
    private final ShuffleGenerator shuffleGenerator;

    public PairGenerator(final ShuffleGenerator shuffleGenerator) {
        this.shuffleGenerator = shuffleGenerator;
    }

    public PairResult generate(List<String> crewNames) {
        List<String> shuffledNames = shuffleGenerator.shuffleNames(crewNames);
        List<Pair> result = new ArrayList<>();
        if (isEven(shuffledNames)) {
            for (int i = 0; i < shuffledNames.size() - 1; i += 2) {
                List<String> names = new ArrayList<>();
                names.add(shuffledNames.get(i));
                names.add(shuffledNames.get(i + 1));
                Pair pair = new Pair(names);
                result.add(pair);
            }
            return new PairResult(result);
        }
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
        return new PairResult(result);
    }

    private boolean isEven(final List<String> shuffledNames) {
        return shuffledNames.size() % 2 == 0;
    }
}
