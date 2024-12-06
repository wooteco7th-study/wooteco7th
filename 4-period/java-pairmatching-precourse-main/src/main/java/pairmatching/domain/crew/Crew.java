package pairmatching.domain.crew;

import pairmatching.domain.order.Course;

public class Crew {

    private final Course course;
    private final String name;

    public Crew(final Course course, final String name) {
        this.course = course;
        this.name = name;
    }

    public boolean isBackend() {
        return course.isBackend();
    }

    public boolean isFrontend() {
        return course.isFrontend();
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }
}
