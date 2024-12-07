package subway.domain;

import subway.domain.pathconnection.StationDistanceConnection;
import subway.domain.pathconnection.StationTimeConnection;
import subway.domain.repository.LineRepository;
import subway.domain.repository.ShortestDistanceRepository;
import subway.domain.repository.ShortestTimeRepository;
import subway.domain.repository.StationRepository;

import java.util.List;

public class DataInitializer {

    public static void initRepository() {
        saveLines();
        saveStations();
        saveTimeConnections();
        saveDistanceConnections();
    }

    private static void saveLines() {
        List<String> lines = List.of("2호선", "3호선", "신분당선");
        for (String lineName : lines) {
            Line line = new Line(lineName);
            LineRepository.addLine(line);
        }
    }

    private static void saveStations() {
        List<String> stations = List.of("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
        for (String stationName : stations) {
            Station station = new Station(stationName);
            StationRepository.addStation(station);
        }
    }

    private static void saveTimeConnections() {
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("강남역"), 2, 3));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("역삼역"), 2, 3));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("남부터미널역"), 3, 2));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("남부터미널역"), StationRepository.findLineByName("양재역"), 6, 5));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("매봉역"), 1, 1));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("양재역"), 2, 8));
        ShortestTimeRepository.addTime(new StationTimeConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("양재시민의숲역"), 10, 3));
    }

    private static void saveDistanceConnections() {
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("강남역"), 2, 3));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("역삼역"), 2, 3));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("교대역"), StationRepository.findLineByName("남부터미널역"), 3, 2));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("남부터미널역"), StationRepository.findLineByName("양재역"), 6, 5));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("매봉역"), 1, 1));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("강남역"), StationRepository.findLineByName("양재역"), 2, 8));
        ShortestDistanceRepository.addDistance(new StationDistanceConnection(StationRepository.findLineByName("양재역"), StationRepository.findLineByName("양재시민의숲역"), 10, 3));
    }
}
