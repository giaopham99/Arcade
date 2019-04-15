package cs1302.arcade;

public class Player{
    int score = 0;

    public int getScore(){
        int temp = score;
        return temp;
    }

    public void addScore(int add){
        score += add;
    }
} // Player
