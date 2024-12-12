package adventOfCode2024.day2;
import java.io.*;
import java.util.*;
import adventOfCode2024.fileInput;
public class challenge1 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        //these 2 lines take input from the file
        String line = "";
        String[] lineSplit = new String[10];
        //this array is used to hold the input and is passed to the function
        ArrayList<Integer> integerArr = new ArrayList<>();
        //this array holds the result
        ArrayList<Boolean> booleanArr = new ArrayList<>();
        while(fileIn.hasNext()){
            //takes the input
            line = fileIn.nextLine();
            lineSplit = line.split(" ");
            //parses the numbers in to a integer ArrayList
            for(String number : lineSplit){
                if(Integer.parseInt(number) != 0){
                    integerArr.add(Integer.parseInt(number));
                }
            }
            //runs the function and stores the answer in another ArrayList
            booleanArr.add(checkSafe(integerArr));
            //clears the ArrayList for use in the next iteration
            integerArr.clear();
        }
        //creates a count
        int total = 0;
        for(int i = 0; i<booleanArr.size(); i++){
            //counts the true values
            if(booleanArr.get(i)){
                total++;
            }
        }
        //prints the total count
        System.out.println(total);
    }
    
    public static boolean checkSafe(ArrayList<Integer> input){
        ArrayList<Integer> test = new ArrayList<>(input);
        //this sorts the input and compares to the original input, if they are the same it means that the list is either continuously increasing or decreasing
        Collections.sort(test);
        if((input.equals(test))){
            for(int i = 0; i<test.size()-1; i++){
                //this loops through the list, if the index and the index after it are equal in a sorted list that is a duplicate and removed
                if(test.get(i)==test.get(i+1)){
                    return false;
                }
                //this then checks for if the difference between indexes are greater than 3
                if(Math.abs(test.get(i)-test.get(i+1)) > 3 ){
                    return false;
                }
            }
            //if nothing is wrong it defaults to true
            return true;
        }
        //same program as above but this time with a reversed list for continuously decreasing lists
        Collections.reverse(test);
        if((input.equals(test))){
            for(int i = 0; i<test.size()-1; i++){
                if(test.get(i)==test.get(i+1)){
                    return false;
                }
                if(Math.abs(test.get(i)-test.get(i+1)) > 3 ){
                    return false;
                }
            }
            return true;
        }
        //the function by default returns false as it means it is not continuously decreasing or increasing
        return false;
    }
}
