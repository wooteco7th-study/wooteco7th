package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class Pair {
    private final List<String> names;

    public Pair(final List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    public void addPair(final String pair) {
        this.names.add(pair);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Pair pair1)) {
            return false;
        }
        return Objects.equals(getNames(), pair1.getNames());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNames());
    }
}
