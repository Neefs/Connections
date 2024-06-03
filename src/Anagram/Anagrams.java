package Anagram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class Anagrams implements ActionListener{
   static final String secretWord = "senput";
   JLabel scrambledWord, label;
   JTextField guessField;
   JButton submitButton;
   JLabel resultLabel;
   JTextArea foundWordsArea;
   JLabel scoreLabel;
   JFrame frame;
   //Set<String> foundWords;




   public Anagrams(){
       //set up the frame
       frame = new JFrame();
       frame.setTitle("ANAGRAMS");
       frame.setSize(500, 500);   
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(new GridLayout(8, 1));


       //inintialize components
       label = new JLabel("Your Letters Are", SwingConstants.CENTER);
       scrambledWord = new JLabel("ABCDEFG", SwingConstants.CENTER);
       resultLabel = new JLabel("", SwingConstants.CENTER);
       scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);


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


       //frame.pack();
       frame.setVisible(true);
   }


  
   public void actionPerformed(ActionEvent e){
       String userGuess = guessField.getText().trim().toLowerCase();
       resultLabel.setText(userGuess);
       scoreLabel.setText("Score: " + 10);
       //foundWords.add(userGuess);
       foundWordsArea.append(userGuess + "\n");
       guessField.setText("");
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


