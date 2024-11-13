
/** ****************************************************************************
 *  Compilation:  javac ColoredBall.java
 *  Execution:    java ColoredBall
 *  Dependencies: StdDraw.java
 *
 *  Implementation of a 2-d ball moving in square with coordinates
 *  between -1 and +1. Bounces off the walls upon collision.
 *
 *
 ***************************************************************************** */
import java.awt.Color;

/**
 * Defines the base ball type of an ordinary standard ball.
 * 
 * @author Anthony
 */
public class BasicBall {

    protected double rx, ry; // position
    protected double vx, vy; // velocity
    protected double radius; // radius
    protected final Color color; // color
    public boolean isOut;
    public static final int BALL_ID = 1; // used to identify type BasicBall

    /**
     * Constructs a new BasicBall object with the radius and color specified in the parameters.
     * @param r the ball's radius
     * @param c the ball's color
     */
    public BasicBall(double r, Color c) {
        rx = 0.0;
        ry = 0.0;
        vx = StdRandom.uniform(-0.01, 0.01);
        vy = StdRandom.uniform(-0.01, 0.01);
        radius = r;
        color = c;
        isOut = false;
    }

    /**
     * Moves the ball one step.
     */
    public void move() {
        rx = rx + vx;
        ry = ry + vy;
        if ((Math.abs(rx) > 1.0) || (Math.abs(ry) > 1.0)) {
            isOut = true;
        }
    }

    /**
     * Draws the ball.
     */
    public void draw() {
        if ((Math.abs(rx) <= 1.0) && (Math.abs(ry) <= 1.0)) {
            StdDraw.setPenColor(color);
            StdDraw.filledCircle(rx, ry, radius);
        } else {
            isOut = true;
        }
    }

    /**
     * Sets the ball back at the center of the play box and sets a new random velocity.
     * @return the BasicBall's unique identifier number
     */
    public int reset() {
        rx = 0.0;
        ry = 0.0;

        // get new random speed
        vx = StdRandom.uniform(-0.01, 0.01);
        vy = StdRandom.uniform(-0.01, 0.01);

        return BALL_ID;
    }

    /**
     * Checks if the ball has been hit by the player.
     * @param x the x location of the hit
     * @param y the y location of the hit
     * @return a boolean value: true for hit, false for no hit
     */
    public boolean isHit(double x, double y) {
        return (Math.abs(rx - x) <= radius) && (Math.abs(ry - y) <= radius);
    }

    /**
     * Gets the number of points the player earns for hitting the ball
     * @return the points the BasicBall's hit is worth
     */
    public int getScore() {
        return 25;
    }

    /**
     * Gets the radius of the ball.
     * @return the ball's radius
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Gets the color of the ball.
     * @return the ball's color
     */
    public Color getColor() {
        return this.color;
    }
}
