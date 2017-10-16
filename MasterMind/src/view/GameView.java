
package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.GameModel;


public class GameView extends JFrame{
    
    GameModel model;
    GuesserPanel guesserPanel;
    JScrollPane guessesScrollPane;
    JPanel guessesPanel;
    
    public GameView(GameModel model) {
        
        setPreferredSize(new Dimension(300, 450));
        this.model = model;
        
        guessesPanel = new JPanel();
        guessesPanel.setLayout(new GridBagLayout());
        
        guessesScrollPane = new JScrollPane(guessesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setLayout(new BorderLayout());
        add(guessesScrollPane, BorderLayout.CENTER);
        guesserPanel = new GuesserPanel(this);
        add(guesserPanel, BorderLayout.PAGE_END);
        
        Controller controller = new Controller(this, model);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
        pack();
        setVisible(true);
    }
    
    public GameModel getGame() {
        return model;
    }

    public JPanel getGuessesPanel() {
        return guessesPanel;
    }

    public GuesserPanel getGuesserPanel() {
        return guesserPanel;
    }
    
    
    
}
