package oncall.domain.turn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import oncall.domain.name.Name;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public class Turn {

    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 35;

    private final Deque<Name> names;

    public Turn(final List<String> names) {
        validate(names);
        this.names = Turn.of(names);
    }

    private static Deque<Name> of(final List<String> names) {
        return new ArrayDeque<>(names.stream()
                .map(Name::new)
                .toList());
    }

    public boolean hasSameNameFront(final Name compared) {
        return compared.equals(names.peekFirst());
    }

    public Name pollFirst() {
        return names.pollFirst();
    }

    public boolean hasOnlyOne(final Name name) {
        int frequency = Collections.frequency(new ArrayList<>(names), name);
        return frequency == 1;
    }

    private void validate(final List<String> values) {
        validateDuplicated(values);
        validateSize(values);
    }

    private void validateSize(final List<String> values) {
        if (values.size() < MIN_LENGTH || values.size() > MAX_LENGTH) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    private void validateDuplicated(final List<String> values) {
        if (values.size() != values.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    public int getSize() {
        return names.size();
    }

    public List<Name> getNames() {
        return List.copyOf(names);
    }

    public void addLast(final Name name) {
        names.addLast(name);
    }

    public void addFirst(final Name name) {
        names.addFirst(name);
    }

    public Name pollLast() {
        return names.pollLast();
    }

    public Name peekFirst() {
        return names.peekFirst();
    }
}
