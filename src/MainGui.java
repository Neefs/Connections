import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGUI {
    JFrame frame;
    JPanel panel;
    JButton anagram, connections, wordle, exit;

    public MainGUI() {
        frame = new JFrame("Main GUI");
        panel = new JPanel();
        anagram = new JButton("Anagram");
        connections = new JButton("Connections");
        wordle = new JButton("Wordle");
        exit = new JButton("Exit");

        anagram.setEnabled(false);
        wordle.setEnabled(false);

        anagram.addActionListener(e -> {
            frame.dispose();
        });

        connections.addActionListener(e -> {
            frame.dispose();
            new Connections.ConnectionsGUI();
        });

        wordle.addActionListener(e -> {
            frame.dispose();
        });

        exit.addActionListener(e -> {
            frame.dispose();
        });

        panel.add(anagram);
        panel.add(connections);
        panel.add(wordle);
        panel.add(exit);

        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
