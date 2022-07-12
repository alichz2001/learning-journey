public class Match {
    private static final char DOT_CHAR = '.';
    private static final char STAR_CHAR = '*';

    public static void main(String[] args) {
        System.out.println("Input: s = \"aa\", p = \"a\"");
        System.out.println("Output: " + match("aa", "a"));
        System.out.println();

        System.out.println("Input: s = \"aa\", p = \"a*\"");
        System.out.println("Output: " + match("aa", "a*"));
        System.out.println();

        System.out.println("Input: s = \"ab\", p = \".*\"");
        System.out.println("Output: " + match("ab", ".*"));
        System.out.println();
    }

    public static Boolean match(String str, String pattern) {
        /* 
        special condotion whene last pattern character is '*'.
        in this condition add a specific character to end of pattern and string.
        */
        Boolean cond = pattern.charAt(pattern.length() - 1) == '*';
        pattern += cond ? "|" : "";
        str += cond ? "|" : "";
        
        return match(0, 0, str, pattern);
    }

    private static Boolean match(Integer strIndex, Integer patternIndex, String str, String pattern) {
        if (patternIndex == pattern.length() && strIndex == str.length()) return true;
        if (patternIndex == pattern.length() && strIndex < str.length()) return false;
        if (strIndex == str.length() && patternIndex < pattern.length()) return false;

        char strChar = str.charAt(strIndex);
        char patternChar = pattern.charAt(patternIndex);
        
        switch (patternChar) {
            case DOT_CHAR:
                return match(strIndex + 1, patternIndex + 1, str, pattern);
            case STAR_CHAR:
                return (patternIndex > 0 && pattern.charAt(patternIndex - 1) == DOT_CHAR)
                ? match(strIndex + 1, patternIndex, str, pattern) 
                || match(strIndex, patternIndex + 1, str, pattern)
                : match(strIndex, patternIndex + 1, str, pattern)
                || ((strIndex > 0 && str.charAt(strIndex - 1) == strChar)
                ? match(strIndex + 1, patternIndex, str, pattern)
                : match(strIndex, patternIndex + 1, str, pattern));
            default:
                return (strChar == patternChar) 
                && match(strIndex + 1, patternIndex + 1, str, pattern);
        }
    }
}
