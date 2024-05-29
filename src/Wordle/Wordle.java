package Wordle;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import utils.WordChecker;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Wordle {
    JFrame frame;
    JPanel topPanel;
    JPanel gamePanel;
    ArrayList<JLabel[]> groups;
    JLabel[] activeGroup;
    JLabel activeLabel;
    int activeLabelIndex;

    public Wordle(){
        frame = new JFrame("Wordle");
        frame.setSize(500, 500);
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

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
