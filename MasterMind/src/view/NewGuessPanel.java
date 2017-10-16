
package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;

public class NewGuessPanel extends JPanel{
    
    int selectedIndex;
    int itemCount;
    List<PropertyChangeListener> selectedIndexChangedListeners;
    GuessPegLabel[] guessPegs;

    public NewGuessPanel(int itemCount) {
        selectedIndex = -1;
        selectedIndexChangedListeners = new ArrayList<>();
        
        guessPegs = new GuessPegLabel[itemCount];
        for (int i = 0; i < itemCount; i++) {
            guessPegs[i] = new GuessPegLabel();
            guessPegs[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int index = Arrays.asList(guessPegs).indexOf(e.getSource());
                    setSelectedIndex(index);
                }
            });
            add(guessPegs[i]);
        }
    }

    public void addSelectedIndexChangedListener(PropertyChangeListener selectedIndexChangedListener) {
        selectedIndexChangedListeners.add(selectedIndexChangedListener);
    }
    
    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        for (int i = 0; i < guessPegs.length; i++) {
            guessPegs[i].setSelected(i == selectedIndex);
        }
        repaint();
        for (PropertyChangeListener selectedIndexChangedListener : selectedIndexChangedListeners) {
            selectedIndexChangedListener.propertyChanged(new PropertyChangeEvent(this, "selectedIndex"));
        }
    }

    public GuessPegLabel[] getGuessPegs() {
        return guessPegs;
    }

    public GuessPegLabel getGuessPeg(int i) {
        return guessPegs[i];
    }
    
    
    
}
