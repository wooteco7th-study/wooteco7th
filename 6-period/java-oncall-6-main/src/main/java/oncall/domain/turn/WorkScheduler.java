package oncall.domain.turn;

import java.util.List;
import oncall.domain.date.Month;
import oncall.domain.name.Name;

public class WorkScheduler {

    private final Month month;
    private final Turns turns;
    private final List<Name> result;

    public WorkScheduler(final Month month, final Turns turns, final List<Name> result) {
        this.month = month;
        this.turns = turns;
        this.result = result;
    }

    public List<Name> process() {
        boolean isHolidayChanged = false;
        boolean isWeekdayChanged = false;
        for (int day = 1; day <= month.getEndDay(); day++) {
            if (month.isHoliday(day)) {
                isHolidayChanged = processTurn(turns.getHolidayTurn(), isHolidayChanged);
                continue;
            }
            isWeekdayChanged = processTurn(turns.getWeekdayTurn(), isWeekdayChanged);
        }
        return result;
    }

    private boolean processTurn(final Turn turn, final boolean isChanged) {
        if (isChanged) {
            changeToOriginalTurn(turn);
            return false;
        }
        if (hasSameNameBefore(turn.peekFirst())) {
            changeTurn(turn);
            return true;
        }
        executeWithoutChange(turn);
        return false;
    }

    private void executeWithoutChange(final Turn turn) {
        Name name = turn.pollFirst();
        result.add(name);
        turn.addLast(name);
    }

    private void changeTurn(final Turn turn) {
        Name name = turn.pollFirst();
        Name nextName = turn.pollFirst();
        result.add(nextName);
        turn.addFirst(name);
        turn.addLast(nextName);
    }

    private boolean hasSameNameBefore(final Name name) {
        return !result.isEmpty() && name.equals(result.getLast());
    }

    private void changeToOriginalTurn(final Turn turn) {
        Name name = turn.pollFirst();
        result.add(name);
        Name nextName = turn.pollLast();
        turn.addLast(name);
        turn.addLast(nextName);
    }
}
