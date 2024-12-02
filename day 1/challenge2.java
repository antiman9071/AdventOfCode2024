import java.io.*;
import java.util.*;
public class challenge1 {
    public static void main(String[] args) {
        //these first 13 lines are to get the file path from the user either as an argument or as a user input
        Scanner userIn = new Scanner(System.in);
        String filePath = ""; 
        if(args.length > 0){
            filePath = args[0];
        } else {
            System.out.println("Input a file path");
            filePath = userIn.nextLine();
        }
        //this is to create the file and file scanner
        Scanner fileIn = null;
        File file = new File(filePath);
        //this is the try/catch loop to open the file and catch if a file has been inputted incorrectly
        try{
            fileIn = new Scanner(file);
        } catch(FileNotFoundException fnf){
            boolean worked = false;
            //this loop is to repeat until the user inputs the correct file
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
