package pairmatching.domain;

import java.util.List;

public class Pair {
    private final List<String> pair;

    public Pair(final List<String> pair) {
        this.pair = pair;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "pair=" + pair +
                '}';
    }
}
