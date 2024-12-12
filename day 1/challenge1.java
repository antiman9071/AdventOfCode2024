package adventOfCode2024.day1;
import java.io.*;
import java.util.*;
import adventOfCode2024.fileInput;
public class challenge1 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        //these four lines set up the input for the scanner, the array so that I can separate between the two lists and the ArrayList for each list
        String line = "";
        String[] lineSplit = new String[2];
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        //this loops through the file to put the lists into ArrayLists
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            lineSplit = line.split("   ");
            leftList.add(Integer.parseInt(lineSplit[0]));
            rightList.add(Integer.parseInt(lineSplit[1]));
        }
        //these three lines use the collections interface to sort the ArrayLists and initialize the variable for the end result
        Collections.sort(leftList);
        Collections.sort(rightList);
        int totalOff = 0;
        //this loops through all of the now sorted values, gets the difference, and adds it to the total of the difference of the list
        for(int i = 0; i<leftList.size(); i++){
            totalOff += Math.abs(leftList.get(i)-rightList.get(i));
        }
        //this outputs the end result
        System.out.println(totalOff);
    }
}
