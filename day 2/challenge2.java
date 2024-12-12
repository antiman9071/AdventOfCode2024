package adventOfCode2024.day2;
import java.io.*;
import java.util.*;
import adventOfCode2024.fileInput;
public class challenge2 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        //these 2 lines take input from the file
        String line = "";
        String[] lineSplit = new String[10];
        //this array is used to store the input as integers
        ArrayList<Integer> test = new ArrayList<Integer>();
        //this is the total count
        int count = 0;
        while(fileIn.hasNext()){
            //this goes line by line taking input
            line = fileIn.nextLine();
            lineSplit = line.split(" ");
            //this loop turns them to integers
            for(String s : lineSplit){
                test.add(Integer.parseInt(s));
            }
            //this calls the function to try all possibilities of the inputted line, if there is a way that the values are "safe" then the count is incremented
            if(tryEverything(test)){
                count++;
            }
            //the input is cleared for the next iteration
            test.clear();
        }
        //the output is printed
        System.out.println(count);
    }
    //this is the function to check the "safety" it is used by the tryEverything function and returns a bool
    public static boolean checkSafe(ArrayList<Integer> test){
        //the checks are stored in arrays so that they can be accessed by index at the end
        boolean[] is3OrLess = new boolean[test.size()-1];
        boolean[] isIncreasing = new boolean[test.size()-1];
        boolean isIncreasingArray = false;
        ArrayList<Integer> indexOfDuplicates = new ArrayList<>();
        //this block checks if the array is increasing or decreasing as a whole
        if(test.get(0) < test.get(1)){
            isIncreasingArray = true;
        } else if (test.get(0) > test.get(1)){
            isIncreasingArray = false;
            //on the off chance that the first 2 numbers are duplicates then the 2nd 2 numbers are checked
            //(this is technically redundant as I forgot that this function is after an array has a number removed and therefore cannot take more errors)
        } else {
            indexOfDuplicates.add(0);
            indexOfDuplicates.add(1);
            if(test.get(1) < test.get(2)){
                isIncreasingArray = true;
            } else if (test.get(1) > test.get(2)){
                isIncreasingArray = false;
            } else {
                return false;
            }
        }
        //this checks for duplicates and stores the index in an arrayList
        for(int i = 0; i<test.size(); i++){
            for(int j = 0; j<test.size(); j++){
                if(test.get(i) == test.get(j)){
                    if(!(i == j)){
                        indexOfDuplicates.add(i);
                    }
                }
            }
        }
        //as stated above if there is a duplicate the input fails as a number has already been removed. 
        if(indexOfDuplicates.size() > 1){
            return false;
        }
        //this declares diff which will be used by the iteration to determine if the index is decreasing/increasing and the difference between the two to ensure safe levels
        int diff = 0;
        //this loops through the array
        for(int i = 0; i<test.size()-1; i++){
            diff = test.get(i) - test.get(i+1);
            //checks if increasing/decreasing using true as increasing and false as decreasing
            if(diff < 0){
                isIncreasing[i] = true;
            } else if(diff > 0){
                isIncreasing[i] = false;
            } else {
                //on the off chance it was not caught earlier if there is a duplicate it is caught
                if(!indexOfDuplicates.contains(i)){
                    indexOfDuplicates.add(i);
                } else {
                    continue;
                }
            }
            //checks if the variance is over 3 again if it is less than 3 the output is true otherwise false
            if(Math.abs(diff) > 3){
                is3OrLess[i] = false;
            } else {
                is3OrLess[i] = true;
            }
        }
        //this counts the errors which is again redundant as one error is too much but oh well...
        int errors = indexOfDuplicates.size()-1;
        //this loops and checks whether the list is allowed or not by checking index by index 
        for(int i = 0; i<test.size()-1; i++){
            // if the index has a difference of less than three and it is either increasing in an increasing array or decreasing in a decreasing array then the loop continues.
            if(is3OrLess[i] && ((!isIncreasing[i] && !isIncreasingArray) || (isIncreasing[i] && isIncreasingArray))){
                continue;
            } else {
                //otherwise the function returns false
                return false;
            }
        }   
        //if there are no issues the function returns true 
        return true;
    }
    //this checks for any safe combination by brute force and returns a bool
    public static boolean tryEverything(ArrayList<Integer> test) {
        //this creates the working copy which will copy the input at the start of each iteration
        ArrayList<Integer> worked = new ArrayList<>();
        //this creates the hash map of the arrays used and the answer
        HashMap<ArrayList<Integer>, Boolean> answer = new HashMap<>();
        //creates an initial copy of the input
        worked.addAll(test);
        //loops through removing an index each iteration to check if anything would work
        for(int i = 0; i<test.size(); i++){
            worked.remove(i);
            //adds the result to the hash map
            if(!worked.isEmpty()){
                answer.put(new ArrayList<Integer>(worked), checkSafe(worked));
            }
            //clears the list for use in the next iteration
            worked.clear();
            //copies the input for use in the next iteration
            worked.addAll(test);
        }
        //checks if the original array is safe
        if(checkSafe(test)){
            return true;
        } else {
            //otherwise loops through the hash map checking if any possibility exists where the values are safe 
            for(ArrayList<Integer> ans : answer.keySet()){
                if(answer.get(ans)){
                    return true;
                } else {
                    continue;
                }
            }
        }
        //the function defaults to false as if no possibility is found then the values are not safe
        return false;
    }
}
