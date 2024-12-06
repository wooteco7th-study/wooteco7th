package pairmatching.dto;

import static pairmatching.domain.vo.Course.toCourse;
import static pairmatching.domain.vo.Level.toLevel;
import static pairmatching.domain.vo.Mission.toMission;

import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Level;
import pairmatching.domain.vo.Mission;

public class FairMatchingDto {
    private final Course course;
    private final Level level;
    private final Mission mission;

    public FairMatchingDto(final String course, final String level, final String mission) {
        this.course = toCourse(course);
        this.level = toLevel(level);
        this.mission = toMission(mission);
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
