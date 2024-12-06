package pairmatching.domain.pair;

import java.util.Objects;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public class PairOrder {

    private final Course course;
    private final Level level;
    private final Mission mission;

    public PairOrder(final String course, final String level, final String mission) {
        this.course = Course.from(course);
        this.level = Level.from(level);
        this.mission = Mission.from(mission);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PairOrder pairOrder)) {
            return false;
        }
        return getCourse() == pairOrder.getCourse() && getLevel() == pairOrder.getLevel()
                && getMission() == pairOrder.getMission();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourse(), getLevel(), getMission());
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public Mission getMission() {
        return mission;
    }
}
