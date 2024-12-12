package pairmatching.domain.pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.order.Level;
import pairmatching.domain.order.PairOrder;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public class PairHistory {

    private final Map<PairOrder, List<Pair>> history;

    public PairHistory(final Map<PairOrder, List<Pair>> history) {
        this.history = new HashMap<>(history);
    }

    public void add(final PairResult pairResult) {
        PairOrder pairOrder = pairResult.getPairOrder();
        history.put(pairOrder, pairResult.getPairs());
    }

    public boolean isExists(final PairOrder compared) {
        return history.containsKey(compared);
    }

    public boolean hasSameLevelPair(final Pair pair, final Level level) {
        return history.entrySet().stream()
                .filter(history -> history.getKey().hasSameLevel(level))
                .anyMatch(history -> history.getValue().contains(pair));
    }

    public List<Pair> inquire(final PairOrder compared) {
        if (history.containsKey(compared)) {
            return history.get(compared);
        }
        throw new CustomIllegalArgumentException(ErrorMessage.INVALID_NO_HISTORY);
    }

    public void clear() {
        history.clear();
    }
}
