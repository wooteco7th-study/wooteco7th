package pairmatching.view.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequestParser {

    private RequestParser() {
    }

    public static List<String> parseItemList(String input) {
        String[] items = input.trim().split(",");
        return Arrays.stream(items)
                .map(String::trim)
                .collect(Collectors.toList());
    }

}



