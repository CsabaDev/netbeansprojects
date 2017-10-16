
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.border.EtchedBorder;
import model.CodePeg;

public class GuessPegLabel extends CodePegLabel{
    boolean selected;
    public GuessPegLabel() {
        selected = false;
//        this.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                newGuessPanel.setSelectedIndex(index);
//                guesser.repaint();
//            }
//        });
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (selected) {
            this.setBorder(new 
                    EtchedBorder(2, Color.lightGray, Color.darkGray));
        } else {
            setBorder(null);
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    
}
