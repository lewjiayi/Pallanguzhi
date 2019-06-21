public class Player {
    private int score;
    private int playerNum;

    public Player(int playerNum, String playerType){
        score = 0;
        this.playerNum = playerNum;
    }

    public int getScore() {
        return score;
    }

    public int getPlayerNum(){
        return playerNum;
    }

    public void addScore(int points){
        score = score+points;
    }

    public void setScore (int score){
        this.score = score;
    }
}
