
/** ****************************************************************************
 *  Compilation:  javac BallGame.java
 *  Execution:    java BallGame n
 *  Dependencies: BasicBall.java StdDraw.java
 *
 *  Creates a BasicBall and animates it
 *
 *  Part of the animation code is adapted from Computer Science:   An Interdisciplinary Approach Book
 *
 *  Run the skeleton code with arguments : 1  basic  0.08
 ****************************************************************************** */
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class BallGame {

    public static void main(String[] args) {

        // number of bouncing balls
        int numBalls = Integer.parseInt(args[0]);
        // ball types
        String ballTypes[] = new String[numBalls];
        // sizes of balls
        double ballSizes[] = new double[numBalls];
        // ball storage
        ArrayList<BasicBall> ballStorage = new ArrayList<>(numBalls);

        // retrieve ball types
        int index = 1;
        for (int i = 0; i < numBalls; i++) {
            ballTypes[i] = args[index];
            index = index + 2;
        }
        // retrieve ball sizes
        index = 2;
        for (int i = 0; i < numBalls; i++) {
            ballSizes[i] = Double.parseDouble(args[index]);
            index = index + 2;
        }

        // create a Player object and initialize the player game stats.
        Player player = new Player();

        // number of active balls
        int numBallsinGame = 0;
        StdDraw.enableDoubleBuffering();

        StdDraw.setCanvasSize(800, 800);
        // set boundary to box with coordinates between -1 and +1
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);

        // create colored balls 
        // create "numBalls" balls (of types given in "ballTypes" with sizes given in "ballSizes") and store them in an Arraylist
        for (int i = 0; i < numBalls; i++) {
            String type = ballTypes[i];
            double size = ballSizes[i];

            switch (type.toLowerCase()) {
                case "basic" ->
                    ballStorage.add(new BasicBall(size, Color.RED));
                case "shrink" ->
                    ballStorage.add(new ShrinkBall(size, Color.CYAN));
                case "bounce" ->
                    ballStorage.add(new BounceBall(size, Color.LIGHT_GRAY));
                case "split" ->
                    ballStorage.add(new SplitBall(size, Color.PINK));
                default -> {
                }
            }

            numBallsinGame++;
        }

        // do the animation loop
        StdDraw.enableDoubleBuffering();
        while (numBallsinGame > 0) {

            moveBalls(ballStorage);

            //Check if the mouse is clicked
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                checkHit(ballStorage, x, y, player);
            }

            // numBallsinGame = 0;
            // draw the n balls
            StdDraw.clear(StdDraw.GRAY);
            StdDraw.setPenColor(StdDraw.BLACK);

            // check each ball and see if they are still visible. numBallsinGame should hold the number of visible balls in the game.  
            numBallsinGame = checkVisibility(ballStorage);

            // check if any of the bounce balls needs to be bounced
            bounceBalls(ballStorage);

            // print the game progress
            StdDraw.setPenColor(StdDraw.YELLOW);
            Font font = new Font("Arial", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(-0.65, 0.90, "Number of balls in game: " + String.valueOf(numBallsinGame));

            // print the rest of the player statistics
            font = new Font("Javanese Text", Font.HANGING_BASELINE, 20);
            StdDraw.setFont(font);
            StdDraw.text(-0.60, 0.85, "Number of hits: ");
            StdDraw.text(-0.60, 0.75, "Total score: " + player.getTotalScore());

            StdDraw.show();
            StdDraw.pause(20);
        }

        StdDraw.clear(StdDraw.GRAY);
        boolean showGO = true;

        /*
         * NOTE: the if statements surrounding the "GAME OVER" StdDraw.text
         * initialization is unecessary at this point and is meant to go with line 156
         * for future implemenations.
         */
        while (true) {
            // display end of game message
            Font font = new Font("Arial", Font.BOLD, 60);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.setFont(font);
            if (showGO) {
                StdDraw.text(0.01, 0.24, "GAME OVER");
            }
            StdDraw.text(0.02, 0.19, "_________");
            StdDraw.text(0.02, 0.05, "_________");
            // create an offset for a style effect
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setFont(font);
            if (showGO) {
                StdDraw.text(0, 0.25, "GAME OVER");
            }
            StdDraw.text(0.01, 0.20, "_________");
            StdDraw.text(0.01, 0.06, "_________");

            // print the rest of the player statistics
            StdDraw.setPenColor(StdDraw.RED);
            font = new Font("Onyx", Font.PLAIN, 45);
            StdDraw.setFont(font);
            StdDraw.text(0, 0.05, "Total Score: " + player.getTotalScore());

            StdDraw.setPenColor(StdDraw.YELLOW);
            font = new Font("Courier New", Font.BOLD, 25);
            StdDraw.setFont(font);
            StdDraw.text(0, -0.10, "Basic: " + player.getBasicHits());
            StdDraw.text(0, -0.20, "Shrink: " + player.getShrinkHits());
            StdDraw.text(0, -0.30, "Bounce: " + player.getBounceHits());
            StdDraw.text(0, -0.40, "Split: " + player.getSplitHits());

            // showGO = !showGO; // future implementations on getting "GAME OVER" to blink

            StdDraw.show();
            StdDraw.pause(10);
        }

    } // end main()

    /**
     * Iterates through all of the BasicBall objects within the ArrayList, <i>balls</i>, given in the parameter
     * and calls each ball's move() method.
     * @param balls the ArrayList of all the balls currently in the game
     */
    private static void moveBalls(ArrayList<BasicBall> balls) {
        for (BasicBall ball : balls) {
            ball.move();
        }
    }

    /**
     * Check all of the balls in <i>balls</i> to see if they are hit. If so, update the player's
     * score by calling the {@link Player#increaseScore(BasicBall)} method and also check if the
     * the hit ball is a SplitBall and call the {@link BallGame#addSplitBall(ArrayList, double, Color, int)}
     * method if it is.
     * @param balls the ArrayList of all the balls currently in the game
     * @param x the mouseX position upon mouse click
     * @param y the mouseY position upon mouse click
     * @param p reference to the Player object
     */
    private static void checkHit(ArrayList<BasicBall> balls, double x, double y, Player p) {
        int numSplitBallsToAdd = 0;
        SplitBall sbRef = null;
        for (BasicBall ball : balls) {
            if (ball.isHit(x, y)) {
                int hitNumber = ball.reset();
                if (hitNumber == SplitBall.BALL_ID) { // a SplitBall was hit
                    numSplitBallsToAdd++; // keep track of how many copies to create
                    sbRef = (SplitBall)ball;
                }

                // Update player statistics
                p.increaseScore(ball);
            }
        }
        
        // call the addSplitBall method after the for loop so as not to add to the ArrayList while iterating over it
        if (numSplitBallsToAdd > 0) {
            if (sbRef != null) {
                addSplitBall(balls, sbRef.getRadius(), sbRef.getColor(), numSplitBallsToAdd);
            }
        }
    }

    /**
     * Check each ball in <i>balls</i> to see if they are still in the play box
     * and draw them if they are by calling the {@link BasicBall#draw()} method for that ball.
     * Keeps track of the number of balls in the game and returns that value.
     * @param balls the ArrayList of all the balls currently in the game
     * @return the number of balls currently in the game
     */
    private static int checkVisibility(ArrayList<BasicBall> balls) {
        int numBallsinGame = 0;
        for (BasicBall ball : balls) {
            if (ball.isOut == false) {
                ball.draw();
                numBallsinGame++;
            }
        }

        return numBallsinGame;
    }

    /**
     * Iterates through the ArrayList of balls given in the parameter, <i>balls</i>,
     * and if they are of type BounceBall, call the {@link BounceBall#touchingBorder()} method
     * and check if the ball has touched a wall. If it has, then bounce the ball off the wall
     * by calling {@link BounceBall#bounce(int)}.
     * @param balls the ArrayList of all the balls currently in the game
     */
    private static void bounceBalls(ArrayList<BasicBall> balls) {
        for (BasicBall ball : balls) {
            try {
                BounceBall bb = (BounceBall)ball;
                int touch = bb.touchingBorder();
                if (touch > 0) {
                    bb.bounce(touch);
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * Creates <i>num</i> number of <i>c</i> colored SplitBall with a radius of <i>r</i>
     * and adds them to <i>balls</i>.
     * @param balls the ArrayList of all the balls currently in the game
     * @param r the radius of the ball
     * @param c the color of the ball
     * @param num the number of new SplitBall to create
     */
    public static void addSplitBall(ArrayList<BasicBall> balls, double r, Color c, int num) {
        for (int i = 0; i < num; i++) {
            balls.add(new SplitBall(r, c));
        }
    }
}
