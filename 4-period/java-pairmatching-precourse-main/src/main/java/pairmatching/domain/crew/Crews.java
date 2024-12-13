package pairmatching.domain.crew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pairmatching.domain.order.Course;
import pairmatching.domain.pair.Pair;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public class Crews {

    private static final int EVEN_NUMBER = 2;

    private final List<Crew> crews;

    public Crews(final List<Crew> crews) {
        validate(crews);
        this.crews = new ArrayList<>(crews);
    }

    public static Crews from(final Course course, final List<String> names) {
        return new Crews(names.stream()
                .map((String name) -> new Crew(course, name))
                .toList());
    }

    public void merge(final Crews other) {
        validateCourse(other);
        validateCrews(other);
        crews.addAll(new ArrayList<>(other.getCrews()));
    }

    public boolean isBackend() {
        Course course = crews.getFirst().getCourse();
        return course.isBackend();
    }

    private void validateCrews(final Crews other) {
        Course course = crews.getFirst().getCourse();
        if (hasSameCrew(other, course)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_CREW_NAME);
        }
    }

    private boolean hasSameCrew(final Crews other, final Course course) {
        return other.getCrews().stream()
                .anyMatch(crew -> isSameCrew(course, crew));
    }

    private boolean isSameCrew(final Course course, final Crew crew) {
        return crew.getCourse() == course && getCrewNames().contains(crew.getName());
    }

    private void validateCourse(final Crews other) {
        Course course = crews.getFirst().getCourse();
        if (isNotMatchCourse(other, course)) {
            throw new CustomIllegalArgumentException(ErrorMessage.CREWS_COURSE_NOT_SAME);
        }
    }

    private boolean isNotMatchCourse(final Crews other, final Course course) {
        return other.getCrews().stream()
                .anyMatch(crew -> crew.getCourse() != course);
    }

    private void validate(final List<Crew> crews) {
        if (crews.size() != crews.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_CREW_NAME);
        }
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

    public void clear() {
        crews.clear();
    }

    public boolean isExists() {
        return !crews.isEmpty();
    }

    public List<String> getCrewNames() {
        return crews.stream()
                .map(Crew::getName)
                .toList();
    }
}
