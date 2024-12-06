package pairmatching.service;

import java.util.List;
import pairmatching.domain.pair.PairHistory;
import pairmatching.domain.pair.PairOrder;
import pairmatching.dto.PairMatchResultDto;

public class PairService {

    public PairMatchResultDto matchPair(final PairOrder pairOrder, final PairHistory pairHistory) {
        return new PairMatchResultDto(List.of(List.of("a", "b"), List.of("c", "d", "e")));
    }

    public void inquirePair(final PairOrder pairOrder, final PairHistory pairHistory) {
        // 페어 조회

    }
}
