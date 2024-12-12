package pairmatching.service;

import java.util.List;
import pairmatching.domain.crew.Crews;
import pairmatching.domain.order.PairOrder;
import pairmatching.domain.pair.Pair;
import pairmatching.domain.pair.PairHistory;
import pairmatching.domain.pair.PairMatcher;
import pairmatching.domain.pair.PairResult;
import pairmatching.domain.random.RandomShuffle;
import pairmatching.dto.PairMatchResultDto;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public class PairService {

    private final Crews backendCrews;
    private final Crews frontendCrews;
    private final PairHistory pairHistory;

    public PairService(final Crews backendCrews, final Crews frontendCrews, final PairHistory pairHistory) {
        this.backendCrews = backendCrews;
        this.frontendCrews = frontendCrews;
        this.pairHistory = pairHistory;
    }

    private Crews getCrews(final PairOrder pairOrder, final Crews backendCrews, final Crews frontendCrews) {
        if (pairOrder.getCourse().isBackend()) {
            return backendCrews;
        }
        return frontendCrews;
    }

    public PairMatchResultDto inquirePair(final PairOrder pairOrder) {
        // 페어 조회
        if (!historyNotExists(pairOrder, pairHistory)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_NO_HISTORY);
        }
        List<Pair> pairs = pairHistory.inquire(pairOrder);
        return PairMatchResultDto.from(pairs);
    }

    private boolean historyNotExists(final PairOrder pairOrder, final PairHistory pairHistory) {
        return pairHistory.isExists(pairOrder);
    }

    public PairMatchResultDto matchPair(final PairOrder pairOrder) {
        Crews crews = getCrews(pairOrder, backendCrews, frontendCrews);
        PairMatcher pairMatcher = new PairMatcher(crews, new RandomShuffle());
        List<Pair> pairs = pairMatcher.matchCrewUntilCount(pairHistory, pairOrder.getLevel());
        pairHistory.add(new PairResult(pairOrder, pairs));
        return PairMatchResultDto.from(pairs);
    }

    public void initialize() {
        pairHistory.clear();
    }

    public boolean hasHistory(final PairOrder pairOrder) {
        return pairHistory.isExists(pairOrder);
    }
}
