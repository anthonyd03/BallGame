
import java.awt.Color;

/**
 * Represents a special kind of ball that shrinks in size after every hit
 * up until it becomes less than 25% of its original size, at which points,
 * is reset to its orginal size.
 * 
 * @author Anthony
 */
public class ShrinkBall extends BasicBall {

    /**
     * The ShrinkBall's unique identifier number. */
    public static final int BALL_ID = 2;
    
    private final double originalSize; // stores the original radius size upon creation

    /**
     * Constructs a new ShrinkBall object with the given radius and color
     * by delegating to the {@link BasicBall#BasicBall(double, Color)} constructor.
     * @param r the radius of the ball
     * @param c the color of the ball
     */
    public ShrinkBall(double r, Color c) {
        super(r, c);
        this.originalSize = r;
    }

    /**
     * Shrinks the size of the ball by 33% unless the ball size is
     * less than or equal to 25% of its original size, at which point
     * it would set it back to the original size.
     */
    private void shrink() {
        if (this.radius <= (this.originalSize * 0.25)) {
            this.radius = this.originalSize;
            return;
        }

        this.radius = (this.radius / 3) * 2;
    }

    /**
     * Calls the ShrinkBall's shrink method and sets the ball to the center
     * of the play box and sets a new random velocity by delegating to the
     * {@link BasicBall#reset()} method.
     * @return the ShrinkBall unique identifier number
     */
    @Override
    public int reset() {
        this.shrink();
        super.reset();

        return BALL_ID;
    }

    /**
     * Gets the number of points the player earns for hitting the ball
     * @return the points the ShrinkBall's hit is worth
     */
    @Override
    public int getScore() {
        return 20;
    }
}
