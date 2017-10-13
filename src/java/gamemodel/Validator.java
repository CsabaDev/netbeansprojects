package gamemodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Validator {
    
    GameModel game;
    String invalidMessage;

    public Validator(GameModel game) {
        this.game = game;
    }
    
    public void validateGuess(CodePeg[] newGuess) {
        boolean valid = true;
        StringBuilder invalidMessageBuilder = new StringBuilder();
        List<CodePeg[]> guesses = new ArrayList<>(game.getGuessesUnmodifiable());
        if (guesses.remove(newGuess)) {
            valid = false;
            invalidMessageBuilder.append("You have already tried this code.");
        }
        if (newGuess.length != game.getCodeLength()) {
            valid = false;
            String invalidLengthMessage = "The length of the code must be " + 
                    game.getCodeLength() + ". ";
            invalidMessageBuilder.append(invalidLengthMessage);
        }
        for (CodePeg guessPeg : newGuess) {
            if (guessPeg == null) {
                valid = false;
                String notFinishedMessage = "Pick a color for every peg!";
                invalidMessageBuilder.append(notFinishedMessage);
                invalidMessage = invalidMessageBuilder.toString();
                throw new UnsupportedOperationException(invalidMessage);
            }
            if (Arrays.asList(CodePeg.values()).indexOf(guessPeg) >= game.getNumberOfColors()) {
                valid = false;
                String invalidColorMessage = guessPeg.toString() + 
                        " is not allowed in this game. ";
                invalidMessageBuilder.append(invalidColorMessage);
                break;
            }
        }
        if (!game.isColorsRepeatable()) {
            for (int i = 0; i < game.getCodeLength(); i++) {
                List<CodePeg> newGuessAsList = new ArrayList<>(Arrays.asList(newGuess));
                newGuessAsList.remove(i);
                if (newGuessAsList.contains(newGuess[i])) {
                    valid = false;
                    String invalidRepeatMessage = "Colors aren't repeatable in this game. ";
                    invalidMessageBuilder.append(invalidRepeatMessage);
                    break;
                }
            }
        }
        if (valid == false) {
            invalidMessage = invalidMessageBuilder.toString();
            throw new UnsupportedOperationException(invalidMessage);
        }
    }
    
    
}
