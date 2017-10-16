package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JLabel;
import model.ResultPeg;

public class ResultPegLabel extends JLabel{
    
    ResultPeg resultPeg;

    public ResultPegLabel(ResultPeg resultPeg) {
        this.resultPeg = resultPeg;
        this.setSize(16, 16);
        this.setPreferredSize(new Dimension(16, 16));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        switch (resultPeg) {
            case WHITE:
                g.drawOval(0, 0, 14, 14);
                g.setColor(Color.WHITE);
            case BLACK:
                g.fillOval(0, 0, 14, 14);
        }
    }
    
}
