package io.github.flightsimroutes.util;

public class StringUtils {
    
    public static String getFirstCharacters(String text, int charactersCount) {
        int offset = Math.min(charactersCount, text.length());
        return text.substring(0, offset);
    }
}
