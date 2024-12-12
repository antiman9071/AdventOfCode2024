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
