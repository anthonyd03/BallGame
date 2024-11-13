/**
 * Represents a player object that keeps track of a game player's scores as they play.
 * 
 * @author Anthony
 */
public class Player {

    private int totalScore;
    private int basicHits;
    private int bounceHits;
    private int shrinkHits;
    private int splitHits;

    public Player() {
        this.totalScore = 0;
        this.basicHits = 0;
        this.bounceHits = 0;
        this.shrinkHits = 0;
        this.splitHits = 0;
    }

    /**
     * Gets the total score.
     * @return the player's total score
     */
    public int getTotalScore() {
        return this.totalScore;
    }

    /**
     * Gets the number of BasicBall that have been hit.
     * @return the number of BasicBall that the player hit
     */
    public int getBasicHits() {
        return this.basicHits;
    }

    /**
     * Gets the number of ShrinkBall that have been hit.
     * @return the number of ShrinkBall that the player hit
     */
    public int getShrinkHits() {
        return this.shrinkHits;
    }

    /**
     * Gets the number of BounceBall that have been hit.
     * @return the number of BounceBall that the player hit
     */
    public int getBounceHits() {
        return this.bounceHits;
    }

    /**
     * Gets the number of SplitBall that have been hit.
     * @return the number of SplitBall that the player hit
     */
    public int getSplitHits() {
        return this.splitHits;
    }

    /**
     * Determines the type of ball and adds to the player's total score and hit count for the specific ball type.
     * @param ball the ball the player hit
     */
    public void increaseScore(BasicBall ball) {
        this.totalScore += ball.getScore(); // add ball specific points to the total score

        if (ball instanceof ShrinkBall) {
            this.shrinkHits++;
        } else if (ball instanceof BounceBall) {
            this.bounceHits++;
        } else if (ball instanceof SplitBall) {
            this.splitHits++;
        } else {
            this.basicHits++;
        }
    }
}
