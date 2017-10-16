/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JLabel;

public class CodePegLabel extends JLabel{
    public CodePegLabel() {
        setForeground(Color.GRAY);
        this.setSize(32, 32);
        this.setPreferredSize(new Dimension(32, 32));
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getForeground());
        g.fillOval(2, 2, 28, 28);
    }
}
