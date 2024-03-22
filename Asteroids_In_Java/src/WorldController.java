import javafx.application.Application;
import javafx.stage.Stage;
import java.util.concurrent.TimeUnit;

public class WorldController extends Application {
   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage stage) throws Exception {     
      World world = new World();
      Presentation presentation = new Presentation(stage, world);
      GameLoop gameLoop = new GameLoop(world, presentation);
        
      gameLoop.start();
   }
}