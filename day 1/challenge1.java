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
        //these four lines set up the input for the scanner, the array so that I can separate between the two lists and the ArrayList for each list
        String line = "";
        String[] lineSplit = new String[2];
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        //this loops through the file to put the lists into ArrayLists
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            lineSplit = line.split("   ");
            leftList.add(Integer.parseInt(lineSplit[0]));
            rightList.add(Integer.parseInt(lineSplit[1]));
        }
        //these three lines use the collections interface to sort the ArrayLists and initialize the variable for the end result
        Collections.sort(leftList);
        Collections.sort(rightList);
        int totalOff = 0;
        //this loops through all of the now sorted values, gets the difference, and adds it to the total of the difference of the list
        for(int i = 0; i<leftList.size(); i++){
            totalOff += Math.abs(leftList.get(i)-rightList.get(i));
        }
        //this outputs the end result
        System.out.println(totalOff);
    }
}
