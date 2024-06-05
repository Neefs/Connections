package Anagram;

import java.util.ArrayList;
import java.util.Random;


public class Game {
    //generates a random string of 6 letters
    public String generateString(){
        Random rand = new Random();
        String generatedString = "";
        String[] givenLetters = new String[6];
        for(int i = 0; i < 6; i++){
            char randomLetter = (char)(rand.nextInt(90-65+1) + 65);
            givenLetters[i] = String.valueOf(randomLetter);
        }


        for(int i = 0; i < givenLetters.length; i++){
            generatedString += givenLetters[i] + " ";
        }




        return generatedString;
    }


    //checks if the word has been used before
    public boolean used (String userGuess, ArrayList<String> words){
        if(words.contains(userGuess)){
            return true;
        } else {
            return false;
        }
    }


    //checks if the characters given are used
    public boolean countMatchingCharacters(char userLetters[], char givenLetters[]) {
        int count = 0;
        int length = userLetters.length;
        for(int i = 0; i < userLetters.length; i++){
            for(int j = 0; j < givenLetters.length; j++){
                if(userLetters[i] == givenLetters[j]){
                    count++;
                    break; // break the inner loop as we found a match
                }
            }
        }
       
        if(count == length){
            return true;
        } else {
            return false;
        }
    }
}