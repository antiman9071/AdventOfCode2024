package adventOfCode2024.day3;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import adventOfCode2024.fileInput;
public class challenge2 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        String line = "";
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher matcherWhole;
        Pattern patternPart = Pattern.compile("\\d{1,3},\\d{1,3}");
        Matcher matcherPart;
        Pattern patternDO = Pattern.compile("do\\(\\)");
        Matcher matcherDO;
        Pattern patternDONT = Pattern.compile("don't\\(\\)");
        Matcher matcherDONT;
        String[] factors;
        String part;
        int product = 0;
        long total = 0;
        String[] splitOutput;
        String[] splitOutputDO;
        String lineSub = "";
        int start = 0;
        int end = 0;
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            matcherDO = patternDO.matcher(line);
            matcherDONT = patternDONT.matcher(line);
        }
        System.out.println(total);
    }
}
