
package controller;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.CodePeg;
import model.GameModel;
import model.GameState;
import view.CodePanel;
import view.CodePegLabel;
import view.GameView;
import view.GuessPegLabel;
import view.ResultPegPanel;

public class Controller {
    
    private GameView view;
    private GameModel model;

    public Controller(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
        JButton guessButton = view.getGuesserPanel().getGuessButton();
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guessButtonActionPerformed(guessButton);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(guessButton, ex.getMessage());
                }
            }
        });
        CodePegLabel[] pickPegs = view.getGuesserPanel().getPickPegs();
        for (CodePegLabel pickPeg : pickPegs) {
            pickPeg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Color color = ((CodePegLabel)e.getSource()).getForeground();
                    GuessPegLabel[] guessPegs = view.getGuesserPanel().getNewGuessPanel().getGuessPegs();
                    int selectedIndex = view.getGuesserPanel().getNewGuessPanel().getSelectedIndex();
                    guessPegs[selectedIndex].setForeground(color);
                    guessPegs[selectedIndex].repaint();
//                    newGuess[selectedIndex] = CodePeg.values()[serialNumber];
//                    picker.setVisible(false);
                }
            });
        }
    }
    
    public CodePeg[] getNewGuess() {
        GuessPegLabel[] guessPegs = view.getGuesserPanel().getNewGuessPanel().getGuessPegs();
        CodePeg[] newGuess = new CodePeg[guessPegs.length];
//        Color[] guessColors = new Color[guessPegs.length];
        for (int i = 0; i < guessPegs.length; i++) {
            newGuess[i] = CodePeg.getCodePeg(guessPegs[i].getForeground());
        }
        return newGuess;
    }
    
    public void guessButtonActionPerformed(JButton guessButton) {
        CodePeg[] newGuess = getNewGuess();
        model.addGuess(newGuess);
        int n = model.getGuessesUnmodifiable().size() - 1;
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = n;
        Color[] guessColors = new Color[newGuess.length];
        for (int i = 0; i < model.getCodeLength(); i++) {
            guessColors[i] = newGuess[i].getColor();
        }
        view.getGuessesPanel().add(new CodePanel(guessColors),c);
        view.getGuessesPanel().add(new ResultPegPanel(model.getEvaluationsUnmodifiable().get(n)), c);
        view.getGuesserPanel().getNewGuessPanel().setSelectedIndex(-1);
        for (CodePegLabel guessPeg : view.getGuesserPanel().getNewGuessPanel().getGuessPegs()) {
            guessPeg.setForeground(Color.GRAY);
            guessPeg.repaint();
        }
//        view.repaint();
        if (model.getGameState().equals(GameState.GAME_WON)) {
            String wonMessage = "You won with " + 
                    (n + 1) + " guesses. Congratulations!";
            JOptionPane.showMessageDialog(guessButton, wonMessage);
            view.dispose();
        }
        if (model.getGameState().equals(GameState.GAME_OVER)) {
            Color[] codeColors = new Color[model.getCodeLength()];
            for (int i = 0; i < model.getCodeLength(); i++) {
                codeColors[i] = model.getCode()[i].getColor();
            }
            CodePanel codeIcon = new CodePanel(codeColors);
            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.dispose();
                }
            });
            JDialog gameOverDialog = new JDialog(view);
            gameOverDialog.setLayout(new GridBagLayout());
            GridBagConstraints dc = new GridBagConstraints();
            dc.insets = new Insets(6, 6, 6, 6);
            dc.gridy = 0;
            gameOverDialog.add(new JLabel("<html><center>GAME OVER!<br>The code was:</html>"), dc);
            dc.gridy = 1;
            gameOverDialog.add(codeIcon, dc);
            dc.gridy = 2;
            gameOverDialog.add(okButton, dc);
            gameOverDialog.pack();
            gameOverDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    view.dispose();
                }
            });
            gameOverDialog.setVisible(true);
        }
    }
    
}
