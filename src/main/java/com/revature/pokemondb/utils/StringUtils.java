package com.revature.pokemondb.utils;

import java.text.DecimalFormat;
import java.util.stream.Collectors;
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

    public static int getNumberFromRomanNumeral (String romanNumeral) {
        switch (romanNumeral.toLowerCase()) {
            case ("i"):
                return 1;
            case ("ii"):
                return 2;
            case ("iii"):
                return 3;
            case ("iv"):
                return 4;
            case ("v"):
                return 5;
            case ("vi"):
                return 6;
            case ("vii"):
                return 7;
            case ("viii"):
                return 8;
            case ("ix"):
                return 9;
            case ("x"):
                return 10;
            case ("xi"):
                return 11;
            case ("xii"):
                return 12;
            case ("xiii"):
                return 13;
            case ("xiv"):
                return 14;
            case ("xv"):
                return 15;
            case ("xvi"):
                return 16;
            case ("xvii"):
                return 17;
            case ("xviii"):
                return 18;
            case ("xix"):
                return 19;
            case ("xx"):
                return 20;
            default:
                return 0;
        }
    }
}
