package cs1302.arcade;

/**
 * Represents a Player
 */
public class Player{
    int score = 0;
    
    /**
     * Method to get the current score of the player.
     * @return the score
     */
    public int getScore(){
        int temp = score;
        return temp;
    }//getScore
    
    /**
     * Method that adds an amount to the current score.
     * @param add the amount to increase or decrease the score by.
     */
    public void addScore(int add){
        score += add;
    }//addScore
    
    /**
     * Method to set the score to a value.
     * @param score the new score to set the current score to.
     */
    public void setScore(int score){
        this.score=score;
    }//setScore
} // Player
