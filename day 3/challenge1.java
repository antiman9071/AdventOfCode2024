package adventOfCode2025.day3;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import adventOfCode2024.fileInput;
public class challenge1 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        //these 8 lines set up the variables used for the loop with the string array being used as the split output
        String line = "";
        //these four specific lines handle the regular expression parsing of the operation and further grabbing only the numbers
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher matcherWhole;
        Pattern patternPart = Pattern.compile("\\d{1,3},\\d{1,3}");
        Matcher matcherPart;
        String[] factors;
        int product = 0;
        long total = 0;
        //this is the working loop of the program
        while(fileIn.hasNext()){
            //this gets the next line and then uses the matcher above to find the operations
            line = fileIn.nextLine();
            matcherWhole = pattern.matcher(line);
            //this loops through all of the found operations and inside the operation separates the number and the separating comma
            while(matcherWhole.find()){
                line = matcherWhole.group();
                matcherPart = patternPart.matcher(line);
                while(matcherPart.find()){
                    //once the numbers are separated the comma is used to split the string and then the numbers are multiplied and added to the total
                    factors = matcherPart.group().split(",");
                    product = Integer.parseInt(factors[1]) * Integer.parseInt(factors[0]);
                    total = product + total;
                }
            }
        }
        //this outputs the total
        System.out.println(total);
    }
}
