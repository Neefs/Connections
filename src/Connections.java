/*  
The main game class that handles
all logic and holds the array data
*/

import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JToggleButton;
import javax.swing.GroupLayout.Group;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Connections {
    private ArrayList<String[]> groups;
    
    /*
     * @pre groups.length() >= 4
     */
    public Connections(ArrayList<String[]> groups) {
        if (groups.size() > 4) {
            groups = pick4(groups);
        }
        if (groups.size() < 4) {
            throw new IllegalArgumentException("groups.length() must be 4 or greater");
        }
        this.groups = groups;
    }

    public String[] getAllWords() {
        String[] allWords = new String[16];
        for(int j = 0; j < 4; j++) {
            String[] group = groups.get(j);
            for(int i = 1; i < group.length; i++) {
                allWords[(j*4) + i - 1] = group[i];
            }
            System.out.println();
        }
        Collections.shuffle(Arrays.asList(allWords));
        return allWords;
    }

    public static ArrayList<String[]> getGroups() {
        ArrayList<String[]> returnedGroups = new ArrayList<String[]>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader("src/connections.json"));
            //System.out.println(a);
            for (Object o : a) {
                String[] group = new String[5];
                for (int i = 0; i < 5; i++) {
                    group[i] = (String) ((JSONArray) o).get(i);
                }
                returnedGroups.add(group);
            }

        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return returnedGroups;
    }

    private static ArrayList<String[]> pick4(ArrayList<String[]> groups) {
        //fix dupe issue
        ArrayList<String[]> pickedGroups = new ArrayList<String[]>();
        
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * groups.size());
            String[] g = groups.get(random);
            
            pickedGroups.add(g);
            
        }
        for(int j = 0; j < 4; j++) {
            String[] group = pickedGroups.get(j);
            for(int i = 0; i < group.length; i++) {
                System.out.print(group[i] + " ");
            }
            System.out.println();
        }
        return pickedGroups;
    }

    
}