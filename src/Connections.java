/*  
The main game class that handles
all logic and holds the array data
*/

import java.awt.event.ActionEvent;

import javax.swing.JToggleButton;

public class Connections {
    //later

    public Connections() {
        //later
    }


    public void buttonPress(ActionEvent e, JToggleButton[] board) {
        JToggleButton buttonPressed = (JToggleButton) e.getSource();
        int selected = 0;
        for (JToggleButton button : board) {
            if (button.isSelected()) {
                selected++;
            }
        }
        if (selected > 4) {
            buttonPressed.setSelected(false);
        }

    }
}