package view;

import java.awt.GridLayout;
import javax.swing.JPanel;
import model.ResultPeg;

public class ResultPegPanel extends JPanel{
    
    public ResultPegPanel(ResultPeg[] evaluation) {
        int el = evaluation.length;
        this.setLayout(new GridLayout(2, (el + 1)/2));
        for (int i = 0; i < el; i++) {
            this.add(new ResultPegLabel(evaluation[i]));
        }
    }
}
