package pairmatching.support;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.crew.Crews;
import pairmatching.domain.order.Course;

public class TestFixture {

    public static Crews makeCrews() {
        List<Crew> crews = new ArrayList<>();
        crews.add(makeCrew("밍트"));
        crews.add(makeCrew("러키"));
        crews.add(makeCrew("수달"));
        crews.add(makeCrew("진수"));
        return new Crews(crews);
    }

    public static Crews makeOddNumberCrews() {
        List<Crew> crews = new ArrayList<>();
        crews.add(makeCrew("밍트"));
        crews.add(makeCrew("러키"));
        crews.add(makeCrew("수달"));
        crews.add(makeCrew("진수"));
        crews.add(makeCrew("가나디"));
        return new Crews(crews);
    }

    public static Crew makeCrew(final String name) {
        return new Crew(Course.백엔드, name);
    }

}
