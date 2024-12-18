package adventOfCode2024.day4;
import java.io.*;
import java.util.*;
import adventOfCode2024.fileInput;
public class challenge2 {
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
        for(int i = 1; i<matrixOfInput.size()-1; i++){
            for(int j = 1; j<matrixOfInput.get(i).size()-1; j++){
                if(matrixOfInput.get(i).get(j) == 'A'){
                    if(matrixOfInput.get(i-1).get(j-1) == 'M'){
                        if(matrixOfInput.get(i-1).get(j+1) == 'M'){
                            if(matrixOfInput.get(i+1).get(j-1) == 'S'){
                                if(matrixOfInput.get(i+1).get(j+1) == 'S'){
                                    count++;
                                }
                            }
                        }
                    }
                    if(matrixOfInput.get(i+1).get(j+1) == 'M'){
                        if(matrixOfInput.get(i+1).get(j-1) == 'M'){
                            if(matrixOfInput.get(i-1).get(j-1) == 'S'){
                                if(matrixOfInput.get(i-1).get(j+1) == 'S'){
                                    count++;
                                }
                            }
                        }
                    }
                    if(matrixOfInput.get(i-1).get(j+1) == 'M'){
                        if(matrixOfInput.get(i+1).get(j+1) == 'M'){
                            if(matrixOfInput.get(i-1).get(j-1) == 'S'){
                                if(matrixOfInput.get(i+1).get(j-1) == 'S'){
                                    count++;
                                }
                            }
                        }
                    }
                    if(matrixOfInput.get(i-1).get(j-1) == 'M'){
                        if(matrixOfInput.get(i+1).get(j-1) == 'M'){
                            if(matrixOfInput.get(i-1).get(j+1) == 'S'){
                                if(matrixOfInput.get(i+1).get(j+1) == 'S'){
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
