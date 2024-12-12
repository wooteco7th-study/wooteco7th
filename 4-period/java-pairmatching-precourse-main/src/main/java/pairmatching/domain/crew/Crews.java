package pairmatching.domain.crew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pairmatching.domain.order.Course;
import pairmatching.domain.pair.Pair;

public class Crews {

    private static final int EVEN_NUMBER = 2;

    private final List<Crew> crews;

    public Crews(final List<Crew> crews) {
        this.crews = crews;
    }

    public static Crews of(final Course course, final List<String> names) {
        return new Crews(names.stream()
                .map((String name) -> new Crew(course, name))
                .toList());
    }

    public boolean isEvenNumberSize() {
        return crews.size() % EVEN_NUMBER == 0;
    }

    public List<Pair> makePairsForEvenNumbers() {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < crews.size(); i += EVEN_NUMBER) {
            Pair pair = new Pair(new ArrayList<>(List.of(crews.get(i), crews.get(i + 1))));
            pairs.add(pair);
        }
        return pairs;
    }

    public List<Pair> makePairsForOddNumbers() {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < crews.size() - 1; i += EVEN_NUMBER) {
            Pair pair = new Pair(new ArrayList<>(List.of(crews.get(i), crews.get(i + 1))));
            pairs.add(pair);
        }
        Pair lastPair = pairs.getLast();
        lastPair.add(crews.getLast());
        return pairs;
    }

    public Course getCourse() {
        return crews.getFirst().getCourse();
    }

    public List<Crew> getCrews() {
        return Collections.unmodifiableList(crews);
    }
}
