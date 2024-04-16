import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressHandler implements EventHandler<KeyEvent> {

   private Ball balls[];
   private Player playerToMove;

   public KeyPressHandler(World world) {
      playerToMove = world.getPlayer();
   }

   @Override
   public void handle(KeyEvent event) {
      switch(event.getCode()) {
         case UP:
            System.out.println("UP");
            playerToMove.up();
            break;
         case DOWN:
            System.out.println("DOWN");
            playerToMove.down();
            break;
         case RIGHT:
            System.out.println("RIGHT");
            playerToMove.right();
            break;
         case LEFT:
            System.out.println("LEFT");
            playerToMove.left();
            break;
         case SPACE:
            System.out.println("SPACE");
            break;
      }
   }      
}