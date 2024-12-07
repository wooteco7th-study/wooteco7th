package pairmatching.domain.pair;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Pair {

    private final List<String> crews;

    public Pair(final List<String> crews) {
        this.crews = crews;
    }

    public void add(final String crew) {
        crews.add(crew);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair pair)) {
            return false;
        }
        HashSet<String> crewsUnique = new HashSet<>(crews);
        HashSet<String> comparedUnique = new HashSet<>(pair.crews);
        return crewsUnique.containsAll(comparedUnique) || comparedUnique.containsAll(crewsUnique);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crews);
    }

    public List<String> getCrews() {
        return Collections.unmodifiableList(crews);
    }
}
