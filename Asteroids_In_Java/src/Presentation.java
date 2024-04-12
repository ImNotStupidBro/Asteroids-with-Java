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
      scene.setFill(Color.BLACK);
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
      
      graphicsContext.clearRect(0, 0, canvasXDimension, canvasYDimension);
      renderShip();
      renderAsteroids();
      
      
      boolean toggleHitbox = false;
      
      // Clear the screen so old objects don't blur across the screen
      graphicsContext.clearRect(0, 0, canvasXDimension, canvasYDimension);

      stage.show();     
   }
   
   public void renderShip(){
      Player playerToMove = world.getPlayer();
      double playerX = 0.0;
      double playerY = 0.0;
      int[] shipXVerticies = {116, 100, 104, 128, 132, 116};
      int[] shipYVerticies = {80, 128, 116, 116, 128, 80};
      Polygon shipPoly = new Polygon(shipXVerticies, shipYVerticies, shipXVerticies.length);
      // Draw the player
      playerX = convertPhysicsScaletoPresentationScale(playerToMove.getX());
      playerY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(playerToMove.getY()));
      playerRadius = convertPhysicsScaletoPresentationScale(playerToMove.getRadius());
      graphicsContext.setFill(Color.WHITE);
      drawShip(playerX, playerY, shipPoly);
      
      if(toggleHitbox){
            graphicsContext.setFill(new Color(1, 0, 0, 0.5));
            drawHitbox(playerX, playerY, shipPoly);
      }
   }
   
   public void renderAsteroids(){
      Asteroid asteroidArray[] = world.getAsteroidsAsArray();
      double asteroidX = 0.0;
      double asteroidY = 0.0;
      Polygon asteroidShape;
      
      for(int i = 0; i < asteroidArray.length; i++) {
         asteroidX = convertPhysicsScaletoPresentationScale(asteroidArray[i].getX());
         asteroidY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(asteroidArray[i].getY()));
         asteroidShape = convertPhysicsScaletoPresentationScale(asteroidArray[i].getRadius());    
         graphicsContext.setFill(Color.WHITE);
         drawAsteroid(asteroidX, asteroidY, asteroidShape);
         
         if(toggleHitbox){
            graphicsContext.setFill(new Color(1, 0, 0, 0.5));
            drawHitbox(asteroidX, asteroidY, asteroidShape);
         }
      }
   }
   
   //A circle is drawn in a box with the upper left corner being 0,0. Need to shift this to match the physics.
   private void drawAsteroid(double x, double y, Polygon asteroidShape) {
      double adjustedX = x;
      double adjustedY = y;
      Polygon asteroidShape = ;
      graphicsContext.drawPolygon(adjustedX, adjustedY, );
   }
   
   private void drawShip(double x, double y, Polygon theShipPoly);
   
   private void drawHitbox(double x, double y, Polygon thePoly) {
      double adjustedX = x;
      double adjustedY = y;
      double width = thePoly.getBounds().getWidth();
      double height = thePoly.getBounds().getHeight();
      graphicsContext.fillRect(adjustedX, adjustedY, width, height);
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