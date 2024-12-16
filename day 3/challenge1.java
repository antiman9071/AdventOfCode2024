package adventOfCode2025.day3;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import adventOfCode2024.fileInput;
public class challenge1 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        String line = "";
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher matcherWhole;
        Pattern patternPart = Pattern.compile("\\d{1,3},\\d{1,3}");
        Matcher matcherPart;
        String[] factors;
        int product = 0;
        long total = 0;
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            matcherWhole = pattern.matcher(line);
            while(matcherWhole.find()){
                line = matcherWhole.group();
                matcherPart = patternPart.matcher(line);
                while(matcherPart.find()){
                    factors = matcherPart.group().split(",");
                    product = Integer.parseInt(factors[1]) * Integer.parseInt(factors[0]);
                    total = product + total;
                }
            }
        }
        System.out.println(total);
    }
}
