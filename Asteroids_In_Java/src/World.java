/**
 * Describes the physics of the world.
 */
import java.util.Random;

public class World {
   private Balls balls;
   private Player playerToMove;
   public static final int X_DIMENSION = 100; // meters
   public static final int Y_DIMENSION = 50; // meters
   private final int MAX_BALL_RADIUS = 9;
   private final double MAX_BALL_SPEED = 30.0;
   private final int NUMBER_OF_BALLS = 4;
   
   public World() {
      //Create Ball set.
      balls = new Balls(NUMBER_OF_BALLS);

      //Generate the player ball.
      int ballXStartLocation = X_DIMENSION/2;
      int ballYStartLocation = Y_DIMENSION/2;
      int ballRadius = 3;
      double ballXSpeed = 0.0;
      double ballYSpeed = 0.0;
      HitBox ballHitbox = new HitBox(0,0,0,0);
      ballHitbox.set(ballXStartLocation, ballYStartLocation, (ballRadius * 2), (ballRadius * 2));
      
      playerToMove = new Player(ballXStartLocation, ballYStartLocation, ballRadius, ballXSpeed, ballYSpeed, ballHitbox);
   }
   
   /** Runs the physics of the world. */
   public void run(long elapsedTimeInNanoseconds) {
      balls.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      playerToMove.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
   }

   public Balls getBalls() {
      return balls;
   }
   
   public Ball[] getBallsAsArray() {
      return balls.getBallsAsArray();
   }

   public Player getPlayer() {
      return playerToMove;
   }

   public double getXDimension() {
      return X_DIMENSION;
   }

   public double getYDimension() {
      return Y_DIMENSION;
   }
}
