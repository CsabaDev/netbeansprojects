package gamemodel;

public enum GameState {
    IN_GAME, GAME_WON, GAME_OVER;

    @Override
    public String toString() {
        switch(this) {
            case IN_GAME : return "inGame";
            case GAME_WON : return "gameWon";
            case GAME_OVER : return "gameOver";
        }
        return null;
    }


}

