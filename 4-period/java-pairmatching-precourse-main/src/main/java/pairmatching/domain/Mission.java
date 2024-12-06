package pairmatching.domain;

import java.util.Objects;

public class Mission {

    private final LevelType levelType;
    private final CourseType courseType;
    private final MissionType missionType;

    public Mission(final LevelType levelType, final CourseType courseType, final MissionType missionType) {
        this.levelType = levelType;
        this.courseType = courseType;
        this.missionType = missionType;
    }

    public boolean isSameLevelType(final LevelType levelType) {
        return this.levelType == levelType;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Mission mission)) {
            return false;
        }
        return Objects.equals(levelType.getName(), mission.levelType.getName())
                && Objects.equals(courseType.getName(), mission.courseType.getName()) && Objects.equals(
                missionType.getName(), mission.missionType.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelType.getName(), courseType.getName(), missionType.getName());
    }
}
