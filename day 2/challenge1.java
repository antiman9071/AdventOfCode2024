package adventOfCode2024.day2;
import java.io.*;
import java.util.*;
import adventOfCode2024.fileInput;
public class challenge1 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        String line = "";
        String[] lineSplit = new String[10];
        ArrayList<Integer> integerArr = new ArrayList<>();
        ArrayList<Boolean> booleanArr = new ArrayList<>();
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            lineSplit = line.split(" ");
            for(String number : lineSplit){
                if(Integer.parseInt(number) != 0){
                    integerArr.add(Integer.parseInt(number));
                }
            }
            booleanArr.add(checkSafe(integerArr));
            integerArr.clear();
        }
        int total = 0;
        for(int i = 0; i<booleanArr.size(); i++){
            if(booleanArr.get(i)){
                total++;
            }
        }
        System.out.println(total);
    }
    
    public static boolean checkSafe(ArrayList<Integer> input){
        ArrayList<Integer> test = new ArrayList<>(input);
        Collections.sort(test);
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
        return false;
    }
}
