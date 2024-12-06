package subway.domain;

import java.util.Collections;
import java.util.List;

public class RoutesRepository {

    private final List<Route> routes;

    public RoutesRepository(final List<Route> routes) {
        this.routes = routes;
    }

    public boolean isConnected(final List<Route> routes, final String start, final String end) {
        return routes.stream()
                .anyMatch(route -> route.findRoute(StationRepository.findByName(start),
                        StationRepository.findByName(end)));
    }

    public int getDistance(final String start, final String end) {
        return routes.stream()
                .filter(route -> route.getDepartureStation().getName().equals(start))
                .filter(route -> route.getArrivalStation().getName().equals(end))
                .map(Route::getDistance)
                .findFirst()
                .orElse(0);
    }

    public void add(final Route route) {
        routes.add(route);
    }

    public int getTotalTime(final List<String> shortestDistancePath) {
        int total = 0;
        for (int i = 0; i < shortestDistancePath.size() - 1; i++) {
            String start = shortestDistancePath.get(i);
            String end = shortestDistancePath.get(i + 1);
            total += getTime(start, end);
        }
        return total;
    }

    public int getTotalDistance(final List<String> shortestDistancePath) {
        int total = 0;
        for (int i = 0; i < shortestDistancePath.size() - 1; i++) {
            String start = shortestDistancePath.get(i);
            String end = shortestDistancePath.get(i + 1);
            total += getDistance(start, end);
        }
        return total;
    }

    public int getTime(final String start, final String end) {
        return getAdjacentDistance(start, end);
    }

    private int getAdjacentDistance(final String start, final String end) {
        return routes.stream()
                .filter(route -> route.getDepartureStation().getName().equals(start))
                .filter(route -> route.getArrivalStation().getName().equals(end))
                .map(Route::getTakenTime)
                .findFirst()
                .orElse(0);
    }

    public List<Route> getRoutes() {
        return Collections.unmodifiableList(routes);
    }
}
