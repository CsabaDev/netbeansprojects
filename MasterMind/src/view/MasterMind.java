package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.GameModel;

public class MasterMind extends javax.swing.JFrame {
    
    int newNumberOfColors = 8;
    int newCodeLength = 4;
    int newMaxNumberOfGuesses = 20;
    boolean newColorsRepeatable = true;
    
    private final JPanel mainPanel;
    private final JButton startGameButton;
    private final JPanel settingsPanel;
    private JLabel codeLengthLabel;
    private JSlider codeLengthSlider;
    private JLabel numberOfColorsLabel;
    private JSlider numberOfColorsSlider;
    private JLabel maxNumberOfGuessesLabel;
    private JSlider maxNumberOfGuessesSlider;
    private JLabel colorsRepeatableLabel;
    private JCheckBox colorsRepeatableCheckBox;
    
    public MasterMind() {
        mainPanel = new JPanel();
        startGameButton = new JButton();
        settingsPanel = new JPanel();
        numberOfColorsLabel = new JLabel();
        numberOfColorsSlider = new JSlider();
        codeLengthLabel = new JLabel();
        codeLengthSlider = new JSlider();
        maxNumberOfGuessesLabel = new JLabel();
        maxNumberOfGuessesSlider = new JSlider();
        colorsRepeatableLabel = new JLabel();
        colorsRepeatableCheckBox = new JCheckBox();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        
        this.setLayout(new BorderLayout());
        
        startGameButton.setText("START NEW GAME!");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameModel game = new GameModel(newNumberOfColors, newCodeLength, 
                        newMaxNumberOfGuesses, newColorsRepeatable);
                GameView gameView = new GameView(game);
            }
        });
        
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(startGameButton);

        this.add(mainPanel, BorderLayout.PAGE_START);
        
        numberOfColorsSlider.setMinimum(6);
        numberOfColorsSlider.setMaximum(10);
        numberOfColorsSlider.setValue(newNumberOfColors);
        numberOfColorsSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                newNumberOfColors = numberOfColorsSlider.getValue();
                numberOfColorsLabel.setText("Number of colors: "
                        + newNumberOfColors);
            }
        });
        numberOfColorsLabel.setText("Number of colors: "
                + newNumberOfColors);
        
        codeLengthSlider.setMinimum(4);
        codeLengthSlider.setMaximum(8);
        codeLengthSlider.setValue(newCodeLength);
        codeLengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                newCodeLength = codeLengthSlider.getValue();
                codeLengthLabel.setText("Length of code: " 
                        + newCodeLength + " pegs");
            }
        });
        codeLengthLabel.setText("Length of code: " 
                + newCodeLength + " pegs");
        
        maxNumberOfGuessesSlider.setMinimum(12);
        maxNumberOfGuessesSlider.setMaximum(60);
        maxNumberOfGuessesSlider.setValue(newMaxNumberOfGuesses);
        maxNumberOfGuessesSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                newMaxNumberOfGuesses = maxNumberOfGuessesSlider.getValue();
                maxNumberOfGuessesLabel.setText("Maximum number of guesses: "
                        + newMaxNumberOfGuesses);
            }
        });
        maxNumberOfGuessesLabel.setText("Maximum number of guesses: "
                + newMaxNumberOfGuesses);
        
        colorsRepeatableCheckBox.setSelected(newColorsRepeatable);
        colorsRepeatableCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                newColorsRepeatable = colorsRepeatableCheckBox.isSelected();
                colorsRepeatableLabel.setText("Colors repeatable: " 
                        + newColorsRepeatable);
            }
        });
        colorsRepeatableLabel.setText("Colors repeatable: "
                + newColorsRepeatable);
        
        settingsPanel.setLayout(new GridLayout(4, 2, 12, 12));
        
        settingsPanel.add(numberOfColorsLabel);
        settingsPanel.add(numberOfColorsSlider);
        settingsPanel.add(codeLengthLabel);
        settingsPanel.add(codeLengthSlider);
        settingsPanel.add(maxNumberOfGuessesLabel);
        settingsPanel.add(maxNumberOfGuessesSlider);
        settingsPanel.add(colorsRepeatableLabel);
        settingsPanel.add(colorsRepeatableCheckBox);

        this.add(settingsPanel, BorderLayout.PAGE_END);
        
        this.pack();
    }
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MasterMind().setVisible(true);
            }
        });
    }    
}
