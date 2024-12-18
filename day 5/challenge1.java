package adventOfCode2024.day5;
import java.io.*;
import java.util.*;
import adventOfCode2024.fileInput;
public class challenge1 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        //used the fact that all rules are 5 characters 
        //found this by writing a small code to check that all rules were 5 characters
        String line = "";
        String[] lineSplit;
        HashMap<Integer, Integer> rules = new HashMap<>();
        ArrayList<Integer> updates = new ArrayList<>();
        List<ArrayList<Integer>> updatesCorrect = new ArrayList<ArrayList<Integer>>();
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            if(line.length() == 5){
                lineSplit = line.split("|");
                rules.put(Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[1]));
            } else {
                if(line.length() > 0){
                    lineSplit = line.split(",");
    outerLoop:
                    for(int i = 0; i<lineSplit.length; i++){
                        for(Map.Entry<Integer, Integer> entry : rules.entrySet()){
                            if(Integer.parseInt(lineSplit[i]) == entry.getKey()){
                                if(updates.contains(entry.getValue())){
                                    break outerLoop;
                                }
                            }
                            if(Integer.parseInt(lineSplit[i]) == entry.getValue()){
                                if(!updates.contains(entry.getKey())){
                                    break outerLoop;
                                }
                            }
                        }
                        updates.add(Integer.parseInt(lineSplit[i]));
                    }
                    updatesCorrect.add(updates);
                    updates.clear();
                }
            }
        }
        int total = 0;
        for(ArrayList<Integer> i : updatesCorrect){
            if(i.size() > 1){
                total += i.get((i.size()/2)+1);
            } else if(i.size() == 1){
                total += i.get(0);
            } else {
                total += 0;
            }
        }
        System.out.println(total);
    }
}
