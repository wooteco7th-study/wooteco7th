package pairmatching.util;

import static pairmatching.exception.ErrorMessage.INVALID_FILE_FORMAT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pairmatching.exception.CustomIllegalStateException;

public class PairFileReader {

    private static final String BACKEND_CREW_MD = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_CREW_MD = "src/main/resources/frontend-crew.md";

    private PairFileReader() {
    }

    public static List<String> readBackends() {
        return readFileFromSource(BACKEND_CREW_MD);
    }

    public static List<String> readFrontends() {
        return readFileFromSource(FRONTEND_CREW_MD);
    }

    private static List<String> readFileFromSource(String fileName) {
        try {
            return readFile(fileName);
        } catch (IOException exception) {
            throw new CustomIllegalStateException(INVALID_FILE_FORMAT);
        }
    }

    private static List<String> readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        List<String> inputs = new ArrayList<>();
        makeInput(br, inputs);
        br.close();
        return inputs;
    }

    private static void makeInput(final BufferedReader br, final List<String> inputs) throws IOException {
        while (true) {
            String input = br.readLine();
            if (isTerminated(input)) {
                break;
            }
            inputs.add(input);
        }
    }

    private static boolean isTerminated(final String input) {
        return input == null;
    }
}
