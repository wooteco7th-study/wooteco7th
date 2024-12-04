package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 출발 지하철역(정점)에서 도착 지하철역까지의 거리, 시간
// - 출발 지하철역
//- 종료 지하철역
//- 걸린 시간
//- 이동 거리
public class RouteRepository {

    private static final List<Route> routes = new ArrayList<>();

    public static List<Route> routes() {
        return Collections.unmodifiableList(routes);
    }

    public static void addAll(List<Route> input) {
        routes.addAll(input);
    }

    public void add(final Route route) {
        routes.add(route);
    }
}
