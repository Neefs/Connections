package Anagram;

import java.util.*;
import util.WordChecker;

public class logic {
    public static void main(String[] args){
        String[] letterArray = new String[6];
        char[] givenLetters = new char[6];
        char[] userArray;
        ArrayList<String> usedWords = new ArrayList<String>();
        String userGuess = "";
        int count = 0;
        int score = 0;
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        for(int i = 0; i < 6; i++){
            char randomLetter = (char)(rand.nextInt(90-65+1) + 65);
            givenLetters[i] = randomLetter;
            letterArray[i] = String.valueOf(randomLetter);
            
        }

        System.out.println("Generated Letters: ");
        for(String letter : letterArray){
            System.out.print(letter + " ");
        }
        System.out.println();
        
        do{
            //Receives User Input 
            System.out.println("Enter A Word: ");
            userGuess = input.nextLine();
            usedWords.add(userGuess);

            userArray = userGuess.toUpperCase().toCharArray();
            Arrays.sort(userArray);
            Arrays.sort(givenLetters);


            if(countMatchingCharacters(userArray, givenLetters) && isValidWord(userGuess) && usedBefore(userGuess, usedWords)){
                System.out.println("WINNER");
                score += 10;
            } else {
                System.out.println("CANT USE");
                count++;
            }

        } while (count != 5);

        System.out.println("GAME OVER");
        System.out.println("YOUR SCORE IS: " + score);

        

        /*//Receives User Input 
        System.out.println("Enter A Word: ");
        userGuess = input.nextLine();
        usedWords.add(userGuess);

        //isValidWord(userGuess);
        if(isValidWord(userGuess)){
            System.out.println("The word is valid");
        } else {
            System.out.println("the word is not valid");
        }

        //converts the guess into a user array
        //userGuess = userGuess.toUpperCase();
        userArray = userGuess.toUpperCase().toCharArray();
        //sorts the array alphabetically
        Arrays.sort(userArray);
        Arrays.sort(givenLetters);
        
        System.out.println(userArray);
        System.out.println(givenLetters);
        System.out.println(usedWords);

        if(countMatchingCharacters(userArray, givenLetters)){
            System.out.println("works");
        } else {
            System.out.println("not works");
        }*/



    }

    //checks if the word is valid.
    public static boolean isValidWord(String userGuess){
        new WordChecker();
        if(WordChecker.isValidWord(userGuess)){
            return true;
        } else {
            return false;
        }
    }

    //checks if the word has been used before
    public static boolean usedBefore(String userGuess, ArrayList<String> words){
        for(int i = 0; i < words.size(); i++ ){
            if(words.get(i) == userGuess){
                return true;
            }
        }
        return false;
    }

    //checks if the characters given are used
    public static boolean countMatchingCharacters(char userLetters[], char givenLetters[]) {
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
