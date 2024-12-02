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
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        while(fileIn.hasNext()){
            line = fileIn.nextLine();
            lineSplit = line.split("   ");
            leftList.add(Integer.parseInt(lineSplit[0]));
            rightList.add(Integer.parseInt(lineSplit[1]));
        }
        Collections.sort(leftList);
        Collections.sort(rightList);
        int totalOff = 0;
        for(int i = 0; i<leftList.size(); i++){
            totalOff += Math.abs(leftList.get(i)-rightList.get(i));
        }
        System.out.println(totalOff);
    }
}
