package christmas.service;

import java.util.List;

public class Separator {

    private Separator() {

    }

    public static List<String> separate(String input, String separator) {
        return List.of(input.split(separator));
    }
}