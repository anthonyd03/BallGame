import java.awt.Color;

/**
 * Represents a special kind of ball that resets and gets duplicated everytime it is hit.
 * 
 * @author Anthony
 */
public class SplitBall extends BasicBall {
    
    /**
     * The SplitBall's unique identifier number.
     */
    public static final int BALL_ID = 4;

    /**
     * Constructs a new SplitBall object with the given radius and color
     * by delegating to the {@link BasicBall#BasicBall(double, Color)} constructor.
     * @param r the radius of the ball
     * @param c the color of the ball
     */
    public SplitBall(double r, Color c) {
        super(r, c);
    }

    /**
     * Gets the number of points the player earns for hitting the ball.
     * @return the points the SplitBall's hit is worth
     */
    @Override
    public int getScore() {
        return 10;
    }
    
    /**
     * Sets the ball back to the center of the play box and sets a new random
     * velocity by delegating to the {@link BasicBall#reset()} method.
     * @return the SplitBall's unique identifier number
     */
    @Override
    public int reset() {
        super.reset();

        return BALL_ID;
    }
}
