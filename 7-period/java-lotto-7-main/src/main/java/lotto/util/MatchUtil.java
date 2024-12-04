package lotto.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MatchUtil {
    private MatchUtil() {
    }

    public static <T> boolean allMatch(List<T> list1, List<T> list2) {
        return list2.containsAll(list1);
    }

    public static <T> boolean anyMatch(List<T> list1, List<T> list2) {
        return !Collections.disjoint(list1, list2);
    }


    public static int countMatches(List<Integer> numbers, List<Integer> anotherNumbers) {
        return (int) numbers.stream()
                .filter(anotherNumbers::contains)
                .count();
    }

    public static int countMatches(List<Integer> numbers, int target) {
        return (int) numbers.stream()
                .filter(number -> number == target)
                .count();
    }

    public static boolean hasNumber(List<Integer> numbers, int target) {
        return numbers.contains(target);
    }

    public static <T> boolean isEqualCollection(Collection<T> coll1, Collection<T> coll2) {
        return coll1.size() == coll2.size() && coll1.containsAll(coll2);
    }

    public static <K, V> boolean allKeysMatch(Map<K, V> map1, Map<K, V> map2) {
        return map2.keySet().containsAll(map1.keySet());
    }

    public static <K, V> boolean anyKeyMatch(Map<K, V> map1, Map<K, V> map2) {
        return !Collections.disjoint(map1.keySet(), map2.keySet());
    }

    public static <K, V> boolean allValuesMatch(Map<K, V> map1, Map<K, V> map2) {
        return map2.values().containsAll(map1.values());
    }

    public static <K, V> boolean anyValueMatch(Map<K, V> map1, Map<K, V> map2) {
        return !Collections.disjoint(map1.values(), map2.values());
    }
    
}
