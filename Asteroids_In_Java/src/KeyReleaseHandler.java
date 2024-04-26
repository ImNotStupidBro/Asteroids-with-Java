import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyReleaseHandler implements EventHandler<KeyEvent> {

   private AsteroidSet asteroids;
   private Ship shipToMove;
   public KeyReleaseHandler(World world) {
      asteroids = world.getAsteroidSet();
      shipToMove = world.getShip();
   }

   public void handle(KeyEvent event) {
      switch(event.getCode()) {
         case UP:
            System.out.println("RELEASED UP");
            shipToMove.isAccelerating = false;
            break;
         case P:
            System.exit(0);
            break;
      }
   }    
}

