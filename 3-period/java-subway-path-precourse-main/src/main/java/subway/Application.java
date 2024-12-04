package subway;

import java.util.Scanner;
import subway.domain.repository.LineRepository;
import subway.domain.repository.StationRepository;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // TODO: 프로그램 구현
        var stationRepository = new StationRepository();
        var lineRepository = new LineRepository();
        var initializeApplication = new InitializeApplication(stationRepository, lineRepository);
        var distanceGraph = initializeApplication.getDistanceGraph();
        var timeGraph = initializeApplication.getTimeGraph();
    }
}
