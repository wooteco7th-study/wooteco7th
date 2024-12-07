package pairmatching.repository;

import static pairmatching.domain.vo.Course.BACKEND;
import static pairmatching.domain.vo.Course.FRONTEND;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Crew;

public class FileLoader {
    private final static String BACKEND_PATH = "/Users/jins/Desktop/project/2024wootecho/wooteco7th/4-period/java-pairmatching-precourse-main/src/main/resources/backend-crew.md";
    private final static String FRONTEND_PATH = "/Users/jins/Desktop/project/2024wootecho/wooteco7th/4-period/java-pairmatching-precourse-main/src/main/resources/frontend-crew.md";

    public List<Crew> initializeBackend() {
        try {
            List<String> backendNames = Files.readAllLines(Paths.get(BACKEND_PATH));
            List<Crew> crews = new ArrayList<>();
            for (String name : backendNames) {
                crews.add(new Crew(BACKEND, name));
            }
            return crews;
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 파일 읽기 문제 발생:" + e.getMessage());
        }
    }

    public List<Crew> initializeFrontend() {
        try {

            List<String> frontendNames = Files.readAllLines(Paths.get(FRONTEND_PATH));
            List<Crew> crews = new ArrayList<>();
            for (String name : frontendNames) {
                crews.add(new Crew(FRONTEND, name));
            }
            return crews;
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 파일 읽기 문제 발생:" + e.getMessage());
        }
    }
}
