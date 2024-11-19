package baseball.infrastructure;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator {
    private final int inclusiveStart;
    private final int inclusiveEnd;

    public RandomNumberGenerator(final int inclusiveStart, final int inclusiveEnd) {
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }

    public int generate(){
       return Randoms.pickNumberInRange(inclusiveStart, inclusiveEnd);

    }
}
