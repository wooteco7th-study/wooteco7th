package pairmatching.domain;

import java.lang.System.Logger.Level;

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
}
