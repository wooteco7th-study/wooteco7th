package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();

    public static List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        if (checkDuplicationLine(line)) {
            return;
        }
        lines.add(line);
    }

    public static Line findLine(String lineName) {
        return lines.stream()
                .distinct()
                .filter(line -> line.getName().equals(lineName))
                .collect(Collectors.toList()).get(0);
    }

    private static boolean checkDuplicationLine(Line newline) {
        return lines.stream().anyMatch(line -> line.getName().equals(newline.getName()));
    }

    public static void addLineInfo(Line line, LineInfo lineInfo) {
        line.getLineInfos().add(lineInfo);
    }

    public static boolean deleteLineByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public static void deleteAll() {
        lines.clear();
    }
}
