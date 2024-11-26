package vendingmachine.util;

import java.util.Arrays;
import java.util.List;

public class Separator {

    private Separator() {

    }

    public static List<String> split(String input, String separator, int limit) {
        return Arrays.asList(input.split(separator, limit));
    }
}
