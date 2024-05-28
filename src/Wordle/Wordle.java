package Wordle;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
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

        frame.add(topPanel);
        frame.add(gamePanel);

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("UP");
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("DOWN");
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("LEFT");
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("RIGHT");
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
