package Wordle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import Main.YouWin;
import utils.WordChecker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Wordle {
    JFrame frame;
    JPanel topPanel;
    JPanel gamePanel;
    ArrayList<JLabel[]> groups;
    JLabel[] activeGroup;
    JLabel activeLabel;
    int activeLabelIndex;
    private String gameWord;

    public Wordle(){
        frame = new JFrame("Wordle");
        frame.setSize(500, 500);
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        gameWord = pickWord();
        System.out.println("Game word: " + gameWord);

        topPanel = new JPanel();
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(6, 5, 10, 10));
        groups = new ArrayList<JLabel[]>();
        for (int i = 0; i < 6; i++){
            JLabel[] group = new JLabel[5];
            for (int j = 0; j < 5; j++){
                group[j] = new JLabel();/* {
                    public Dimension getPreferredSize() {
                        Dimension size = super.getPreferredSize();
                        int length = Math.max(size.width, size.height);
                        return new Dimension(length, length);
                    }
                };*/ 
                //TODO: Set label to have a SQUARE boarder
                group[j].setPreferredSize(new Dimension(10,10));
                group[j].setBorder(new LineBorder(Color.BLACK)); // Add a border around each JLabel
                group[j].setHorizontalAlignment(JLabel.CENTER); // Center text horizontally
                group[j].setVerticalAlignment(JLabel.CENTER); // Center text vertically
                gamePanel.add(group[j]);
            }
            groups.add(group);
        }
        activeGroup = groups.get(0);
        activeLabelIndex = 0;
        activeLabel = activeGroup[activeLabelIndex];

        frame.add(topPanel);
        frame.add(gamePanel);

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    String word = "";
                    for (JLabel label : activeGroup){
                        word += label.getText();
                    }
                    if (word.length() != 5){
                        System.out.println("Word must be 5 characters long");
                        //show to user eventually
                        return;
                    }
                    boolean validWord = WordChecker.isValidWord(word);
                    System.out.println("Word: " + word);
                    System.out.println("Valid word: " + validWord);

                    if (!validWord){
                        System.out.println("Invalid word");
                        //show to user eventually
                        return;
                    }else{
                        word = word.toLowerCase();
                        gameWord = gameWord.toLowerCase();
                        
                        Map<String, ArrayList<Integer>> result = checkWord(word, gameWord);
                        int[] checkedLetters = new int[5];
                        for (int i = 0; i < 5; i++){
                            checkedLetters[i] = 0;
                        }
                        for (int index : result.get("rightPlace")){
                            activeGroup[index].setBackground(Color.GREEN);
                            activeGroup[index].setOpaque(true);
                            checkedLetters[index] = 1;
                        }
                        for (int index : result.get("wrongPlace")){
                            activeGroup[index].setBackground(Color.YELLOW);
                            activeGroup[index].setOpaque(true);
                            checkedLetters[index] = 1;
                        }
                        for (int i = 0; i < 5; i++){
                            if (checkedLetters[i] == 0){
                                activeGroup[i].setBackground(Color.GRAY);
                                activeGroup[i].setOpaque(true);
                            }
                        }
                        System.out.println("Right place: " + result.get("rightPlace"));
                        System.out.println("Wrong place: " + result.get("wrongPlace"));

                        if (word.equals(gameWord)){
                            System.out.println("You win!");
                            frame.removeKeyListener(this);
                            new YouWin();
                            frame.dispose();
                            return;
                        }

                        
                    }

                    if (activeGroup == groups.get(5)){
                        System.out.println("Game over");
                        //show to user eventually
                        frame.removeKeyListener(this);
                        return;
                    } else{
                        activeGroup = groups.get(groups.indexOf(activeGroup) + 1);
                        activeLabel = activeGroup[0];
                        activeLabelIndex = 0;
                    }
                }
                if (key == KeyEvent.VK_BACK_SPACE) {
                    System.out.println("Backspace pressed");
                    activeLabel.setText("");
                    if (activeLabelIndex > 0){
                        activeLabelIndex--;
                        activeLabel = activeGroup[activeLabelIndex];
                    } else{
                        activeLabel = activeGroup[0];
                        activeLabelIndex = 0;
                    }
                }
                if ((key >= 65 && key <= 90) || (key >= 97 && key <= 122)) {
                    char letter = (char) key;
                    activeLabel.setText(Character.toString(letter));
                    if (activeLabelIndex < 4){
                        activeLabelIndex++;
                        activeLabel = activeGroup[activeLabelIndex];
                    }
                }
                

            }
        
        });




    }

    public static String pickWord(){
        ArrayList<String> words = new ArrayList<String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/wordle/words.txt"));
            for (String line : in.lines().toArray(String[]::new)){
                if (line.length() == 5){
                    words.add(line);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        if (!words.isEmpty()){
            int index = (int) (Math.random() * words.size());
            return words.get(index);
        } else{
            return "happy"; // default word
        }
    }

    // public static Map<String, ArrayList<Integer>> checkWord(String word, String gameWord) {
    //     Map<String, ArrayList<Integer>> result = new HashMap<>();
    //     result.put("rightPlace", new ArrayList<Integer>());
    //     result.put("wrongPlace", new ArrayList<Integer>());

    //     boolean[] visited = new boolean[gameWord.length()];

    //     for (int i = 0; i < word.length(); i++) {
    //         if (word.charAt(i) == gameWord.charAt(i)) {
    //             result.get("rightPlace").add(i);
    //             visited[i] = true;
    //         }
    //     }

    //     for (int i = 0; i < word.length(); i++) {
    //         if (!visited[i] && gameWord.indexOf(word.charAt(i)) != -1) {
    //             result.get("wrongPlace").add(i);
    //         }
    //     }

    //     return result;
    // }

    public static Map<String, ArrayList<Integer>> checkWord(String word, String gameWord) {
        Map<String, ArrayList<Integer>> result = new HashMap<>();
        result.put("rightPlace", new ArrayList<Integer>());
        result.put("wrongPlace", new ArrayList<Integer>());
    
        int[] freqWord = new int[26];
        int[] freqGameWord = new int[26];
    
        for (int i = 0; i < word.length(); i++) {
            freqWord[word.charAt(i) - 'a']++;
            freqGameWord[gameWord.charAt(i) - 'a']++;
        }
    
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == gameWord.charAt(i)) {
                result.get("rightPlace").add(i);
                freqWord[word.charAt(i) - 'a']--;
                freqGameWord[gameWord.charAt(i) - 'a']--;
            }
        }
    
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != gameWord.charAt(i) && freqGameWord[word.charAt(i) - 'a'] > 0) {
                result.get("wrongPlace").add(i);
                freqGameWord[word.charAt(i) - 'a']--;
            }
        }
    
        return result;
    }


    public static void runGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            JFrame.setDefaultLookAndFeelDecorated(true);
        }
        new Wordle();
    }
}
