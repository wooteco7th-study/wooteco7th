package pairmatching.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class ShuffleGenerator {

    public List<String> shuffleNames(List<String> crewNames) {
        return Randoms.shuffle(crewNames);
    }
}
