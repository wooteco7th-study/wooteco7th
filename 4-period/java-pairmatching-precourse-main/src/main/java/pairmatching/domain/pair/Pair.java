package pairmatching.domain.pair;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import pairmatching.domain.crew.Crew;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public class Pair {

    private final List<Crew> crews;

    public Pair(final List<Crew> crews) {
        validate(crews);
        this.crews = crews;
    }

    private void validate(final List<Crew> crews) {
        if (isDuplicated(crews)) {
            throw new CustomIllegalArgumentException(ErrorMessage.PAIR_DUPLICATED);
        }
    }

    private boolean isDuplicated(final List<Crew> crews) {
        return crews.size() != crews.stream()
                .distinct()
                .count();
    }

    public void add(final Crew crew) {
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
        HashSet<Crew> crewsUnique = new HashSet<>(crews);
        HashSet<Crew> comparedUnique = new HashSet<>(pair.crews);
        return crewsUnique.containsAll(comparedUnique) || comparedUnique.containsAll(crewsUnique);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crews);
    }

    public List<String> getNames() {
        return crews.stream()
                .map(Crew::getName)
                .toList();
    }

    public List<Crew> getCrews() {
        return Collections.unmodifiableList(crews);
    }
}
