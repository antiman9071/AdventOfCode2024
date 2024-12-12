import java.io.*;
import java.util.*;
public class challenge2 {
    public static void main(String[] args) {
        Scanner userIn = new Scanner(System.in);
        String filePath = ""; 
        if(args.length > 0){
            filePath = args[0];
        } else {
            System.out.println("Input a file path");
            filePath = userIn.nextLine();
        }
        Scanner fileIn = null;
        File file = new File(filePath);
        try{
            fileIn = new Scanner(file);
        } catch(FileNotFoundException fnf){
            boolean worked = false;
            while(!worked){
                System.out.println("Your file path does not exist try a different one");
                filePath = userIn.nextLine();
                try{
                    file = new File(filePath);
                    fileIn = new Scanner(file);
                    worked = true;
                } catch(FileNotFoundException fnf2){
                    worked = false;
                }
            }
        }

        String line = "";
        String[] lineSplit = new String[10];
        ArrayList<Integer> integerArr = new ArrayList<>();
        ArrayList<Entry> entryArr = new ArrayList<>();
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            lineSplit = line.split(" ");
            for(String s:lineSplit){
                if(Integer.parseInt(s) != 0){
                    integerArr.add(Integer.parseInt(s));
                }
            }
            entryArr.add(new Entry(integerArr));
            
            integerArr.clear();
        }
        int countOfWorking = 0;
        for(Entry entry: entryArr){
            if(entry.checkSafe()){
                countOfWorking++;   
            }
        }
        System.out.println(countOfWorking);
    }
}
class Entry{
    private ArrayList<Integer> working = new ArrayList<>(); 
    private Map<Integer,Integer> countOfErrors = new HashMap<>();
    private int indexKVP;
    public Entry(ArrayList<Integer> arr){
        working.addAll(arr);
        int errors;
        for(int i = 0; i<arr.size(); i++){
            errors = countOfDuplicates(arr.get(i));
            countOfErrors.put(i, errors);
        }
    }
    //duplicates have been rewritten and should be functioning properly
    //the next thing to do is to create the checking for monotany and difference
    //in both cases if a number causes an issue it is to be added to the issue
    //count and then the entry is to be redone without the value that causes
    //the most errors
    private int countOfDuplicates(int valueOfDup){
        int count = -1;
        for(int i = 0; i<working.size(); i++){
            if(working.get(i) == valueOfDup){
                count++;
            }
        }
        return count;
    }
    private void checkMonotanyDifference(){
        int incOrDec = 0;
        for(int i = 1; i<working.size(); i++){
            if(Math.abs(working.get(i) - working.get(i-1)) > 3){
                countOfErrors.merge(i, 1, Integer::sum);
                countOfErrors.merge(i-1, 1, Integer::sum);
            }
            if((working.get(i) < working.get(i-1)) && incOrDec == 0){
                incOrDec = -1;
            }
            if((working.get(i) > working.get(i-1)) && incOrDec == 0){
                incOrDec = 1;
            }
            if((working.get(i) < working.get(i-1)) && incOrDec > 0){
                countOfErrors.merge(i, 1, Integer::sum);
            }
            if((working.get(i) > working.get(i-1)) && incOrDec < 0){
                countOfErrors.merge(i, 1, Integer::sum);
            }
        }
    }
    public boolean checkSafe(){
        checkMonotanyDifference();
        int max = -1;
        int maxIndex = 0;
        int countOfEqual = 0;
        for(int k: countOfErrors.keySet()){
            if(countOfErrors.get(k) == max && max != 0){
                countOfEqual++;
            }
            if(countOfErrors.get(k) >= max && countOfErrors.get(k) > 0){
                maxIndex = k;
                max = countOfErrors.get(k);
            }
        }
        if(countOfErrors.get(0) == max){
            maxIndex = 0;
        }
        HashMap<Integer, Boolean> equalErrors = new HashMap<>();
        ArrayList<Integer> workingCopy= new ArrayList<>();
        ArrayList<Integer> equalErrorsArrayList = new ArrayList<>();
        workingCopy.addAll(working);
        if(countOfEqual > 0 && max != 0){
            for(int k: countOfErrors.keySet()){
                if(countOfErrors.get(k) == max){
                    equalErrorsArrayList.add(k);
                }
            }   
            for(int key: equalErrorsArrayList){
                working.clear();
                working.addAll(workingCopy);
                countOfErrors.clear();
                working.remove(key);
                int errors;
                for(int i = 0; i<working.size(); i++){
                    errors = countOfDuplicates(working.get(i));
                    countOfErrors.put(i, errors);
                }
                checkMonotanyDifference();
                //stuff is mostly working as it is checking all outcomes the problem is it is checking too much and allowing too may through not totally certain what the problem is
                for(int k: countOfErrors.keySet()){
                    if(countOfErrors.get(k) > 0){
                        System.out.println(working + " and errors " + countOfErrors);
                        System.out.println("false");
                        equalErrors.put(k, false);
                    } else {
                        System.out.println(working + " and errors " + countOfErrors);
                        System.out.println("true");
                        equalErrors.put(k, true);
                        return true;
                    }
                }
            }
            for(int key: equalErrors.keySet()){
                if(equalErrors.get(key)){
                    return true;
                }
            }
        } else {
            countOfErrors.clear();
            working.remove(maxIndex);
            int errors;
            for(int i = 0; i<working.size(); i++){
                errors = countOfDuplicates(working.get(i));
                countOfErrors.put(i, errors);
            }
            checkMonotanyDifference();
            for(int k: countOfErrors.keySet()){
                if(countOfErrors.get(k) > 0){
                    System.out.println(working + " and errors " + countOfErrors);
                    System.out.println("false");
                    return false;
                }
            }
            System.out.println(working + " and errors " + countOfErrors);
            System.out.println("true");
            return true;
        }
        return false;
    }
}
