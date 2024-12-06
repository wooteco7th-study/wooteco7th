package pairmatching.domain.pair;

import java.util.Collections;
import java.util.List;
import pairmatching.domain.Level;

public class PairResult {

    private final PairOrder pairOrder;
    private final List<Pair> pairs;

    public PairResult(final PairOrder pairOrder, final List<Pair> pairs) {
        this.pairOrder = pairOrder;
        this.pairs = pairs;
    }


    public boolean hasSameLevel(final Level level) {
        return pairOrder.getLevel().equals(level);
    }

    public boolean hasSamePair(final Pair compared) {
        return pairs.stream()
                .anyMatch(pair -> pair.equals(compared));
    }

    public PairOrder getPairOrder() {
        return pairOrder;
    }

    public List<Pair> getPairs() {
        return Collections.unmodifiableList(pairs);
    }
}
