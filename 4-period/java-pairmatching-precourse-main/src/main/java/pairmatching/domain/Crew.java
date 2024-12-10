package pairmatching.domain;

import static pairmatching.domain.vo.Course.BACKEND;
import static pairmatching.domain.vo.Course.FRONTEND;

import pairmatching.domain.vo.Course;

public class Crew {
    private final Course course;
    private final String name;

    public Crew(final Course course, final String name) {
        this.course = course;
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public boolean isBackend() {
        return BACKEND.equals(course);
    }

    public boolean isFrontend() {
        return FRONTEND.equals(course);
    }

    @Override
    public String toString() {
        return "Crew{" +
                "course=" + course +
                ", name='" + name + '\'' +
                '}';
    }
}
