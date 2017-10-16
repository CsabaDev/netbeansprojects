package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.CodePeg;
import model.GameModel;

public class GuesserPanel extends JPanel {
    
    GameModel game;
    
    int codeLength;
    int numberOfColors;
    List<CodePeg> codePegs;
    
    CodePegLabel[] pickPegs;
    NewGuessPanel newGuessPanel;
    JButton guessButton;
    JPanel guesser;
    JPanel picker;
    JPanel pickerHolder;
    
    int selectedIndex;
    
    public GuesserPanel(GameView gameView) {

        game = gameView.getGame();
        codeLength = game.getCodeLength();
        numberOfColors = game.getNumberOfColors();
        selectedIndex = -1;
        codePegs = Arrays.asList(CodePeg.values());
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        newGuessPanel = new NewGuessPanel(codeLength);
        guessButton = new JButton("GUESS!");
        guesser = new JPanel(new GridBagLayout());
        pickPegs = new CodePegLabel[numberOfColors];
        picker = new JPanel(new GridBagLayout());
        pickerHolder = new JPanel(new GridLayout(1, 1, 0, 0));
        
        guesser.add(newGuessPanel);
        guesser.add(guessButton);
        add(guesser);

        pickerHolder.setPreferredSize(new Dimension((numberOfColors + 1)/2 * 32, 64));
        GridBagConstraints c = new GridBagConstraints();
        for (int j = 0; j < numberOfColors; j++) {
            pickPegs[j] = new CodePegLabel();
            pickPegs[j].setForeground(CodePeg.values()[j].getColor());
            if (j < (numberOfColors + 1) / 2) {
                c.gridy = 0;
            } else {
                c.gridy = 1;
            }
            picker.add(pickPegs[j], c);
        }
        
        picker.setVisible(newGuessPanel.getSelectedIndex() >= 0);
        pickerHolder.add(picker);
        this.add(pickerHolder);
        
        newGuessPanel.addSelectedIndexChangedListener(new PropertyChangeListener() {
            @Override
            public void propertyChanged(PropertyChangeEvent event) {
                picker.setVisible(newGuessPanel.getSelectedIndex() >= 0);
                repaint();
            }
        });
    }
    
    public CodePegLabel[] getPickPegs() {
        return pickPegs;
    }

    public JButton getGuessButton() {
        return guessButton;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public NewGuessPanel getNewGuessPanel() {
        return newGuessPanel;
    }

    
}
