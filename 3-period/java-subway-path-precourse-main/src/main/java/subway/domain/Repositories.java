package subway.domain;

public class Repositories {

    private final RoutesRepository routeRepository;
    private final StationRepository stationRepository;

    public Repositories(final RoutesRepository routeRepository,
                        final StationRepository stationRepository) {
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
