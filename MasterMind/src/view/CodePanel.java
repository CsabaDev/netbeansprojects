package view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class CodePanel extends JPanel{
    public CodePanel(Color[] codeColors) {
        setLayout(new GridLayout(1, codeColors.length));
        for (int i = 0; i < codeColors.length; i++) {
            CodePegLabel codePeg = new CodePegLabel();
            codePeg.setForeground(codeColors[i]);
            add(codePeg);
        }
    }
}
