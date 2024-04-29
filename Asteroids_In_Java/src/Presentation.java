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
   private final double ASTEROID_SCALE = 10.0;
   private final double MEDIUM_ASTEROID_SCALE = 5.0;
   private final double SMALL_ASTEROID_SCALE = 2.5;
   
   public boolean toggleHitbox = true;

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
      KeyReleaseHandler keyReleaseHandler = new KeyReleaseHandler(world);
      scene.setOnKeyPressed(keyPressHandler);
      scene.setOnKeyReleased(keyReleaseHandler);
      
      stage.setScene(scene);
   
   }
   
   // Render: Draw everything using the rendering methods given.
   public void render() {
      
      graphicsContext.clearRect(0, 0, canvasXDimension, canvasYDimension);
      graphicsContext.setStroke(Color.WHITE);
      
      renderShip();
      renderLazers();

      renderAsteroids();
      renderMediumAsteroids();
      renderSmallAsteroids();
      renderLives();
      
      stage.show();     
   }
   
   // Render Call for the ship
   public void renderShip(){
      Ship shipToMove = world.getShip();
      double shipX = 0.0;
      double shipY = 0.0;
      double shipDirection = 0.0;
      HitBox shipHitbox;
      
      shipX = convertPhysicsScaletoPresentationScale(shipToMove.getX());
      shipY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(shipToMove.getY()));
      shipDirection = shipToMove.getDirection() + 90.0;
      shipHitbox = shipToMove.getHitBox();
      drawShip(shipX, shipY, shipDirection);
      
      if(toggleHitbox){
         graphicsContext.setFill(new Color(1, 0, 0, 0.5));
         drawHitbox(shipX, shipY, shipHitbox);
      }
   }
   
   // Render Call for the lazers
   public void renderLazers(){
      Lazer lazerArray[] = world.getLazersAsArray();
      double lazerX = 0.0;
      double lazerY = 0.0;
      double lazerRadius = 5;
      HitBox lazerHitbox;
      
      for(int i = 0; i < lazerArray.length; i++) {
         lazerX = convertPhysicsScaletoPresentationScale(lazerArray[i].getX());
         lazerY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(lazerArray[i].getY()));
         lazerHitbox = lazerArray[i].getHitbox();
         graphicsContext.setFill(Color.WHITE);
         drawLazer(lazerX, lazerY, lazerRadius);
         
         if(toggleHitbox){
            graphicsContext.setFill(new Color(1, 0, 0, 0.5));
            drawHitbox(lazerX, lazerY, lazerHitbox);
         }
      }
   }
   
   // Render Call for the life counter
   private void renderLives() {
      int lives = world.getNumOfLives();
      double lifeCounterXPosition = 3.0;
      double lifeCounterYPosition = 670.0;
      double lifeCounterDirection = 22.5;
      
      for(int i = lives; i > 0; i--){
         drawShip((lifeCounterXPosition + (30 * i)), lifeCounterYPosition, lifeCounterDirection);
      }
   }
   
   // Render Call for the regular asteroids
   public void renderAsteroids(){
      Asteroid asteroidArray[] = world.getAsteroidsAsArray();
      double asteroidX = 0.0;
      double asteroidY = 0.0;
      int asteroidConfigNumber = 0;
      HitBox asteroidHitbox;
      
      for(int i = 0; i < asteroidArray.length; i++) {
         asteroidX = convertPhysicsScaletoPresentationScale(asteroidArray[i].getX());
         asteroidY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(asteroidArray[i].getY()));
         asteroidConfigNumber = asteroidArray[i].getConfigNumber();
         asteroidHitbox = asteroidArray[i].getHitBox();
         graphicsContext.setFill(Color.WHITE);
         drawAsteroid(asteroidX, asteroidY, asteroidConfigNumber);
         
         if(toggleHitbox){
            graphicsContext.setFill(new Color(1, 0, 0, 0.5));
            drawHitbox(asteroidX, asteroidY, asteroidHitbox);
         }
      }
   }
   
   // Render Call for the medium asteroids
   public void renderMediumAsteroids(){
      Asteroid asteroidArray[] = world.getMediumAsteroidsAsArray();
      double asteroidX = 0.0;
      double asteroidY = 0.0;
      int asteroidConfigNumber = 0;
      HitBox asteroidHitbox;
      
      for(int i = 0; i < asteroidArray.length; i++) {
         asteroidX = convertPhysicsScaletoPresentationScale(asteroidArray[i].getX());
         asteroidY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(asteroidArray[i].getY()));
         asteroidConfigNumber = asteroidArray[i].getConfigNumber();
         asteroidHitbox = asteroidArray[i].getHitBox();
         graphicsContext.setFill(Color.WHITE);
         drawMediumAsteroid(asteroidX, asteroidY, asteroidConfigNumber);
         
         if(toggleHitbox){
            graphicsContext.setFill(new Color(1, 0, 0, 0.5));
            drawHitbox(asteroidX, asteroidY, asteroidHitbox);
         }
      }
   }
   
   // Render Call for the small asteroids
   public void renderSmallAsteroids(){
      Asteroid asteroidArray[] = world.getSmallAsteroidsAsArray();
      double asteroidX = 0.0;
      double asteroidY = 0.0;
      int asteroidConfigNumber = 0;
      HitBox asteroidHitbox;
      
      for(int i = 0; i < asteroidArray.length; i++) {
         asteroidX = convertPhysicsScaletoPresentationScale(asteroidArray[i].getX());
         asteroidY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(asteroidArray[i].getY()));
         asteroidConfigNumber = asteroidArray[i].getConfigNumber();
         asteroidHitbox = asteroidArray[i].getHitBox();
         graphicsContext.setFill(Color.WHITE);
         drawSmallAsteroid(asteroidX, asteroidY, asteroidConfigNumber);
         
         if(toggleHitbox){
            graphicsContext.setFill(new Color(1, 0, 0, 0.5));
            drawHitbox(asteroidX, asteroidY, asteroidHitbox);
         }
      }
   }
   
   // Regular Asteroid Drawing Method
   private void drawAsteroid(double x, double y, int CONFIGNUM) {
      double adjustedX = x;
      double adjustedY = y;
      double transformedX[];
      double transformedY[];
      int numOfVertices;
      int configurationNum = CONFIGNUM;
      if(configurationNum == 0){
         transformedX = new double[]{
            x - (4.0 * ASTEROID_SCALE), x - (1.0 * ASTEROID_SCALE), x + (2.0 * ASTEROID_SCALE), x + (4.0 * ASTEROID_SCALE), 
            x + (4.0 * ASTEROID_SCALE), x + (2.0 * ASTEROID_SCALE), x + (0.0 * ASTEROID_SCALE), x + (0.0 * ASTEROID_SCALE), 
            x - (2.0 * ASTEROID_SCALE), x - (4.0 * ASTEROID_SCALE), x - (2.0 * ASTEROID_SCALE), x - (4.0 * ASTEROID_SCALE)};
            
         transformedY = new double[]{
            y + (1.0 * ASTEROID_SCALE), y + (4.0 * ASTEROID_SCALE), y + (4.0 * ASTEROID_SCALE), y + (1.0 * ASTEROID_SCALE),
            y - (2.0 * ASTEROID_SCALE), y - (4.0 * ASTEROID_SCALE), y - (4.0 * ASTEROID_SCALE), y - (1.0 * ASTEROID_SCALE),
            y - (4.0 * ASTEROID_SCALE), y - (1.0 * ASTEROID_SCALE), y + (0.0 * ASTEROID_SCALE), y + (1.0 * ASTEROID_SCALE)};
            
         numOfVertices = 11;
      }else if(configurationNum == 1){
         transformedX = new double[]{
         x - (3.0 * ASTEROID_SCALE), x - (4.0 * ASTEROID_SCALE), x - (2.0 * ASTEROID_SCALE), x + (0.0 * ASTEROID_SCALE), 
         x + (2.0 * ASTEROID_SCALE), x + (4.0 * ASTEROID_SCALE), x + (2.0 * ASTEROID_SCALE), x + (4.0 * ASTEROID_SCALE), 
         x + (2.0 * ASTEROID_SCALE), x - (1.0 * ASTEROID_SCALE), x - (2.0 * ASTEROID_SCALE), x - (4.0 * ASTEROID_SCALE),
         x - (3.0 * ASTEROID_SCALE)};
         
         transformedY = new double[]{
         y + (0.0 * ASTEROID_SCALE), y + (2.0 * ASTEROID_SCALE), y + (4.0 * ASTEROID_SCALE), y + (3.0 * ASTEROID_SCALE),
         y + (4.0 * ASTEROID_SCALE), y + (2.0 * ASTEROID_SCALE), y + (1.0 * ASTEROID_SCALE), y - (1.0 * ASTEROID_SCALE),
         y - (4.0 * ASTEROID_SCALE), y - (3.0 * ASTEROID_SCALE), y - (4.0 * ASTEROID_SCALE), y - (2.0 * ASTEROID_SCALE),
         y + (0.0 * ASTEROID_SCALE)};
         
         numOfVertices = 12;
      }else{
         transformedX = new double[]{
         x - (4.0 * ASTEROID_SCALE), x - (4.0 * ASTEROID_SCALE), x - (1.0 * ASTEROID_SCALE), x - (2.0 * ASTEROID_SCALE), 
         x + (1.0 * ASTEROID_SCALE), x + (4.0 * ASTEROID_SCALE), x + (4.0 * ASTEROID_SCALE), x + (1.0 * ASTEROID_SCALE), 
         x + (4.0 * ASTEROID_SCALE), x + (2.0 * ASTEROID_SCALE), x + (1.0 * ASTEROID_SCALE), x - (3.0 * ASTEROID_SCALE),
         x - (4.0 * ASTEROID_SCALE)};
         
         transformedY = new double[]{
         y - (1.0 * ASTEROID_SCALE), y + (2.0 * ASTEROID_SCALE), y + (2.0 * ASTEROID_SCALE), y + (4.0 * ASTEROID_SCALE),
         y + (4.0 * ASTEROID_SCALE), y + (2.0 * ASTEROID_SCALE), y + (1.0 * ASTEROID_SCALE), y - (0.0 * ASTEROID_SCALE),
         y - (1.0 * ASTEROID_SCALE), y - (4.0 * ASTEROID_SCALE), y - (3.0 * ASTEROID_SCALE), y - (4.0 * ASTEROID_SCALE),
         y - (1.0 * ASTEROID_SCALE)};
         
         numOfVertices = 12;
      }
      graphicsContext.strokePolygon(transformedX, transformedY, numOfVertices);
   }
   
   //Medium Asteroid Drawing Method
   private void drawMediumAsteroid(double x, double y, int CONFIGNUM) {
      double adjustedX = x;
      double adjustedY = y;
      double transformedX[];
      double transformedY[];
      int numOfVertices;
      int configurationNum = CONFIGNUM;
      if(configurationNum == 0){
         transformedX = new double[]{
            x - (4.0 * MEDIUM_ASTEROID_SCALE), x - (1.0 * MEDIUM_ASTEROID_SCALE), x + (2.0 * MEDIUM_ASTEROID_SCALE), x + (4.0 * MEDIUM_ASTEROID_SCALE), 
            x + (4.0 * MEDIUM_ASTEROID_SCALE), x + (2.0 * MEDIUM_ASTEROID_SCALE), x + (0.0 * MEDIUM_ASTEROID_SCALE), x + (0.0 * MEDIUM_ASTEROID_SCALE), 
            x - (2.0 * MEDIUM_ASTEROID_SCALE), x - (4.0 * MEDIUM_ASTEROID_SCALE), x - (2.0 * MEDIUM_ASTEROID_SCALE), x - (4.0 * MEDIUM_ASTEROID_SCALE)};
            
         transformedY = new double[]{
            y + (1.0 * MEDIUM_ASTEROID_SCALE), y + (4.0 * MEDIUM_ASTEROID_SCALE), y + (4.0 * MEDIUM_ASTEROID_SCALE), y + (1.0 * MEDIUM_ASTEROID_SCALE),
            y - (2.0 * MEDIUM_ASTEROID_SCALE), y - (4.0 * MEDIUM_ASTEROID_SCALE), y - (4.0 * MEDIUM_ASTEROID_SCALE), y - (1.0 * MEDIUM_ASTEROID_SCALE),
            y - (4.0 * MEDIUM_ASTEROID_SCALE), y - (1.0 * MEDIUM_ASTEROID_SCALE), y + (0.0 * MEDIUM_ASTEROID_SCALE), y + (1.0 * MEDIUM_ASTEROID_SCALE)};
            
         numOfVertices = 11;
      }else if(configurationNum == 1){
         transformedX = new double[]{
         x - (3.0 * MEDIUM_ASTEROID_SCALE), x - (4.0 * MEDIUM_ASTEROID_SCALE), x - (2.0 * MEDIUM_ASTEROID_SCALE), x + (0.0 * MEDIUM_ASTEROID_SCALE), 
         x + (2.0 * MEDIUM_ASTEROID_SCALE), x + (4.0 * MEDIUM_ASTEROID_SCALE), x + (2.0 * MEDIUM_ASTEROID_SCALE), x + (4.0 * MEDIUM_ASTEROID_SCALE), 
         x + (2.0 * MEDIUM_ASTEROID_SCALE), x - (1.0 * MEDIUM_ASTEROID_SCALE), x - (2.0 * MEDIUM_ASTEROID_SCALE), x - (4.0 * MEDIUM_ASTEROID_SCALE),
         x - (3.0 * MEDIUM_ASTEROID_SCALE)};
         
         transformedY = new double[]{
         y + (0.0 * MEDIUM_ASTEROID_SCALE), y + (2.0 * MEDIUM_ASTEROID_SCALE), y + (4.0 * MEDIUM_ASTEROID_SCALE), y + (3.0 * MEDIUM_ASTEROID_SCALE),
         y + (4.0 * MEDIUM_ASTEROID_SCALE), y + (2.0 * MEDIUM_ASTEROID_SCALE), y + (1.0 * MEDIUM_ASTEROID_SCALE), y - (1.0 * MEDIUM_ASTEROID_SCALE),
         y - (4.0 * MEDIUM_ASTEROID_SCALE), y - (3.0 * MEDIUM_ASTEROID_SCALE), y - (4.0 * MEDIUM_ASTEROID_SCALE), y - (2.0 * MEDIUM_ASTEROID_SCALE),
         y + (0.0 * MEDIUM_ASTEROID_SCALE)};
         
         numOfVertices = 12;
      }else{
         transformedX = new double[]{
         x - (4.0 * MEDIUM_ASTEROID_SCALE), x - (4.0 * MEDIUM_ASTEROID_SCALE), x - (1.0 * MEDIUM_ASTEROID_SCALE), x - (2.0 * MEDIUM_ASTEROID_SCALE), 
         x + (1.0 * MEDIUM_ASTEROID_SCALE), x + (4.0 * MEDIUM_ASTEROID_SCALE), x + (4.0 * MEDIUM_ASTEROID_SCALE), x + (1.0 * MEDIUM_ASTEROID_SCALE), 
         x + (4.0 * MEDIUM_ASTEROID_SCALE), x + (2.0 * MEDIUM_ASTEROID_SCALE), x + (1.0 * MEDIUM_ASTEROID_SCALE), x - (3.0 * MEDIUM_ASTEROID_SCALE),
         x - (4.0 * MEDIUM_ASTEROID_SCALE)};
         
         transformedY = new double[]{
         y - (1.0 * MEDIUM_ASTEROID_SCALE), y + (2.0 * MEDIUM_ASTEROID_SCALE), y + (2.0 * MEDIUM_ASTEROID_SCALE), y + (4.0 * MEDIUM_ASTEROID_SCALE),
         y + (4.0 * MEDIUM_ASTEROID_SCALE), y + (2.0 * MEDIUM_ASTEROID_SCALE), y + (1.0 * MEDIUM_ASTEROID_SCALE), y - (0.0 * MEDIUM_ASTEROID_SCALE),
         y - (1.0 * MEDIUM_ASTEROID_SCALE), y - (4.0 * MEDIUM_ASTEROID_SCALE), y - (3.0 * MEDIUM_ASTEROID_SCALE), y - (4.0 * MEDIUM_ASTEROID_SCALE),
         y - (1.0 * MEDIUM_ASTEROID_SCALE)};
         
         numOfVertices = 12;
      }
      graphicsContext.strokePolygon(transformedX, transformedY, numOfVertices);
   }
   
   // Small Asteroid Drawing Method
   private void drawSmallAsteroid(double x, double y, int CONFIGNUM) {
      double adjustedX = x;
      double adjustedY = y;
      double transformedX[];
      double transformedY[];
      int numOfVertices;
      int configurationNum = CONFIGNUM;
      if(configurationNum == 0){
         transformedX = new double[]{
            x - (4.0 * SMALL_ASTEROID_SCALE), x - (1.0 * SMALL_ASTEROID_SCALE), x + (2.0 * SMALL_ASTEROID_SCALE), x + (4.0 * SMALL_ASTEROID_SCALE), 
            x + (4.0 * SMALL_ASTEROID_SCALE), x + (2.0 * SMALL_ASTEROID_SCALE), x + (0.0 * SMALL_ASTEROID_SCALE), x + (0.0 * SMALL_ASTEROID_SCALE), 
            x - (2.0 * SMALL_ASTEROID_SCALE), x - (4.0 * SMALL_ASTEROID_SCALE), x - (2.0 * SMALL_ASTEROID_SCALE), x - (4.0 * SMALL_ASTEROID_SCALE)};
            
         transformedY = new double[]{
            y + (1.0 * SMALL_ASTEROID_SCALE), y + (4.0 * SMALL_ASTEROID_SCALE), y + (4.0 * SMALL_ASTEROID_SCALE), y + (1.0 * SMALL_ASTEROID_SCALE),
            y - (2.0 * SMALL_ASTEROID_SCALE), y - (4.0 * SMALL_ASTEROID_SCALE), y - (4.0 * SMALL_ASTEROID_SCALE), y - (1.0 * SMALL_ASTEROID_SCALE),
            y - (4.0 * SMALL_ASTEROID_SCALE), y - (1.0 * SMALL_ASTEROID_SCALE), y + (0.0 * SMALL_ASTEROID_SCALE), y + (1.0 * SMALL_ASTEROID_SCALE)};
            
         numOfVertices = 11;
      }else if(configurationNum == 1){
         transformedX = new double[]{
         x - (3.0 * SMALL_ASTEROID_SCALE), x - (4.0 * SMALL_ASTEROID_SCALE), x - (2.0 * SMALL_ASTEROID_SCALE), x + (0.0 * SMALL_ASTEROID_SCALE), 
         x + (2.0 * SMALL_ASTEROID_SCALE), x + (4.0 * SMALL_ASTEROID_SCALE), x + (2.0 * SMALL_ASTEROID_SCALE), x + (4.0 * SMALL_ASTEROID_SCALE), 
         x + (2.0 * SMALL_ASTEROID_SCALE), x - (1.0 * SMALL_ASTEROID_SCALE), x - (2.0 * SMALL_ASTEROID_SCALE), x - (4.0 * SMALL_ASTEROID_SCALE),
         x - (3.0 * SMALL_ASTEROID_SCALE)};
         
         transformedY = new double[]{
         y + (0.0 * SMALL_ASTEROID_SCALE), y + (2.0 * SMALL_ASTEROID_SCALE), y + (4.0 * SMALL_ASTEROID_SCALE), y + (3.0 * SMALL_ASTEROID_SCALE),
         y + (4.0 * SMALL_ASTEROID_SCALE), y + (2.0 * SMALL_ASTEROID_SCALE), y + (1.0 * SMALL_ASTEROID_SCALE), y - (1.0 * SMALL_ASTEROID_SCALE),
         y - (4.0 * SMALL_ASTEROID_SCALE), y - (3.0 * SMALL_ASTEROID_SCALE), y - (4.0 * SMALL_ASTEROID_SCALE), y - (2.0 * SMALL_ASTEROID_SCALE),
         y + (0.0 * SMALL_ASTEROID_SCALE)};
         
         numOfVertices = 12;
      }else{
         transformedX = new double[]{
         x - (4.0 * SMALL_ASTEROID_SCALE), x - (4.0 * SMALL_ASTEROID_SCALE), x - (1.0 * SMALL_ASTEROID_SCALE), x - (2.0 * SMALL_ASTEROID_SCALE), 
         x + (1.0 * SMALL_ASTEROID_SCALE), x + (4.0 * SMALL_ASTEROID_SCALE), x + (4.0 * SMALL_ASTEROID_SCALE), x + (1.0 * SMALL_ASTEROID_SCALE), 
         x + (4.0 * SMALL_ASTEROID_SCALE), x + (2.0 * SMALL_ASTEROID_SCALE), x + (1.0 * SMALL_ASTEROID_SCALE), x - (3.0 * SMALL_ASTEROID_SCALE),
         x - (4.0 * SMALL_ASTEROID_SCALE)};
         
         transformedY = new double[]{
         y - (1.0 * SMALL_ASTEROID_SCALE), y + (2.0 * SMALL_ASTEROID_SCALE), y + (2.0 * SMALL_ASTEROID_SCALE), y + (4.0 * SMALL_ASTEROID_SCALE),
         y + (4.0 * SMALL_ASTEROID_SCALE), y + (2.0 * SMALL_ASTEROID_SCALE), y + (1.0 * SMALL_ASTEROID_SCALE), y - (0.0 * SMALL_ASTEROID_SCALE),
         y - (1.0 * SMALL_ASTEROID_SCALE), y - (4.0 * SMALL_ASTEROID_SCALE), y - (3.0 * SMALL_ASTEROID_SCALE), y - (4.0 * SMALL_ASTEROID_SCALE),
         y - (1.0 * SMALL_ASTEROID_SCALE)};
         
         numOfVertices = 12;
      }
      graphicsContext.strokePolygon(transformedX, transformedY, numOfVertices);
   }
   
   // Ship Drawing Method
   private void drawShip(double x, double y, double Deg) {
      double adjustedX = x;
      double adjustedY = y;
      
      double maxYFromOrigin = 1.5 * CANVAS_SCALE;
      double maxXFromOrigin = 1.0 * CANVAS_SCALE;
      double baseFromOrigin = 0.75 * CANVAS_SCALE;
      
      double tipInitX = ((0-0.001)*Math.cos(Math.toRadians(Deg)))+((0-maxYFromOrigin)*Math.sin(Math.toRadians(Deg)));
      double tipInitY = ((0-maxYFromOrigin)*Math.cos(Math.toRadians(Deg)))+((0-0.001)*Math.sin(Math.toRadians(Deg)));
      
      double tipFinalX = (0.001*Math.cos(Math.toRadians(Deg)))+((0-maxYFromOrigin)*Math.sin(Math.toRadians(Deg)));
      double tipFinalY = ((0-maxYFromOrigin)*Math.cos(Math.toRadians(Deg)))+(0.001*Math.sin(Math.toRadians(Deg)));
      /* //Debug feature: Give the exact coordinates of the tip.
      System.out.println("Tip X: " + (x-tipInitX));
      System.out.println("Tip Y: " + (y+tipInitY));
      */
      double tail1X = ((0-maxXFromOrigin)*Math.cos(Math.toRadians(Deg)))-((0-maxYFromOrigin)*Math.sin(Math.toRadians(Deg)));
      double tail1Y = ((0-maxYFromOrigin)*Math.cos(Math.toRadians(Deg)))+((0-maxXFromOrigin)*Math.sin(Math.toRadians(Deg)));
      
      double tail2X = (maxXFromOrigin*Math.cos(Math.toRadians(Deg)))-((0-maxYFromOrigin)*Math.sin(Math.toRadians(Deg)));
      double tail2Y = ((0-maxYFromOrigin)*Math.cos(Math.toRadians(Deg)))+(maxXFromOrigin*Math.sin(Math.toRadians(Deg)));
      
      double base1X = ((0-baseFromOrigin)*Math.cos(Math.toRadians(Deg)))-((0-baseFromOrigin)*Math.sin(Math.toRadians(Deg)));
      double base1Y = ((0-baseFromOrigin)*Math.cos(Math.toRadians(Deg)))+((0-baseFromOrigin)*Math.sin(Math.toRadians(Deg)));
      
      double base2X = (baseFromOrigin*Math.cos(Math.toRadians(Deg)))-((0-baseFromOrigin)*Math.sin(Math.toRadians(Deg)));
      double base2Y = ((0-baseFromOrigin)*Math.cos(Math.toRadians(Deg)))+(baseFromOrigin*Math.sin(Math.toRadians(Deg)));

      double transformedX[] = new double[]{
      x - tipInitX, x - tail1X, x - base1X, x - base2X, 
      x - tail2X, x - tipFinalX};
      
      double transformedY[] = new double[]{
      y + tipInitY, y - tail1Y, y - base1Y, y - base2Y,
      y - tail2Y, y - tipFinalY};
      
      int numOfVertices = 5;
      
      graphicsContext.strokePolygon(transformedX, transformedY, numOfVertices);
      /* //Debug feature: Draw point onto wherever the tip of the ship is.
      graphicsContext.setFill(Color.RED);
      graphicsContext.fillOval((x-tipFinalX)-2.5, (y+tipFinalY) - 2.5, 5, 5);
      */
   }
   
   public void drawLazer(double x, double y, double radius){
      double adjustedX = x - radius;
      double adjustedY = y - radius;
      double diameter = 2.0 * radius;
      graphicsContext.fillOval(adjustedX, adjustedY, diameter, diameter);
   }
   
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