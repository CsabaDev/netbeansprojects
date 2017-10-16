package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import view.PropertyChangeListener;

public class GameModel {
    
    private int numberOfColors;
    private int codeLength;
    private int maxNumberOfGuesses;
    private boolean colorsRepeatable;
    private CodePeg[] code;
    private List<CodePeg[]> guesses;
    private List<CodePeg[]> guessesUnmodifiable;
    private List<ResultPeg[]> evaluations;
    private List<ResultPeg[]> evaluationsUnmodifiable;
    private GameState gameState;

    public GameModel(int numberOfColors, int codeLength, int maxNumberOfGuesses, boolean colorsRepeatable) {
        this.numberOfColors = numberOfColors;
        this.codeLength = codeLength;
        this.maxNumberOfGuesses = maxNumberOfGuesses;
        this.colorsRepeatable = colorsRepeatable;
        generateCode();
        guesses = new ArrayList<>();
        evaluations = new ArrayList<>();
        gameState = GameState.IN_GAME;
    }
    
    public GameModel(CodePeg[] code, int numberOfColors, int maxNumberOfGuesses, boolean colorsRepeatable) {
        this.numberOfColors = numberOfColors;
        this.codeLength = code.length;
        this.maxNumberOfGuesses = maxNumberOfGuesses;
        this.colorsRepeatable = colorsRepeatable;
        this.code = code;
        guesses = new ArrayList<>();
        evaluations = new ArrayList<>();
        gameState = GameState.IN_GAME;
    }
    
    private void generateCode() {
        Random rnd = new Random();
        CodePeg[] newCode = new CodePeg[codeLength];
        List<CodePeg> pegColors = new ArrayList<>(Arrays.asList(CodePeg.values()));
        int restLength = numberOfColors;
        for (int i = 0; i < codeLength; i++) {
            int n = rnd.nextInt(restLength);
            newCode[i] = pegColors.get(n);
            if(!colorsRepeatable) {
                pegColors.remove(n);
                restLength --;
            }
        }
        this.code = newCode;
    }
    
    public void addGuess(CodePeg[] newGuess) throws UnsupportedOperationException{
        Validator validator = new Validator(this);
        validator.validateGuess(newGuess);
        guesses.add(newGuess);
        evaluate(newGuess);
        newGuess = new CodePeg[codeLength];
    }
    
    public void evaluate(CodePeg[] guess) {
        ResultPeg[] evaluation = Evaluator.evaluate(this, guess);
        evaluations.add(evaluation);
        if (evaluation[codeLength - 1].equals(ResultPeg.WHITE)) {
            gameState = GameState.GAME_WON;
        } else if (guesses.size() >= maxNumberOfGuesses) {
            gameState = GameState.GAME_OVER;
        }
    }

    public int getNumberOfColors() {
        return numberOfColors;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public int getMaxNumberOfGuesses() {
        return maxNumberOfGuesses;
    }

    public boolean isColorsRepeatable() {
        return colorsRepeatable;
    }

    public CodePeg[] getCode() {
        return code;
    }

//    public CodePeg[] getNewGuess() {
//        return newGuess;
//    }
    
    public List<CodePeg[]> getGuessesUnmodifiable() {
        return Collections.unmodifiableList(guesses);
    }

    public List<ResultPeg[]> getEvaluationsUnmodifiable() {
        return Collections.unmodifiableList(evaluations);
    }

    public GameState getGameState() {
        return gameState;
    }
    
}
