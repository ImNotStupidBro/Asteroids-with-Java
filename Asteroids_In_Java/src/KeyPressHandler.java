import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressHandler implements EventHandler<KeyEvent> {

   private AsteroidSet asteroids;
   private Ship shipToMove;
   public KeyPressHandler(World world) {
      asteroids = world.getAsteroidSet();
      shipToMove = world.getShip();
   }

   public void handle(KeyEvent event) {
      switch(event.getCode()) {
         case D:
            System.out.println("d");
            asteroids.deleteAsteroid();
            break;
         case A:
            System.out.println("a");
            asteroids.addAsteroid();
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
            System.out.println("SPACE");
            break;
         case P:
            System.exit(0);
            break;
      }
   }    
}

