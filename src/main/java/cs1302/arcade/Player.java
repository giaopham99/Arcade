package cs1302.arcade;

public class Player{
    int score = 0;

    public int getScore(){
        int temp = score;
        return temp;
    }//getScore

    public void addScore(int add){
        score += add;
    }//addScore

    public void setScore(int score){
        this.score=score;
    }//setScore
} // Player
