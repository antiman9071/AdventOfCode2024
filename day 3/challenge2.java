package adventOfCode2024.day3;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import adventOfCode2024.fileInput;
public class challenge2 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        String fullText = "";
        Pattern patternDO = Pattern.compile("do\\(\\)");
        Matcher matcherDO;
        Pattern patternDONT = Pattern.compile("don't\\(\\)");
        Matcher matcherDONT;
        TreeMap<Integer, Boolean> conditionList = new TreeMap<>();
        long total = 0;
        String[] splitOutput;
        String[] splitOutputDO;
        boolean doFound;
        boolean dontFound;
        while(fileIn.hasNext()){
            fullText += fileIn.nextLine();
        }
        matcherDONT=patternDONT.matcher(fullText);
        matcherDO=patternDO.matcher(fullText);
        doFound = matcherDO.find();
        dontFound = matcherDONT.find();
        while(doFound || dontFound){
            if(doFound && dontFound){
                if(matcherDO.end() > matcherDONT.end()){
                    conditionList.put(matcherDO.end(), true);
                    conditionList.put(matcherDONT.end(), false);
                } else {
                    conditionList.put(matcherDONT.end(), false);
                    conditionList.put(matcherDO.end(), true);
                }
            } else if(doFound){
                conditionList.put(matcherDO.end(), true);
            } else {
                conditionList.put(matcherDONT.end(), false);
            }
            doFound = matcherDO.find();
            dontFound = matcherDONT.find();
        }
        String line;
        int last = 0;
        boolean lastBool = true;
        for(Map.Entry<Integer, Boolean> i: conditionList.entrySet()){
            line = fullText.substring(last, i.getKey());
            if(lastBool){
                total += work(line);
            }
            last = i.getKey();
            lastBool = i.getValue();
        }
        System.out.println(total);
    }
    public static long work(String line){
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher matcherWhole;
        Pattern patternPart = Pattern.compile("\\d{1,3},\\d{1,3}");
        Matcher matcherPart;
        String[] factors;
        int product = 0;
        long total = 0;
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
        return total;
    }
}
