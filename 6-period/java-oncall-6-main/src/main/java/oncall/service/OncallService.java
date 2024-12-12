package oncall.service;

import java.util.List;
import oncall.domain.turn.TurnScheduler;
import oncall.dto.TurnDto;

public class OncallService {

    public List<TurnDto> makeWorkSchedule(final TurnScheduler turnScheduler) {
        return turnScheduler.makeWorkSchedule();
    }
}
