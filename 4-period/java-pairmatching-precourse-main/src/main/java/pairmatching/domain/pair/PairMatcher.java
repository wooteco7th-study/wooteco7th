package pairmatching.domain.pair;

import java.util.Collections;
import java.util.List;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.crew.Crews;
import pairmatching.domain.order.Level;
import pairmatching.domain.random.Shuffle;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public class PairMatcher {

    private static final int MAX_PAIR_COUNT = 3;

    private final Crews crews;
    private final Shuffle shuffle;

    public PairMatcher(final Crews crews, final Shuffle randomShuffle) {
        this.crews = crews;
        this.shuffle = randomShuffle;
    }

    public List<Pair> matchCrewUntilCount(final PairHistory pairHistory, final Level level) {
        int count = 0;
        while (count != MAX_PAIR_COUNT) {
            List<Pair> pairs = matchCrew(pairHistory, level);
            if (!pairs.isEmpty()) {
                return pairs;
            }
            count++;
        }
        throw new CustomIllegalArgumentException(ErrorMessage.PAIR_MATCH_FAILED);
    }

    private List<Pair> matchCrew(final PairHistory pairHistory, final Level level) {
        Crews shuffleCrews = Crews.from(crews.getCourse(), shuffle.shuffleCrews(getNames()));
        List<Pair> pairs = makePairs(shuffleCrews);
        if (hasPair(pairs, pairHistory, level)) {
            return Collections.emptyList();
        }
        return pairs;
    }

    private List<Pair> makePairs(final Crews crews) {
        if (crews.isEvenNumberSize()) {
            return crews.makePairsForEvenNumbers();
        }
        return crews.makePairsForOddNumbers();
    }

    private boolean hasPair(final List<Pair> pairs, final PairHistory pairHistory, final Level level) {
        return pairs.stream()
                .anyMatch(pair -> pairHistory.hasSameLevelPair(pair, level));
    }

    public List<String> getNames() {
        return crews.getCrews().stream()
                .map(Crew::getName)
                .toList();
    }
}
