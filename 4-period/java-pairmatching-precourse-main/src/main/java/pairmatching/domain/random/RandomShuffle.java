package pairmatching.domain.random;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomShuffle implements Shuffle {

    public List<String> shuffleCrews(List<String> tokens) {
        return Randoms.shuffle(tokens);
    }

}
