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
        HashMap <Integer, Integer> rules = new HashMap<>();
        List<List<Integer>> relevantRules = new ArrayList<List<Integer>>();
        ArrayList<ArrayList<Integer>> updatesCorrect = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> updatesWorking = new ArrayList<Integer>();
        boolean save = true;
        ArrayList<Integer> working = new ArrayList<>();
        HashMap <Integer, Integer> translation = new HashMap<>();
        int number = 0;
        int reverse = 0;
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            if(line.length() == 5){
                lineSplit= line.split("\\|");
                rules.put(Integer.parseInt(lineSplit[0]) , Integer.parseInt(lineSplit[1]));
            } else {
                if(line.length() > 0){
                    lineSplit = line.split(",");
                    for(int i = 0; i<lineSplit.length; i++){
                        working.add(Integer.parseInt(lineSplit[i]));
                    }
                    reverse = working.size()-1;
                    for(int i = 0; i<working.size(); i++){
                        if(!translation.containsKey(working.get(i))){
                            translation.put(working.get(i),i);
                            number = i;
                        } else {
                            number = translation.get(working.get(i));
                        }
                        for(Map.Entry<Integer, Integer> entry : rules.entrySet()){
                            if(working.get(i) == entry.getKey()){
                                if(working.contains(entry.getValue())){
                                    if(translation.containsKey(entry.getValue())){
                                        relevantRules.add(new ArrayList<>(Arrays.asList(number, translation.get(entry.getValue()))));
                                    } else {
                                        relevantRules.add(new ArrayList<>(Arrays.asList(number, reverse)));
                                        translation.put(entry.getValue(), reverse);
                                        reverse -= 1;
                                    }
                                }
                            }
                            if(working.get(i) == entry.getValue()){
                                if(working.contains(entry.getKey())){
                                    if(translation.containsKey(entry.getValue())){
                                        relevantRules.add(new ArrayList<>(Arrays.asList(number, translation.get(entry.getValue()))));
                                    } else {
                                        relevantRules.add(new ArrayList<>(Arrays.asList(number, reverse)));
                                        translation.put(entry.getValue(), reverse);
                                        reverse -= 1;
                                    }
                                }
                            }
                        }
                    }
                    for(int i : topoSort(relevantRules, working.size())){
                        for(Map.Entry<Integer, Integer> entry : translation.entrySet()){
                            if(entry.getValue() == i){
                                updatesWorking.add(entry.getKey());
                            }   
                        }   
                    }
                    updatesCorrect.add(updatesWorking);
                    relevantRules.clear();
                    working.clear();
                    translation.clear();
                }
            }
        }
        int total = 0;
        for(ArrayList<Integer> i : updatesCorrect){
            if(i.size() > 1){
                total += i.get(((i.size()-1)/2));
            } else if(i.size() == 1){
                total += i.get(0);
            } else {
                total += 0;
            }
        }
        System.out.println(total);
    }
    static void util(int index, List<List<Integer>> adj, boolean[] visited, LinkedList<Integer> stack){
        visited[index] = true;

        for(int i : adj.get(index)){
            if(!visited[i]){
                util(i, adj, visited, stack);
            }
        }

        stack.addFirst(index);
    }
    static ArrayList<Integer> topoSort(List<List<Integer>> adj, int size){
        LinkedList<Integer> stack = new LinkedList<>();
        boolean[] visited = new boolean[size];

        for(int i = 0; i<size; i++){
            if(!visited[i]){
                util(i, adj, visited, stack);
            }
        }
        ArrayList<Integer> output = new ArrayList<>();
        Integer out = stack.pollLast();
        while(out != null){
            output.add(out);
            out = stack.pollLast();
        }
        return output;
    }
}
