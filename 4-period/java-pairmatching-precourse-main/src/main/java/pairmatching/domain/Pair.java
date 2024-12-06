package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class Pair {
    private final List<String> pair;

    public Pair(final List<String> pair) {
        this.pair = pair;
    }

    public List<String> getPair() {
        return pair;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Pair pair1)) {
            return false;
        }
        return Objects.equals(getPair(), pair1.getPair());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPair());
    }
}
