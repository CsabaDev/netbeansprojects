package gamemodel;

public enum ResultPeg {
    BLACK, WHITE, EMPTY;

    public String getResultColorName(ResultPeg resultPeg) {
        switch (resultPeg) {
            case BLACK : return "black";
            case WHITE : return "white";
            case EMPTY : return "burlywood";
        }
        return null;
    }
}

