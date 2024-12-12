package adventOfCode2024.day1;
import java.io.*;
import java.util.*;
import adventOfCode2024.fileInput;
public class challenge2 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        //these two lines set up the input for the scanner and the array so that I can separate between the two lists 
        String line = "";
        String[] lineSplit = new String[2];
        //this line sets up the hash map for the left list and the following line sets up the ArrayList for the right list
        Map<Integer, Integer> leftList = new HashMap<Integer, Integer>();
        ArrayList <Integer> rightList = new ArrayList<>();
        //this loops through the file to put the lists into the respective hash map and ArrayList
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            lineSplit = line.split("   ");
            leftList.put(Integer.parseInt(lineSplit[0]), 0);
            rightList.add(Integer.parseInt(lineSplit[1]));
        }
        //this loops through and checks if each integer in the right list is a key in the left list and adds 1 to the value if it is
        for(int i : rightList){
            if(leftList.containsKey(i)){
                leftList.put(i, leftList.get(i)+1);
            }
        }
        //this initializes the total similarity score
        int totalSimScore = 0;
        //this loop adds together the total similarity score by multiplying the key by the value
        for(Map.Entry<Integer, Integer> entry:leftList.entrySet()){
            totalSimScore += entry.getKey() * entry.getValue();
        }
        //this outputs the similarity score
        System.out.println(totalSimScore);
    }
}
