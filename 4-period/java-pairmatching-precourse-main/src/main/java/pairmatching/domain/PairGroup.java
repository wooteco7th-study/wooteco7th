package pairmatching.domain;

import java.util.Collections;
import java.util.List;

public class PairGroup {

    private final List<Pair> pairs;

    public PairGroup(final List<Pair> pairs) {
        this.pairs = pairs;
    }

    public boolean hasCrews(final List<String> crews) {
        return pairs.stream()
                .anyMatch(pair -> pair.hasCrews(crews));
    }

    public List<Pair> getPairs() {
        return Collections.unmodifiableList(pairs);
    }
}
