/*
 * Handles all the GUI components of the program
 * 
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MainGui {
    Connections game = new Connections(Connections.getGroups());
    JFrame frame;

    JPanel topPanel;
    JLabel introLabel;

    JPanel gamePanel;
    JToggleButton[] gameButtons;

    JPanel bottomPanel;
    JButton shuffle, deselect, submit;

    public MainGui() {
        
        frame = new JFrame("Connections");
        frame.setSize(680, 560);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        topPanel = new JPanel();
        introLabel = new JLabel("Create 4 groups of 4!");
        topPanel.add(introLabel);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(4, 4, 10, 10));
        gameButtons = new JToggleButton[16];
        String[] words = game.getAllWords();
        for (int i = 0; i < gameButtons.length; i++) {
            gameButtons[i] = new JToggleButton();
            gameButtons[i].setText(words[i]);
            gamePanel.add(gameButtons[i]);
            gameButtons[i].addActionListener(e -> {
                JToggleButton buttonPressed = (JToggleButton) e.getSource();
                int selected = 0;
                for (JToggleButton button : gameButtons) {
                    if (button.isSelected()) {
                        selected++;
                    }
                }
                if (selected > 4) {
                    buttonPressed.setSelected(false);
                }
            });
        }
        

        bottomPanel = new JPanel();
        shuffle = new JButton("Shuffle");
        shuffle.addActionListener(e -> {
            for (JToggleButton button : gameButtons) {
                System.out.print(button.getText());
            }
            Collections.shuffle(Arrays.asList(gameButtons));
            System.out.println("");
            for (JToggleButton button : gameButtons) {
                System.out.print(button.getText());
            }
            System.out.println("");

            gamePanel.removeAll();
            for (JToggleButton button : gameButtons) {
                gamePanel.add(button);
            }
            gamePanel.revalidate();
        });


        deselect = new JButton("Deselect");
        deselect.addActionListener(e -> {
            for (JToggleButton button : gameButtons) {
                button.setSelected(false);
            }
        });

        submit = new JButton("Submit");
        submit.addActionListener(e -> {
            // move to top and change color to show group and disable
            ArrayList<String> groups = new ArrayList<String>();
            for (JToggleButton button : gameButtons) {
                if (button.isSelected()) {
                    groups.add(button.getText());
                }
            }
            String groupName = game.checkGroup(groups.toArray(new String[groups.size()]));
            System.out.println(groupName);
        });

        bottomPanel.add(shuffle);
        bottomPanel.add(deselect);
        bottomPanel.add(submit);

        

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);


    }












    public static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new MainGui();
    }




    // private static void runGUI() {
    //     try {
    //         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    //     } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
    //             | UnsupportedLookAndFeelException e) {
    //         e.printStackTrace();
    //         JFrame.setDefaultLookAndFeelDecorated(true);
    //     }
    //     new MainGui();
    // }
}
