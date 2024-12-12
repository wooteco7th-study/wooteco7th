package pairmatching.domain.crew;

import java.util.Objects;
import pairmatching.domain.order.Course;

public class Crew {

    private final Course course;
    private final String name;

    public Crew(final Course course, final String name) {
        this.course = course;
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Crew crew)) {
            return false;
        }
        return getCourse() == crew.getCourse() && Objects.equals(getName(), crew.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourse(), getName());
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }
}
