package subway.domain;

public class Repositories {
    private final LineRepository lineRepository;
    private final RoutesRepository routeRepository;
    private final StationRepository stationRepository;

    public Repositories(final LineRepository lineRepository, final RoutesRepository routeRepository,
                        final StationRepository stationRepository) {
        this.lineRepository = lineRepository;
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
    }

    public RoutesRepository getRouteRepository() {
        return routeRepository;
    }

    public StationRepository getStationRepository() {
        return stationRepository;
    }
}
