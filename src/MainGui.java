/*
 * Handles all the GUI components of the program
 * 
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MainGui {
    Connections game = new Connections();
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
        for (int i = 0; i < gameButtons.length; i++) {
            gameButtons[i] = new JToggleButton();
            gameButtons[i].setText("pump");
            gamePanel.add(gameButtons[i]);
            gameButtons[i].addActionListener(e -> {
                game.buttonPress(e, gameButtons);
            });
        }
        for (JToggleButton button : gameButtons){
            button = new JToggleButton();
            button.setText("pump");
            gamePanel.add(button);
           button.addActionListener(e -> {
                game.buttonPress(e, gameButtons);
            });
        }

        bottomPanel = new JPanel();
        shuffle = new JButton("Shuffle");
        deselect = new JButton("Deselect");
        submit = new JButton("Submit");
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
