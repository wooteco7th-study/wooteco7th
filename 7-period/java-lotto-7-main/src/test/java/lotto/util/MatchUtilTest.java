package lotto.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MatchUtilTest {

    @Test
    public void testAllMatch_Integer() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        assertTrue(MatchUtil.allMatch(list1, list2));

        List<Integer> list3 = Arrays.asList(1, 2, 6);
        assertFalse(MatchUtil.allMatch(list3, list2));
    }

    @Test
    public void testAnyMatch_Integer() {
        List<Integer> list1 = Arrays.asList(6, 7, 8);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        assertFalse(MatchUtil.anyMatch(list1, list2));

        List<Integer> list3 = Arrays.asList(3, 6, 7);
        assertTrue(MatchUtil.anyMatch(list3, list2));
    }

    @Test
    public void testAllMatch_String() {
        List<String> list1 = Arrays.asList("a", "b");
        List<String> list2 = Arrays.asList("a", "b", "c");
        assertTrue(MatchUtil.allMatch(list1, list2));

        List<String> list3 = Arrays.asList("a", "d");
        assertFalse(MatchUtil.allMatch(list3, list2));
    }

    @Test
    public void testAnyMatch_String() {
        List<String> list1 = Arrays.asList("x", "y");
        List<String> list2 = Arrays.asList("a", "b", "c");
        assertFalse(MatchUtil.anyMatch(list1, list2));

        List<String> list3 = Arrays.asList("a", "x", "y");
        assertTrue(MatchUtil.anyMatch(list3, list2));
    }

    @Test
    public void testCountMatches_List() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> anotherNumbers = Arrays.asList(3, 4, 5, 6, 7);
        assertEquals(3, MatchUtil.countMatches(numbers, anotherNumbers));

        List<Integer> numbers2 = Arrays.asList(8, 9);
        assertEquals(0, MatchUtil.countMatches(numbers2, anotherNumbers));
    }

    @Test
    public void testCountMatches_Target() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 2);
        assertEquals(3, MatchUtil.countMatches(numbers, 2));

        assertEquals(0, MatchUtil.countMatches(numbers, 5));
    }

    @Test
    public void testHasNumber() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        assertTrue(MatchUtil.hasNumber(numbers, 3));
        assertFalse(MatchUtil.hasNumber(numbers, 6));
    }
}
