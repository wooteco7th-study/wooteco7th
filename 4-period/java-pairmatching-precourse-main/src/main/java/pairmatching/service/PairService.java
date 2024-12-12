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
import pairmatching.support.Initializer;

public class PairService {

    public PairMatchResultDto matchPair(final PairOrder pairOrder, final Initializer initializer) {
        Crews crews = getCrews(pairOrder, initializer);
        PairMatcher pairMatcher = new PairMatcher(crews, new RandomShuffle());
        PairHistory pairHistory = initializer.getHistory();
        List<Pair> pairs = pairMatcher.matchCrewUntilCount(pairHistory, pairOrder.getLevel());
        pairHistory.add(new PairResult(pairOrder, pairs));
        return PairMatchResultDto.from(pairs);
    }

    private Crews getCrews(final PairOrder pairOrder, final Initializer initializer) {
        if (pairOrder.getCourse().isBackend()) {
            return initializer.getBackendCrews();
        }
        return initializer.getFrontendCrews();
    }

    public PairMatchResultDto inquirePair(final PairOrder pairOrder, final PairHistory pairHistory) {
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
}
