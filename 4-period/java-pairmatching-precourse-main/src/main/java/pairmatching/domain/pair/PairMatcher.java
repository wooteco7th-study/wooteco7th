package pairmatching.domain.pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.order.Level;
import pairmatching.domain.random.Shuffle;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public class PairMatcher {

    private final List<Crew> crews;
    private final Shuffle shuffle;

    public PairMatcher(final List<Crew> crews, final Shuffle randomShuffle) {
        this.crews = crews;
        this.shuffle = randomShuffle;
    }

    public List<Pair> matchCrewUntilCount(final PairHistory pairHistory, final Level level) {
        int count = 0;
        while (count != 3) {
            List<Pair> pairs = matchCrew(pairHistory, level);
            if (!pairs.isEmpty()) {
                return pairs;
            }
            count++;
        }
        throw new CustomIllegalArgumentException(ErrorMessage.PAIR_MATCH_FAILED);
    }

    private List<Pair> matchCrew(final PairHistory pairHistory, final Level level) {
        List<String> crews = shuffle.shuffleCrews(getNames());
        List<Pair> pairs = makePairs(crews);
        if (hasPair(pairs, pairHistory, level)) {
            return Collections.emptyList();
        }
        return pairs;
    }

    private List<Pair> makePairs(final List<String> crews) {
        if (crews.size() % 2 == 0) {
            return makePairsForEvenNumbers(crews);
        }
        return makePairsForOddNumbers(crews);
    }

    private List<Pair> makePairsForEvenNumbers(final List<String> crews) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < crews.size(); i += 2) {
            Pair pair = new Pair(new ArrayList<>(List.of(crews.get(i), crews.get(i + 1))));
            pairs.add(pair);
        }
        return pairs;
    }

    private List<Pair> makePairsForOddNumbers(final List<String> crews) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < crews.size() - 1; i += 2) {
            Pair pair = new Pair(new ArrayList<>(List.of(crews.get(i), crews.get(i + 1))));
            pairs.add(pair);
        }
        Pair lastPair = pairs.getLast();
        lastPair.add(crews.getLast());
        return pairs;
    }

    private boolean hasPair(final List<Pair> pairs, final PairHistory pairHistory, final Level level) {
        return pairs.stream()
                .anyMatch(pair -> pairHistory.hasSameLevelPair(pair, level));
    }

    private List<String> getNames() {
        return crews.stream()
                .map(Crew::getName)
                .toList();
    }
}
