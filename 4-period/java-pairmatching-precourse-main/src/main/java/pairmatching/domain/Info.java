package pairmatching.domain;

import java.util.List;

public class Info {
    private final Course course;
    private final Level level;
    private final Mission mission;

    public Info(final List<String> info) {
        this.course = Course.from(info.getFirst());
        this.level = Level.from(info.get(1));
        this.mission = Mission.from(info.get(2));
    }

    public boolean isBackendCourse() {
        return course == Course.BACKEND;
    }

    public Mission getMission() {
        return mission;
    }

    public Level getLevel() {
        return level;
    }
}
