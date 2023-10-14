import org.apache.hadoop.util.hash.Hash;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static Set<Character> limitedCharacters = Stream.of('m', 'n', 'o', 'p', 'q').collect(Collectors.toSet());

    public static boolean checkStartCharacter(String word, Set<Character> limitation) {
        if (word == null || word.length() == 0) return false;
        char ch = word.charAt(0);
        if (!Character.isAlphabetic(ch)) return false;

        return limitation.contains(Character.toLowerCase(ch));
    }
}
