package gamemodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Evaluator {
    
    
    public static ResultPeg[] evaluate(GameModel game, CodePeg[] guess) {
        List<CodePeg> codeToEvaluate = new ArrayList<>(Arrays.asList(game.getCode()));
        List<CodePeg> guessToEvaluate = new ArrayList<>(Arrays.asList(guess));
        int whitePegNumber = 0;
        int blackPegNumber = 0;
        ResultPeg[] evaluation = new ResultPeg[game.getCodeLength()];
        for (int i = 0; i < game.getCodeLength(); i++) {
            if (guess[i].equals(game.getCode()[i])) {
                whitePegNumber ++;
                codeToEvaluate.remove(guess[i]);
                guessToEvaluate.remove(guess[i]);
            }
        }
        for (CodePeg guessPeg : guessToEvaluate) {
            if (codeToEvaluate.remove(guessPeg)) {
                blackPegNumber ++;
            }
        }
        for (int i = 0; i < whitePegNumber; i++) {
            evaluation[i] = ResultPeg.WHITE;
        }
        for (int i = whitePegNumber; i < whitePegNumber + blackPegNumber; i++) {
            evaluation[i] = ResultPeg.BLACK;
        }
        for (int i = whitePegNumber + blackPegNumber; i < game.getCodeLength(); i++) {
            evaluation[i] = ResultPeg.EMPTY;
        }
        return evaluation;
    }
    
}
