/**
 * Describes the physics of a ball.
 */
import java.util.Random;
public class Ball {
   private double x; // meters
   private double y; // meters
   private double radius; // meters
   private double xSpeed; // meters/second
   private double ySpeed; // meters/second
   private HitBox hitbox; // Collision detection tool
   
   private static final int MAX_BALL_RADIUS = 9;
   private static final double MAX_BALL_SPEED = 30.0;

   public Ball(double x, double y, double radius, double xSpeed, double ySpeed, HitBox hitbox) {
      this.x = x; // meters
      this.y = y; // meters
      this.radius = radius; //meters
      this.xSpeed = xSpeed; // meters/second
      this.ySpeed = ySpeed; // meter/second
      this.hitbox = hitbox; // Collision-detection tool
   }
   
   /** Moves the ball based on the elapsed time and the velocity. */
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      x += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      y += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep ball on the torus
      if (x < 0 - (this.radius * 2)) { // moving in the negative x direction
         x = worldXDimension + x % worldXDimension + (this.radius * 3);
      } else if (x >= worldXDimension + (this.radius * 2)) {
         x = x % worldXDimension - (this.radius * 3);
      }
      if (y < 0 - (this.radius * 2)) { // moving in the negative y direction
         y = worldYDimension + y % worldYDimension + (this.radius * 3);
      } else if (y >= worldYDimension + (this.radius * 2)) {
         y = y % worldYDimension - (this.radius * 3);
      }
   }

   public static Ball createRandomBall() {
      Random random = new Random();
      int ballXStartLocation = random.nextInt(World.X_DIMENSION);
      int ballYStartLocation = random.nextInt(World.Y_DIMENSION);
      int ballRadius = random.nextInt(MAX_BALL_RADIUS) + 1; //no zero radius balls
      double ballXSpeed = (random.nextDouble() - 0.5) * MAX_BALL_SPEED;
      double ballYSpeed = (random.nextDouble() - 0.5) * MAX_BALL_SPEED;
      HitBox ballHitbox = new HitBox(ballXStartLocation, ballYStartLocation, (ballRadius * 2), (ballRadius * 2));
      Ball ball = new Ball(ballXStartLocation, ballYStartLocation, ballRadius, ballXSpeed, ballYSpeed, ballHitbox);
      return ball;
   }
   
   public void up() {
      ySpeed += 1.0;
   }
   
   public void down() {
      ySpeed -= 1.0;
   }
   
   public void right() {
       xSpeed += 1.0;
   }
   
   public void left() {
      xSpeed -= 1.0;
   }

   public double getX() {
      return x;
   }

   public double getY() {
      return y;
   }

   public double getRadius() {
      return radius;
   }

   public double getXSpeed() {
      return xSpeed;
   }

   public double getYSpeed() {
      return ySpeed;
   }
   
   public HitBox getHitbox() {
      return hitbox;
   }
}
