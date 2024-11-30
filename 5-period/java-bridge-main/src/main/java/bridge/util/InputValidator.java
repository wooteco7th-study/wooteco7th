package bridge.util;

import bridge.exception.CustomIllegalArgumentException;
import bridge.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    private InputValidator() {
    }

    public static void validateNotNullOrBlank(final String input, final ErrorMessage message) {
        if (input == null || input.isBlank()) {
            throw new CustomIllegalArgumentException(message);
        }
    }

    public static boolean isInvalidPattern(String input, Pattern pattern) {
        return !pattern.matcher(input).matches();
    }

    /**
     * 정규식 패턴으로 문자열을 파싱하여 매칭되는 그룹들의 리스트를 반환
     */
    public static List<String> findMatchingGroups(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        List<String> groups = new ArrayList<>();
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                groups.add(matcher.group(i));
            }
        }
        return Collections.unmodifiableList(groups);
    }
}
