package model;

import java.awt.Color;
import java.util.Arrays;

public enum CodePeg {
    RED (Color.RED), GREEN (Color.GREEN), BLUE (Color.BLUE), 
        YELLOW (Color.YELLOW), ORANGE (Color.ORANGE), PURPLE (Color.MAGENTA), 
        LIGHT_BLUE (Color.CYAN), PINK (Color.PINK), 
        BLACK (Color.BLACK), WHITE (Color.WHITE);

    private final Color color;

    CodePeg(Color color) {
        this.color = color;
    }

    public static CodePeg getCodePeg(Color codeColor) {
        for (CodePeg codePeg : CodePeg.values()) {
            if (codePeg.color.equals(codeColor)) {
                return codePeg;
            }
        }
        return null;
    }
    
    public Color getColor() {
        return color;
    }
    
}
