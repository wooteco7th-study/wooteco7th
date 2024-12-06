package pairmatching.domain;

import java.util.List;

public class Pair {
    private final List<String> pair;

    public Pair(final List<String> pair) {
        this.pair = pair;
    }

    public List<String> getPair() {
        return pair;
    }
}
