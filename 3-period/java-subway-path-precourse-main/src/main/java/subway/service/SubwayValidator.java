package subway.service;

import subway.domain.StationRepository;

public class SubwayValidator {

    public SubwayValidator(final String start, final String end) {
        validate(start, end);
    }

    private void validate(final String start, final String end) {
        validateStation(start);
        validateStation(end);
        validateStart(start, end);
    }

    private void validateStation(final String station) {
        if (StationRepository.notContain(station)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateStart(final String start, final String end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException();
        }
    }
}
