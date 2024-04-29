import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressHandler implements EventHandler<KeyEvent> {

   private AsteroidSet asteroids;
   private AsteroidSetMedium mediumAsteroids;
   private AsteroidSetSmall smallAsteroids;
   private Ship shipToMove;
   private Lazers fireLazer;
   public KeyPressHandler(World world) {
      asteroids = world.getAsteroidSet();
      mediumAsteroids = world.getMediumAsteroidSet();
      smallAsteroids = world.getSmallAsteroidSet();
      shipToMove = world.getShip();
      fireLazer = world.getLazers();
   }

   public void handle(KeyEvent event) {
      switch(event.getCode()) {
         case D:
            System.out.println("d");
            asteroids.deleteAsteroid();
            mediumAsteroids.deleteAsteroid();
            smallAsteroids.deleteAsteroid();
            break;
         case A:
            System.out.println("a");
            asteroids.addAsteroid();
            mediumAsteroids.addAsteroid();
            smallAsteroids.addAsteroid();
            break;
         case UP:
            System.out.println("UP");
            shipToMove.accelerate();
            shipToMove.isAccelerating = true;
            break;
         case RIGHT:
            System.out.println(shipToMove.getDirection());
            shipToMove.turnRight();
            break;
         case LEFT:
            System.out.println(shipToMove.getDirection());
            shipToMove.turnLeft();
            break;
         case SPACE:
            System.out.println("FIRE LAZER");
            fireLazer.addLazer(shipToMove.getX(), shipToMove.getY(), shipToMove.getDirection());
            break;
         case P:
            System.exit(0);
            break;
      }
   }    
}

