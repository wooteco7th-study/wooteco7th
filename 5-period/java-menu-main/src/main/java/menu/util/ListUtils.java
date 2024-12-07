package menu.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListUtils {
    private ListUtils() {
    }

    // 빈 값 확인
    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }


    // 중복 요소가 있는지 확인
    public static boolean hasDuplicates(List<?> target) {
        Set<?> set = new HashSet<>(target);
        return set.size() != target.size();
    }

    // 리스트 역순 반환
    public static <T> List<T> reverse(List<T> list) {
        List<T> reversedList = new ArrayList<>(list);
        Collections.reverse(reversedList);
        return reversedList;
    }

    // 중복 제거 메서드
    public static <T> List<T> removeDuplicates(List<T> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    // 구분자로 문자열 연결 메서드
    public static String join(List<?> list, String delimiter) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(delimiter));
    }

    // 특정 요건 확인 메서드
    // 예시 :
    // boolean allPositive = ListUtil.allMatch(numbers, number -> number > 0);
    public static <T> boolean allMatch(List<T> list, Predicate<T> condition) {
        return list.stream().allMatch(condition);
    }


}
