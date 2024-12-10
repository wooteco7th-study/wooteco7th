package store.util;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Queue;

public class FileReader {

    private FileReader() {
    }

    public static Queue<String> readFileLines(final String path) {
        try {
            URI uri = FileReader.class.getClassLoader().getResource(path).toURI();
            return new ArrayDeque<>(Files.readAllLines(Paths.get(uri)));
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}
