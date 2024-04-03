import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressHandler implements EventHandler<KeyEvent> {

   private Balls balls;

   public KeyPressHandler(World world) {
      balls = world.getBalls();
   }

   @Override
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
      }
   }
/*
   public void handlePlayer(KeyEvent event) {
      switch(event.getCode()) {
         case UP:
            System.out.println("UP");
            ballToMove.up();
            break;
         case DOWN:
            System.out.println("DOWN");
            ballToMove.down();
            break;
         case RIGHT:
            System.out.println("RIGHT");
            ballToMove.right();
            break;
         case LEFT:
            System.out.println("LEFT");
            ballToMove.left();
            break;
      }
   }
*/      
}