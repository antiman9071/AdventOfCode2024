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
        String[] factors;
        String part;
        int product = 0;
        long total = 0;
        boolean mult = true;
        String[] splitOutput;
        String[] splitOutputDO;
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            splitOutput = line.split("don't()");
            for(int i = 0; i<splitOutput.length; i++){
                if(i%2 == 0){
                    matcherWhole = pattern.matcher(splitOutput[i]);
                    while(matcherWhole.find()){
                        part = matcherWhole.group();
                        matcherPart = patternPart.matcher(part);
                        while(matcherPart.find()){
                            factors = matcherPart.group().split(",");
                            product = Integer.parseInt(factors[1]) * Integer.parseInt(factors[0]);
                            total = product + total;
                        }
                    }
                } else {
                    splitOutputDO = splitOutput[i].split("do()");
                    System.out.println(Arrays.toString(splitOutputDO));
                    System.out.println("");
                    try{
                        matcherWhole = pattern.matcher(splitOutputDO[1]);
                        while(matcherWhole.find()){
                            part = matcherWhole.group();
                            matcherPart = patternPart.matcher(part);
                            while(matcherPart.find()){
                                factors = matcherPart.group().split(",");
                                product = Integer.parseInt(factors[1]) * Integer.parseInt(factors[0]);
                                total = product + total;
                            }
                        }
                    } catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            
        }
        System.out.println(total);
    }
}
