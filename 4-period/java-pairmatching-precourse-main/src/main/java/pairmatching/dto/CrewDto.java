package pairmatching.dto;

import java.util.List;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.crew.Crews;

public record CrewDto(List<String> names) {

    public static CrewDto of(Crews crews) {
        return new CrewDto(crews.getCrews().stream()
                .map(Crew::getName)
                .toList());
    }
}
