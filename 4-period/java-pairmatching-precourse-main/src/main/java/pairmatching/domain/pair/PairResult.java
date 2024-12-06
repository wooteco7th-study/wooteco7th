package pairmatching.domain.pair;

import java.util.List;

public class PairResult {

    private final PairOrder pairOrder;
    private final List<Pair> pairs;

    public PairResult(final PairOrder pairOrder, final List<Pair> pairs) {
        this.pairOrder = pairOrder;
        this.pairs = pairs;
    }


}
