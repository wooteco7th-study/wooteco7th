package menu.view.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequestParser {
    private RequestParser() {
    }


    public static List<String> parseNames(String input) {
        String[] names = input.trim().split(",");
        return Arrays.stream(names)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
