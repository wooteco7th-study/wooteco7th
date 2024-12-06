package pairmatching.domain;

import java.util.Collections;
import java.util.List;

public class PairGroup {

    private final List<Pair> pairs;

    public PairGroup(final List<Pair> pairs) {
        this.pairs = pairs;
    }

    public boolean hasPair(final List<Pair> pairs) {
        return pairs.stream()
                .anyMatch(pair -> hasCrews(pair.getCrews()));
    }

    public List<Pair> getPairs() {
        return Collections.unmodifiableList(pairs);
    }

    private boolean hasCrews(final List<String> crews) {
        return pairs.stream()
                .anyMatch(pair -> pair.hasCrews(crews));
    }
}
