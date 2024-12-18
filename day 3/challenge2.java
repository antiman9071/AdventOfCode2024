package adventOfCode2024.day3;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import adventOfCode2024.fileInput;
public class challenge2 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        //these 5 lines grab all of the text and holds it in one string and sets up the regular expression parsing for the conditionals 
        String fullText = "";
        Pattern patternDO = Pattern.compile("do\\(\\)");
        Matcher matcherDO;
        Pattern patternDONT = Pattern.compile("don't\\(\\)");
        Matcher matcherDONT;
        //this tree map holds the positions for all of the conditions and whether they are do or don't based on booleans 
        TreeMap<Integer, Boolean> conditionList = new TreeMap<>();
        //this stores the total
        long total = 0;
        //this loops through all lines and puts them into the full text string
        while(fileIn.hasNext()){
            fullText += fileIn.nextLine();
        }
        //these four lines hold the found regular expressions and the booleans are used to start the loop
        matcherDONT=patternDONT.matcher(fullText);
        matcherDO=patternDO.matcher(fullText);
        boolean doFound = matcherDO.find();
        boolean dontFound = matcherDONT.find();
        //this loop goes through all found patterns 
        while(doFound || dontFound){
            //this if statement checks if both conditions are found and adds them based on which condition comes first(not needed due to usage of tree map but oh well...)
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
            //iterates the loop by checking for both again
            doFound = matcherDO.find();
            dontFound = matcherDONT.find();
        }
        //this string is used to store a substring of fullText based on the locations of the conditionals and whether the last conditional was a do or dont
        String line;
        int last = 0;
        boolean lastBool = true;
        for(Map.Entry<Integer, Boolean> i: conditionList.entrySet()){
            line = fullText.substring(last, i.getKey());
            if(lastBool){
                //if the last conditional was to do then it calls the function based on the code from part 1
                total += work(line);
            }
            //this sets the last values
            last = i.getKey();
            lastBool = i.getValue();
        }
        //this outputs the total
        System.out.println(total);
    }
    //this function is the code from the last challenge
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
