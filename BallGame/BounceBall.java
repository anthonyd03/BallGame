import java.awt.Color;

/**
 * Represents a special kind of ball that bounces off the walls of the game box
 * and dissapears if it bounces 3 times.
 * 
 * @author Anthony
 */
public class BounceBall extends BasicBall {

    private int bounceCount;
    private final int TOUCHING_TOP = 1;
    private final int TOUCHING_BOTTOM = 2;
    private final int TOUCHING_LEFT = 3;
    private final int TOUCHING_RIGHT = 4;

    /**
     * The BounceBall's unique identifier number.
     */
    public static final int BALL_ID = 3;

    /**
     * Constructs a new BounceBall object with the given radius and color
     * by delegating to the {@link BasicBall#BasicBall(double, Color)} constructor.
     * @param r the radius of the ball
     * @param c the color of the ball
     */
    public BounceBall(double r, Color c) {
        super(r, c);
        this.bounceCount = 0;
    }

    /**
     * Gets the number of points the player earns for hitting the ball
     * @return the points the BounceBall's hit is worth
     */
    @Override
    public int getScore() {
        return 15;
    }

    /**
     * Sets the ball back to the center of the play box and sets a new random
     * velocity by delegating to the {@link BasicBall#reset()} method.
     * @return the BounceBall's unique identifier number
     */
    @Override
    public int reset() {
        super.reset();

        return BALL_ID;
    }

    /**
     * Checks if the ball is touching or has passed the play box border
     * (to mimic touching the walls) and returns the number associated
     * with which wall the ball has touched.
     * @return 0 for no wall, 1 for top wall, 2 for bottom wall, 3 for left wall, and 4 for right wall
     */
    public int touchingBorder() {
        if ((this.ry + this.radius) >= 1.0) { // touching top wall
            return this.TOUCHING_TOP;
        }
        else if ((this.ry - this.radius) <= -1.0) { // touching bottom wall
            return this.TOUCHING_BOTTOM;
        }
        else if ((this.rx - this.radius) <= -1.0) { // touching left wall
            return this.TOUCHING_LEFT;
        }
        else if ((this.rx + this.radius) >= 1.0) { // touching right wall
            return this.TOUCHING_RIGHT;
        }
        return 0; // the ball is not touching the border
    }

    /**
     * Changes the direction of ball depending on which wall the ball
     * is bouncing off of, specified through the parameter <i>side</i>.
     * @param side the number that specifies which wall the ball is bouncing off of.
     * 1 is top, 2 is bottom, 3 is left, and 4 is right.
     */
    public void bounce(int side) {
        if (side == this.TOUCHING_TOP || side == this.TOUCHING_BOTTOM) {
            this.vy = -this.vy;
            this.bounceCount++;
        }
        else if (side == this.TOUCHING_RIGHT || side== this.TOUCHING_LEFT) {
            this.vx = -this.vx;
            this.bounceCount++;
        }

        if (this.bounceCount == 3) {
            this.isOut = true;
        }
    }
}
