package adventOfCode2024.day2;
import java.io.*;
import java.util.*;
import adventOfCode2024.fileInput;
public class challenge2 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        String line = "";
        String[] lineSplit = new String[10];
        ArrayList<Integer> test = new ArrayList<Integer>();
        int count = 0;
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            lineSplit = line.split(" ");
            for(String s : lineSplit){
                test.add(Integer.parseInt(s));
            }
            if(tryEverything(test)){
                count++;
            }
            test.clear();
        }
        System.out.println(count);
    }

    public static boolean checkSafe(ArrayList<Integer> test){
        boolean[] is3OrLess = new boolean[test.size()-1];
        boolean[] isIncreasing = new boolean[test.size()-1];
        boolean isIncreasingArray = false;
        ArrayList<Integer> indexOfDuplicates = new ArrayList<>();
        if(test.get(0) < test.get(1)){
            isIncreasingArray = true;
        } else if (test.get(0) > test.get(1)){
            isIncreasingArray = false;
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
        for(int i = 0; i<test.size(); i++){
            for(int j = 0; j<test.size(); j++){
                if(test.get(i) == test.get(j)){
                    if(!(i == j)){
                        indexOfDuplicates.add(i);
                    }
                }
            }
        }
        if(indexOfDuplicates.size() > 1){
            //System.out.println(test + " failed");
            return false;
        }
        int diff = 0;
        for(int i = 0; i<test.size()-1; i++){
            diff = test.get(i) - test.get(i+1);
            if(diff < 0){
                isIncreasing[i] = true;
            } else if(diff > 0){
                isIncreasing[i] = false;
            } else {
                if(!indexOfDuplicates.contains(i)){
                    indexOfDuplicates.add(i);
                } else {
                    continue;
                }
            }
            if(Math.abs(diff) > 3){
                is3OrLess[i] = false;
            } else {
                is3OrLess[i] = true;
            }
        }
        int errors = indexOfDuplicates.size()-1;
        for(int i = 0; i<test.size()-1; i++){
            if(is3OrLess[i] && ((!isIncreasing[i] && !isIncreasingArray) || (isIncreasing[i] && isIncreasingArray))){
                continue;
            } else {
                //System.out.println(test + " failed");
                return false;
            }
        }   
        //System.out.println(test + " passed");
        return true;
    }
    public static boolean tryEverything(ArrayList<Integer> test) {
        ArrayList<Integer> worked = new ArrayList<>();
        HashMap<ArrayList<Integer>, Boolean> answer = new HashMap<>();
        worked.addAll(test);
        for(int i = 0; i<test.size(); i++){
            worked.remove(i);
            if(!worked.isEmpty()){
                answer.put(new ArrayList<Integer>(worked), checkSafe(worked));
            }
            worked.clear();
            worked.addAll(test);
        }
        if(checkSafe(test)){
            //System.out.println(test + " passed");
            return true;
        } else {
            for(ArrayList<Integer> ans : answer.keySet()){
                if(answer.get(ans)){
                    //System.out.println(test + " passed");
                    return true;
                } else {
                    continue;
                }
            }
        }
        //System.out.println(test + " failed");
        return false;
    }
}
