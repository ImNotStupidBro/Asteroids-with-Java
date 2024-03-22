import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Presentation {
   private Stage stage;
   private World world;
   private Group root;
   private Scene scene;
   private Canvas canvas;
   private GraphicsContext graphicsContext;
   private double canvasXDimension;
   private double canvasYDimension;
   private final double CANVAS_SCALE = 10.0;

   public Presentation(Stage stage, World world) throws Exception {
      this.stage = stage;
      this.world = world;
      
      root = new Group();
      scene = new Scene(root);
      canvasXDimension = world.getXDimension()*CANVAS_SCALE;
      canvasYDimension = world.getYDimension()*CANVAS_SCALE;
      canvas = new Canvas(canvasXDimension, canvasYDimension);
      graphicsContext = canvas.getGraphicsContext2D();
   
      root.getChildren().add(canvas);
   
      KeyPressHandler keyPressHandler = new KeyPressHandler(world);
      scene.setOnKeyPressed(keyPressHandler);
      
      stage.setScene(scene);
      
   
   }
   
   public void render() {
      Ball balls[] = world.getBalls();
      double ballX = 0.0;
      double ballY = 0.0;
      double ballRadius = 0.0;
      
      // Clear the screen so old objects don't blur across the screen
      graphicsContext.clearRect(0, 0, canvasXDimension, canvasYDimension);
      
      // Draw the balls
   
      for(int i = 0; i < balls.length; i++) {
         ballX = convertPhysicsScaletoPresentationScale(balls[i].getX());
         ballY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(balls[i].getY()));
         ballRadius = convertPhysicsScaletoPresentationScale(balls[i].getRadius());
         if (i == 0) { // color first ball green
            graphicsContext.setFill(Color.GREEN);
         }
         else {
            graphicsContext.setFill(Color.BLUE);
         }       
      
         drawBall(ballX, ballY, ballRadius);
      }
   
      stage.show();     
   }
   
   //A circle is drawn in a box with the upper left corner being 0,0. Need to shift this to match the physics.
   private void drawBall(double x, double y, double radius) {
      double adjustedX = x - radius;
      double adjustedY = y - radius;
      double diameter = 2.0 * radius;
      graphicsContext.fillOval(adjustedX, adjustedY, diameter, diameter);
   }
   
   private double convertPhysicsScaletoPresentationScale(double location) {
      return location * CANVAS_SCALE;
   }
   
   /** The physics world has a normal origin (lower left is 0,0), while the render space has typical graphics origin (upper left is 0,0) */
   private double convertPhysicsOriginToPresentationOrigin(double physicsYLocation) {
      double presentationYLocation = canvasYDimension - physicsYLocation;
      return presentationYLocation;
   }
      
   
    
}