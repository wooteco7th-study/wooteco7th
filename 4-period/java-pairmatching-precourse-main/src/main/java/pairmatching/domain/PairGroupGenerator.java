package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import pairmatching.error.ErrorMessage;

public class PairGroupGenerator {

    private static final String BACK_END_CREW_PATH = "backend-crew.md";
    private static final String FRONT_END_CREW_PATH = "frontend-crew.md";

    private PairGroupGenerator() {

    }

    public static PairGroup generate(final CourseType courseType) {
        List<String> crews;
        if (Objects.equals(courseType, CourseType.BACK_END)) {
            crews = Randoms.shuffle(readFileLines(BACK_END_CREW_PATH));
            return new PairGroup(createPairs(crews));
        }
        crews = Randoms.shuffle(readFileLines(FRONT_END_CREW_PATH));
        return new PairGroup(createPairs(crews));
    }

    private static List<Pair> createPairs(final List<String> crews) {
        final List<Pair> pairs = new ArrayList<>();
        final Queue<String> queue = new ArrayDeque<>(crews);
        while (queue.size() > 3) {
            pairs.add(new Pair(List.of(queue.poll(), queue.poll())));
        }
        pairs.add(new Pair(new ArrayList<>(queue)));
        return pairs;
    }

    private static List<String> readFileLines(final String path) {
        try {
            URI uri = PairGroupGenerator.class.getClassLoader().getResource(path).toURI();
            return new ArrayList<>(Files.readAllLines(Paths.get(uri)));
        } catch (Exception e) {
            throw new IllegalStateException(ErrorMessage.INVALID_FAIL_FILE_LOAD.getMessage());
        }
    }}
