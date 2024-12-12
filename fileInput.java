package adventOfCode2024;
import java.util.*;
import java.io.*;
public class fileInput {
    public static Scanner fileIn(String[] args){
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
        //this returns the object to the scanner
        return fileIn;
    }
}
