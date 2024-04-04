import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressHandler implements EventHandler<KeyEvent> {

   private Balls balls;
   private Player playerToMove;
   public KeyPressHandler(World world) {
      balls = world.getBalls();
      playerToMove = world.getPlayer();
   }

   public void handle(KeyEvent event) {
      switch(event.getCode()) {
         case D:
            System.out.println("d");
            balls.deleteBall();
            break;
         case A:
            System.out.println("a");
            balls.addBall();
            break;
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
      }
   }
/*
   public void handlePlayer(KeyEvent event) {
      switch(event.getCode()) {
         
      }
   }
*/      
}