import java.io.*;
import java.util.*;
public class challenge1 {
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
        String[] lineSplit = new String[2];
        Map<Integer, Integer> leftList = new HashMap<Integer, Integer>();
        ArrayList <Integer> rightList = new ArrayList<>();
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            lineSplit = line.split("   ");
            leftList.put(Integer.parseInt(lineSplit[0]), 0);
            rightList.add(Integer.parseInt(lineSplit[1]));
        }
        for(int i : rightList){
            if(leftList.containsKey(i)){
                leftList.put(i, leftList.get(i)+1);
            }
        }
        int totalSimScore = 0;
        for(Map.Entry<Integer, Integer> entry:leftList.entrySet()){
            totalSimScore += entry.getKey() * entry.getValue();
        }
        System.out.println(totalSimScore);
    }
}
