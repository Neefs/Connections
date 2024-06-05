package Anagram;

import javax.swing.*;

import Main.YouLose;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

import utils.WordChecker;


public class Anagrams implements ActionListener{
   JLabel scrambledWord, label;
   JTextField guessField;
   JButton submitButton;
   JLabel resultLabel;
   JTextArea foundWordsArea;
   JLabel scoreLabel;
   JLabel mistakeLabel;
   JFrame frame;
   Game gameObj = new Game();


   //initialize variables
   private char[] userArray;
   private char[] givenLetters = new char[6];
   private String generatedLetters;
   private ArrayList<String> usedWords = new ArrayList<>();
   private int score = 0;
   private int mistakes = 0;


   public Anagrams(){
       //set up the frame
       frame = new JFrame();
       frame.setTitle("ANAGRAMS");
       frame.setSize(500, 500);  
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(new GridLayout(8, 1));


       //initialize components
       label = new JLabel("Your Letters Are", SwingConstants.CENTER);


       //sets the generated letters
       generatedLetters = gameObj.generateString();
       givenLetters = generatedLetters.toCharArray();


       scrambledWord = new JLabel(generatedLetters, SwingConstants.CENTER);
       resultLabel = new JLabel("", SwingConstants.CENTER);
       scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
       mistakeLabel = new JLabel("Mistakes: 0", SwingConstants.CENTER);
       
       guessField = new JTextField();


       submitButton = new JButton("SUBMIT");
       submitButton.setActionCommand("submit");
       submitButton.addActionListener(this);


       foundWordsArea = new JTextArea();
       foundWordsArea.setEditable(false);


       frame.add(label);
       frame.add(scrambledWord);
       frame.add(guessField);
       frame.add(submitButton);
       frame.add(resultLabel);
       frame.add(foundWordsArea);
       frame.add(scoreLabel);
       frame.add(mistakeLabel);
    

       frame.setVisible(true);
   }
 
   public void actionPerformed(ActionEvent e){
       String userGuess = guessField.getText().trim();
       userArray = userGuess.toUpperCase().toCharArray();
       Arrays.sort(userArray);
       Arrays.sort(givenLetters);


       if(gameObj.countMatchingCharacters(userArray, givenLetters) && WordChecker.isValidWord(userGuess) && !gameObj.used(userGuess, usedWords)){
            resultLabel.setText(userGuess.toUpperCase() + " IS A VALID WORD!");
            score += 10;
            usedWords.add(userGuess);
            scoreLabel.setText("SCORE: " + score);
            foundWordsArea.append(userGuess.toUpperCase() + " ");
            guessField.setText("");
        } else if(mistakes == 5){
            resultLabel.setText("GAME OVER");
            scoreLabel.setText("YOUR SCORE IS: " + score);
            mistakeLabel.setText("MISTAKES: " + mistakes);
            guessField.setEditable(false);
            submitButton.setEnabled(false);
            new YouLose();
        }else if(!(gameObj.countMatchingCharacters(userArray, givenLetters) && WordChecker.isValidWord(userGuess) && !gameObj.used(userGuess, usedWords))){
            resultLabel.setText("CANT USE THIS WORD!");
            mistakes += 1;
            mistakeLabel.setText("MISTAKES: " + mistakes);
            System.out.println(mistakes);
        }
   }


   //create and show GUI
   private static void runGUI(){
       JFrame.setDefaultLookAndFeelDecorated(true);
       new Anagrams();
   }




   public static void main(String[] args) {
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run(){
               runGUI();
           }
       });
   }
}