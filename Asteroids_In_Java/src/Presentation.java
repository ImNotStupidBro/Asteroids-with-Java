import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.*;
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
   
   public boolean toggleHitbox = true;

   public Presentation(Stage stage, World world) throws Exception {
      this.stage = stage;
      this.world = world;
      
      root = new Group();
      scene = new Scene(root);
      scene.setFill(Color.WHITE);
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
      //renderShip();
      renderAsteroids();
      
      graphicsContext.setFill(Color.BLACK);
      graphicsContext.strokePolygon( new double[]{300.0,450.0,300.0,150.0}, new double[]{50.0,150.0,250.0,150.0}, 4);
      
      stage.show();     
   }
   /*
   public void renderShip(){
      Ship shipToMove = world.getShip();
      double shipX = 0.0;
      double shipY = 0.0;
      double shipXVertices[];
      double shipYVertices[];
      
      shipX = convertPhysicsScaletoPresentationScale(shipToMove.getX());
      shipY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(shipToMove.getY()));
      shipXVertices = shipToMove.getShape();
      graphicsContext.setFill(Color.WHITE);
      //drawShip(shipX, shipY, shipPoly);
      
      if(toggleHitbox){
         graphicsContext.setFill(new Color(1, 0, 0, 0.5));
         drawHitbox(shipX, shipY, shipPoly);
      }
   }
   */
   public void renderAsteroids(){
      Asteroid asteroidArray[] = world.getAsteroidsAsArray();
      double asteroidX = 400.0;
      double asteroidY = 400.0;
      double asteroidXVertices[];
      double asteroidYVertices[];
      HitBox asteroidHitbox;
      
      for(int i = 0; i < asteroidArray.length; i++) {
         asteroidX = convertPhysicsScaletoPresentationScale(asteroidArray[i].getX());
         asteroidY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(asteroidArray[i].getY()));
         asteroidXVertices = asteroidArray[i].getXVertices();    
         asteroidYVertices = asteroidArray[i].getYVertices();
         asteroidHitbox = asteroidArray[i].getHitBox();
         graphicsContext.setFill(Color.WHITE);
         drawAsteroid(asteroidX, asteroidY, asteroidXVertices, asteroidYVertices);
         
         if(toggleHitbox){
            graphicsContext.setFill(new Color(1, 0, 0, 0.5));
            drawHitbox(asteroidX, asteroidY, asteroidHitbox);
         }
      }
   }
   
   //A circle is drawn in a box with the upper left corner being 0,0. Need to shift this to match the physics.
   private void drawAsteroid(double x, double y, double[] xVert, double[] yVert) {
      double adjustedX = x;
      double adjustedY = y;
      double transformedX[] = xVert;
      double transformedY[] = yVert;
      graphicsContext.strokePolygon(transformedX, transformedY, transformedX.length);
   }
   /*
   private void drawShip(double x, double y, double[] shipShape) {
      
   }
   */
   private void drawHitbox(double x, double y, HitBox hitbox) {
      double adjustedX = x;
      double adjustedY = y;
      double width = hitbox.getWidth();
      double height = hitbox.getHeight();
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