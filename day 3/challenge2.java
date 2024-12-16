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
        Pattern patternDO = Pattern.compile("do\\(\\)");
        Matcher matcherDO;
        Pattern patternDONT = Pattern.compile("don't\\(\\)");
        Matcher matcherDONT;
        String part;
        long total = 0;
        String[] splitOutput;
        String[] splitOutputDO;
        String lineSub = "";
        int start = 0;
        int end = 0;
        boolean mult = true;
        boolean doFound = false;
        boolean dontFound = false;
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            matcherDONT = patternDONT.matcher(line);
            matcherDO = patternDO.matcher(line);
            doFound = matcherDO.find();
            dontFound = matcherDONT.find();
            while(doFound || dontFound){
                if(end == 0){
                    start = 0;
                    try{
                        end = matcherDONT.end();
                    } catch (Exception e){
                        end = line.length()-1;
                        System.out.println(e);
                    }
                    mult = false;
                } else {
                    start = end;
                    if(mult){
                        try{
                            end = matcherDONT.end();
                        } catch (Exception e){
                            end = line.length()-1;
                            System.out.println(e);
                        }
                        mult = false;
                    } else {
                        try{
                            end = matcherDO.end();
                        } catch (Exception e){
                            end = line.length()-1;
                            System.out.println(e);
                        }
                        mult = true;
                    }
                }
                if(!mult){
                    try{
                        lineSub = line.substring(start, end);
                    } catch(Exception e){
                        lineSub = line.substring(start);
                    }
                    total += work(lineSub);
                }
                start = end;
                if(mult){
                    try{
                        end = matcherDONT.end();
                    } catch(Exception e){
                        end = line.length()-1;
                        System.out.println(e);
                    }
                    mult = false;
                } else {
                    try{
                        end = matcherDO.end();
                    } catch (Exception e){
                        end = line.length()-1;
                        System.out.println(e);
                    }
                    mult = true;
                }
                if(!mult){
                    lineSub = line.substring(start, end);
                    total += work(lineSub);
                } 
                doFound = matcherDO.find();
                dontFound = matcherDONT.find();
            }
        }
        lineSub = line.substring(end);
        total += work(lineSub);
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
