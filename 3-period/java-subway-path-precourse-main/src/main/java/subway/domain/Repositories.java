package subway.domain;

public class Repositories {
    private final LineRepository lineRepository;
    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;

    public Repositories(final LineRepository lineRepository, final RouteRepository routeRepository,
                        final StationRepository stationRepository) {
        this.lineRepository = lineRepository;
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
    }


    public LineRepository getLineRepository() {
        return lineRepository;
    }

    public RouteRepository getRouteRepository() {
        return routeRepository;
    }

    public StationRepository getStationRepository() {
        return stationRepository;
    }
}
