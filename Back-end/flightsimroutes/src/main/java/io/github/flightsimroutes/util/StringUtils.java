package io.github.flightsimroutes.util;

public class StringUtils {
    
    public static String getFirstCharacters(String text, int charactersCount) {
        int offset = Math.min(charactersCount, text.length());
        return text.substring(0, offset);
    }

    public static String removeLastCaracters(String original, int quantity) {
        if (original != null && original.length() >= quantity) {
            return original.substring(0, original.length() - quantity);
        } else {
            return original;
        }
    }
}
