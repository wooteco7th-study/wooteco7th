package pairmatching.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static pairmatching.exception.ExceptionMessage.FILE_NOT_EXISTS;

public class CrewFileReader {
    private static final String BACKEND_PATH = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_PATH = "src/main/resources/frontend-crew.md";

    public static List<String> readBackendCrews() {
        return readAllLines(BACKEND_PATH);
    }

    public static List<String> readFrontendCrews() {
        return readAllLines(FRONTEND_PATH);
    }

    private static List<String> readAllLines(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            return br.lines()
                    .map(line -> line.split(" "))
                    .flatMap(Arrays::stream)
                    .toList();
        } catch (IOException e) {
            throw new IllegalStateException(FILE_NOT_EXISTS.getMessage());
        }
    }
}
