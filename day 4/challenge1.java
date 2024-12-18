package adventOfCode2024.day4;
import java.io.*;
import java.util.*;
import adventOfCode2024.fileInput;
public class challenge1 {
    public static void main(String[] args) {
        //this line calls to the parent class to do file input
        Scanner fileIn = fileInput.fileIn(args);
        //these 8 lines set up the variables used for the loop with the string array being used as the split output
        String line = "";
        //these four specific lines handle the regular expression parsing of the operation and further grabbing only the numbers
        List<List<Character>> matrixOfInput = new ArrayList<List<Character>>();
        int current = 0;
        int count = 0;
        while(fileIn.hasNext()){
            matrixOfInput.add(new ArrayList<Character>());
            line = fileIn.nextLine();
            for(int i = 0; i<line.length(); i++){
                matrixOfInput.get(current).add(line.charAt(i));
            }
            current++;
        }
        boolean upPossible = false;
        boolean downPossible = false;
        boolean leftPossible = false;
        boolean rightPossible = false;
        for(int i = 0; i<matrixOfInput.size(); i++){
            if(i > 2){
                upPossible = true;
            } else {
                upPossible = false;
            }
            if((matrixOfInput.size() - i) > 3){
                downPossible = true;
            } else {
                downPossible = false;
            }
            for(int j = 0; j<matrixOfInput.get(i).size(); j++){
                if(j > 2){
                    leftPossible = true;
                } else {
                    leftPossible = false;
                }
                if((matrixOfInput.get(i).size() - j) > 3){
                    rightPossible = true;
                } else {
                    rightPossible = false;
                }
                if(matrixOfInput.get(i).get(j) == 'X'){
                    if(upPossible && matrixOfInput.get(i-1).get(j) == 'M'){
                        if(matrixOfInput.get(i-2).get(j) == 'A'){
                            if(matrixOfInput.get(i-3).get(j) == 'S'){
                                count++;
                            }
                        }
                    }
                    if(upPossible && leftPossible && matrixOfInput.get(i-1).get(j-1) == 'M'){
                        if(matrixOfInput.get(i-2).get(j-2) == 'A'){
                            if(matrixOfInput.get(i-3).get(j-3) == 'S'){
                                count++;
                            }
                        }
                    }
                    if(upPossible && rightPossible && matrixOfInput.get(i-1).get(j+1) == 'M'){
                        if(matrixOfInput.get(i-2).get(j+2) == 'A'){
                            if(matrixOfInput.get(i-3).get(j+3) == 'S'){
                                count++;
                            }
                        }
                    }
                    if(leftPossible && matrixOfInput.get(i).get(j-1) == 'M'){
                        if(matrixOfInput.get(i).get(j-2) == 'A'){
                            if(matrixOfInput.get(i).get(j-3) == 'S'){
                                count++;
                            }
                        }
                    }
                    if(rightPossible && matrixOfInput.get(i).get(j+1) == 'M'){
                        if(matrixOfInput.get(i).get(j+2) == 'A'){
                            if(matrixOfInput.get(i).get(j+3) == 'S'){
                                count++;
                            }
                        }
                    }
                    if(downPossible && matrixOfInput.get(i+1).get(j) == 'M'){
                        if(matrixOfInput.get(i+2).get(j) == 'A'){
                            if(matrixOfInput.get(i+3).get(j) == 'S'){
                                count++;
                            }
                        }
                    }
                    if(downPossible && leftPossible && matrixOfInput.get(i+1).get(j-1) == 'M'){
                        if(matrixOfInput.get(i+2).get(j-2) == 'A'){
                            if(matrixOfInput.get(i+3).get(j-3) == 'S'){
                                count++;
                            }
                        }
                    }
                    if(downPossible && rightPossible && matrixOfInput.get(i+1).get(j+1) == 'M'){
                        if(matrixOfInput.get(i+2).get(j+2) == 'A'){
                            if(matrixOfInput.get(i+3).get(j+3) == 'S'){
                                count++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
