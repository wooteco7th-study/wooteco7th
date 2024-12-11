package store.util;

import java.lang.Character.UnicodeBlock;

public class KoreanFormatter {

    private static final String STRING_FORMAT = "%%-%ds";

    public static String formatKorean(String word, int formatSize) {
        String formatter = String.format(STRING_FORMAT, formatSize - countKoreanCharacters(word));
        return String.format(formatter, word);
    }

    private static int countKoreanCharacters(String text) {
        return (int) text.chars()
                .filter(KoreanFormatter::isKoreanCharacter)
                .count();
    }

    private static boolean isKoreanCharacter(int ch) {
        UnicodeBlock block = UnicodeBlock.of(ch);
        return block == UnicodeBlock.HANGUL_SYLLABLES
                || block == UnicodeBlock.HANGUL_JAMO
                || block == UnicodeBlock.HANGUL_COMPATIBILITY_JAMO;
    }
}
