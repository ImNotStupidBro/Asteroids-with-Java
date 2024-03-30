/**
 * A set of balls.
 */
import java.util.HashSet;
import java.util.Random;
 
public class Balls {
   private HashSet<Ball> balls;
   private Random random;

   public Balls() {
      balls = new HashSet<Ball>();
      random = new Random();
   }
   
   public Balls(int numberOfBalls) {
      this();
      balls.add(Ball.createPlayerBall());
      for(int i = 1; i < numberOfBalls; i++) {
         balls.add(Ball.createRandomBall());
      }
   }
   
   /** Moves the balls based on the elapsed time and the velocity. */
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      for(Ball ball: balls) {
         ball.move(elapsedTimeInNanoseconds, worldXDimension, worldYDimension);
      }
   }
   
   public void deleteBall() {
      int size = balls.size();
      if (size > 0) { // don't try to delete from empty set of balls
         int indexOfBallToDelete = random.nextInt(size);
         int currentIndex = 0;
         for(Ball ball: balls) {
            if (currentIndex == indexOfBallToDelete) {
               balls.remove(ball);
               break;
            }
            currentIndex++;
         }
      }
   }
   public void addPlayerBall() {
      Ball ball = Ball.createPlayerBall();
      balls.add(ball);
   }
   
   public void addBall() {
      Ball ball = Ball.createRandomBall();
      balls.add(ball);
   }
   
   public HashSet<Ball> getBalls() {
      return balls;
   }
   
   public Ball[] getBallsAsArray() {
      int numberOfBalls = this.balls.size();
      Ball[] balls = new Ball[numberOfBalls];
      int i = 0;
      for(Ball ball: this.balls) {
         balls[i++] = ball;
      }
      return balls;
   }
}
