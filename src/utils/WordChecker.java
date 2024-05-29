package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class WordChecker {
   public static boolean isValidWord(String word) {
       // System.out.println(word);
       
       try {
            word = word.toLowerCase().trim();
            BufferedReader in = new BufferedReader(new FileReader("src/utils/dictionary.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                if (str.indexOf(word) != -1) {
                    return true;
                }
            }
            in.close();
       } catch (IOException e) {
        System.out.println(e);
       }
       //65-90 97-122

       return false;
   }


}