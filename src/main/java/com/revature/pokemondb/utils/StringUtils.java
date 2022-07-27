package com.revature.pokemondb.utils;

import java.text.DecimalFormat;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringUtils {
    
    public static String convertToTitleCase(final String words) {
        return Stream.of(words.trim().split("\\s"))
        .filter(word -> word.length() > 0)
        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
        .collect(Collectors.joining(" "));
    }

    /**
     * Convert name to lowercase, replace whitespaces with hyphens, remove periods
     * @param inputString
     * @return
     */
    public static String convertToURIFormat(String inputString) {
        inputString = inputString.toLowerCase();
        inputString = inputString.replace(' ', '-');
        inputString = inputString.replace(".", "");
        return inputString;
    }

    public static String formatDecimalPlaces (float num, int decimalPlaces) {
        String pattern = "#.";
        for (int i = 0; i < decimalPlaces; i++) {pattern += "#";}
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(num);
    }

    public static String convertFromURIFormat (String inputString) {
        inputString = inputString.replace('-', ' ');
        return convertToTitleCase (inputString);
    }
}
