package gamemodel;

import java.awt.Color;
import java.util.Arrays;

public enum CodePeg {
    RED (Color.RED), GREEN (Color.GREEN), BLUE (Color.BLUE), 
        YELLOW (Color.YELLOW), ORANGE (Color.ORANGE), PURPLE (Color.MAGENTA), 
        LIGHT_BLUE (Color.CYAN), PINK (Color.PINK), 
        BLACK (Color.BLACK), WHITE (Color.WHITE);

    private final Color color;
    private String colorName;

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
    
    public String getColorName(CodePeg peg) {
        switch (peg) {
            case RED : return "red";
            case GREEN : return "green";
            case BLUE : return "blue";
            case YELLOW : return "yellow";
            case ORANGE : return "orange";
            case PURPLE : return "blueviolet";
            case LIGHT_BLUE : return "aqua";
            case PINK : return "pink";
            case BLACK : return "black";
            case WHITE : return "white";
        }
        return "";
    }
    
    public static CodePeg[] getCodePegValues() {
        return CodePeg.values();
    }
}
