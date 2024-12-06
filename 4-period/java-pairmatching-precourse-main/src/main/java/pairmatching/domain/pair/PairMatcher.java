package pairmatching.domain.pair;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pairmatching.domain.order.Level;
import pairmatching.domain.crew.Crew;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public class PairMatcher {

    private final List<Crew> crews;

    public PairMatcher(final List<Crew> crews) {
        this.crews = crews;
    }

    public List<Pair> matchCrew(final PairHistory pairHistory, final Level level) {
        //    1. **3회** 시도까지 매칭이 되지 않거나 **매칭을 할 수 있는 경우의 수가 없으면 에러 메시지**를 출력한다.
        int count = 0;
        while (count != 3) {
            List<Pair> pairs = matchCrewUntilCount(pairHistory, level);
            if (!pairs.isEmpty()) {
                return pairs;
            }
            count++;
        }
        throw new CustomIllegalArgumentException(ErrorMessage.PAIR_MATCH_FAILED);
    }

    private List<Pair> matchCrewUntilCount(final PairHistory pairHistory, final Level level) {
        List<String> crews = shuffleCrews();
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
        //    1. 같은 레벨에서 이미 페어로 만난적이 있는 크루끼리 다시 페어로 매칭 된다면 **크루 목록의 순서를 다시 랜덤으로 섞어서 매칭을 시도**한다.
        return pairs.stream()
                .anyMatch(pair -> pairHistory.hasSameLevelPair(pair, level));
    }

    private List<String> shuffleCrews() {
        return Randoms.shuffle(getNames());
    }

    private List<String> getNames() {
        return crews.stream()
                .map(Crew::getName)
                .toList();
    }
}
