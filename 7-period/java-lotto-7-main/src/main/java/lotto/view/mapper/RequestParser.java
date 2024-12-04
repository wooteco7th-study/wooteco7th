package lotto.view.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.util.ValidationUtils;

public class RequestParser {

    private RequestParser() {
    }

    /**
     * 입력 예시: "[ 사탕 , 2,1000] ; [ 콜라, 1,2000 ] ; [초코-1,3000]" 아이템 상세 정보를 파싱하여 리스트로 반환합니다.
     */
    public static List<List<String>> parseItemDetailsWithPrice(String input) {
        String[] items = extractItems(input);
        return Arrays.stream(items)
                .map(RequestParser::parseItemWithPrice)
                .collect(Collectors.toList());
    }

    private static String[] extractItems(String input) {
        String cleanedInput = input.replaceAll("[\\[\\]]", "");
        return cleanedInput.split(";");
    }

    private static List<String> parseItemWithPrice(String item) {
        String[] details = item.trim().split(",");
        return Arrays.stream(details)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * 입력 예시: "[ 사탕 , 2] ; [ 콜라, 1 ] ; [초코-1]" 아이템 상세 정보를 파싱하여 리스트로 반환합니다.
     */
    public static List<List<String>> parseItemDetails(String input) {
        String[] items = extractItems(input);
        return Arrays.stream(items)
                .map(RequestParser::parseItemDetail)
                .collect(Collectors.toList());
    }

    private static List<String> parseItemDetail(String item) {
        String[] details = item.trim().split(",");
        return Arrays.stream(details)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * 입력 예시: "사탕 - 2, 바나나 -1, 구름-2" 아이템과 수량을 파싱하여 리스트로 반환합니다.
     */
    public static List<List<String>> parseItemQuantities(String input) {
        String[] items = input.trim().split(",");
        return Arrays.stream(items)
                .map(RequestParser::parseItemQuantity)
                .collect(Collectors.toList());
    }

    private static List<String> parseItemQuantity(String item) {
        String[] itemData = item.trim().split("-");
        return Arrays.stream(itemData)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * 입력 예시: "구름, 사탕, 족발, 고기, 사과" 아이템 리스트를 반환합니다.
     */
    public static List<String> parseItemList(String input) {
        String[] items = input.trim().split(",");
        return Arrays.stream(items)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /*
        사과 -2
     */
    public static List<String> parseSingleItemQuantity(String input) {
        String[] parts = input.trim().split("-");
        return Arrays.stream(parts)
                .map(String::trim)
                .collect(Collectors.toList());
    }


    /**
     * 입력 예시: "1,2,3,4,5,6" 숫자 리스트를 반환합니다.
     */
    public static List<Integer> parseIntegerList(String input) {
        String[] numbers = input.trim().split(",");
        return Arrays.stream(numbers)
                .map(String::trim)
                .map(RequestParser::parseToInt)
                .collect(Collectors.toList());
    }

    public static int parseToInt(String input) {
        ValidationUtils.validateInteger(input);
        return Integer.parseInt(input);
    }
}
