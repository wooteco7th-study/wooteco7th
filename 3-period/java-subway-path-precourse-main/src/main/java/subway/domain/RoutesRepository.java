package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoutesRepository {

    private static final List<Route> routes = new ArrayList<>();

    public static void addRoute(final Route route) {
        routes.add(route);
    }

    public static List<Route> routes() {
        return Collections.unmodifiableList(routes);
    }

    public static boolean isConnected(final String start, final String end) {
        return routes.stream()
                .anyMatch(route -> route.findRoute(StationRepository.findByName(start),
                        StationRepository.findByName(end)));
    }

    public static int getDistance(final String start, final String end) {
        return routes.stream()
                .filter(route -> route.getDepartureStation().getName().equals(start))
                .filter(route -> route.getArrivalStation().getName().equals(end))
                .map(Route::getDistance)
                .findFirst()
                .orElse(0);
    }

    public static int getTotalTime(final List<String> shortestDistancePath) {
        int total = 0;
        for (int i = 0; i < shortestDistancePath.size() - 1; i++) {
            String start = shortestDistancePath.get(i);
            String end = shortestDistancePath.get(i + 1);
            total += getTime(start, end);
        }
        return total;
    }

    public static int getTotalDistance(final List<String> shortestDistancePath) {
        int total = 0;
        for (int i = 0; i < shortestDistancePath.size() - 1; i++) {
            String start = shortestDistancePath.get(i);
            String end = shortestDistancePath.get(i + 1);
            total += getDistance(start, end);
        }
        return total;
    }

    public static int getTime(final String start, final String end) {
        return getAdjacentDistance(start, end);
    }

    private static int getAdjacentDistance(final String start, final String end) {
        return routes.stream()
                .filter(route -> route.getDepartureStation().getName().equals(start))
                .filter(route -> route.getArrivalStation().getName().equals(end))
                .map(Route::getTakenTime)
                .findFirst()
                .orElse(0);
    }
}
