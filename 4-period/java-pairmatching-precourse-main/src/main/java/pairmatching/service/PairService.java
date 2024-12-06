package pairmatching.service;

import java.util.List;
import pairmatching.domain.Initializer;
import pairmatching.domain.pair.Crews;
import pairmatching.domain.pair.Pair;
import pairmatching.domain.pair.PairHistory;
import pairmatching.domain.pair.PairMatcher;
import pairmatching.domain.pair.PairOrder;
import pairmatching.dto.PairMatchResultDto;

public class PairService {

    public PairMatchResultDto matchPair(final PairOrder pairOrder, final Initializer initializer) {
        Crews crews = getCrews(pairOrder, initializer);
        PairMatcher pairMatcher = new PairMatcher(crews.getCrews());
        List<Pair> pairs = pairMatcher.matchCrew(initializer.getHistory(), pairOrder.getLevel());
        return PairMatchResultDto.from(pairs);
    }

    private Crews getCrews(final PairOrder pairOrder, final Initializer initializer) {
        if (pairOrder.getCourse().isBackend()) {
            return initializer.getBackendCrews();
        }
        return initializer.getFrontendCrews();
    }

    public void inquirePair(final PairOrder pairOrder, final PairHistory pairHistory) {
        // 페어 조회

    }
}
