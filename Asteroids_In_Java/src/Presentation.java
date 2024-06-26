import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Random;

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
   
   public boolean toggleHitbox = false;

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
      renderShip();
      renderLazers();

      renderAsteroids();
      renderMediumAsteroids();
      renderSmallAsteroids();
      renderAlienShips();
      
      renderScore();
      renderNumberOfAsteroids();
      renderLevelIndicator();
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
      
      graphicsContext.setStroke(Color.WHITE);
      drawShip(shipX, shipY, shipDirection);
      
      if(toggleHitbox){
         graphicsContext.setFill(new Color(1, 0, 0, 0.5));
         drawHitbox(shipHitbox.getX(), shipHitbox.getY(), shipHitbox);
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
            drawHitbox(lazerHitbox.getX(), lazerHitbox.getY(), lazerHitbox);
         }
      }
   }
   private void renderAlienShips(){
      AlienShip alienShipArray[] = world.getAlienShipsAsArray();
      double alienShipX = 0.0;
      double alienShipY = 0.0;
      HitBox alienShipHitbox;
      Random random = new Random();
      
      int r = 0;
      int g = 0;
      int b = 0;
      Color randomColor;
      
      for(int i = 0; i < alienShipArray.length; i++) {
         alienShipX = convertPhysicsScaletoPresentationScale(alienShipArray[i].getX());
         alienShipY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(alienShipArray[i].getY()));
         alienShipHitbox = alienShipArray[i].getHitBox();
         
         if(world.getAdditionalAsteroidCount() >= 5){
            r = random.nextInt(256);
            g = random.nextInt(256);
            b = random.nextInt(256);
            randomColor = Color.rgb(r, g, b);
            graphicsContext.setStroke(randomColor);
            graphicsContext.setFill(randomColor);
         }else{
            graphicsContext.setStroke(Color.WHITE);
            graphicsContext.setFill(Color.WHITE);
         }
         drawAlienShip(alienShipX, alienShipY);
         
         if(toggleHitbox){
            graphicsContext.setFill(new Color(1, 0, 0, 0.5));
            drawHitbox(alienShipHitbox.getX(), alienShipHitbox.getY(), alienShipHitbox);
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
      Random random = new Random();
      
      int r = 0;
      int g = 0;
      int b = 0;
      Color randomColor;
      
      for(int i = 0; i < asteroidArray.length; i++) {
         asteroidX = convertPhysicsScaletoPresentationScale(asteroidArray[i].getX());
         asteroidY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(asteroidArray[i].getY()));
         asteroidConfigNumber = asteroidArray[i].getConfigNumber();
         asteroidHitbox = asteroidArray[i].getHitBox();
         
         if(world.getAdditionalAsteroidCount() >= 5){
            r = random.nextInt(256);
            g = random.nextInt(256);
            b = random.nextInt(256);
            randomColor = Color.rgb(r, g, b);
            graphicsContext.setStroke(randomColor);
         }else{
            graphicsContext.setStroke(Color.WHITE);
         }
         drawAsteroid(asteroidX, asteroidY, asteroidConfigNumber);
         
         if(toggleHitbox){
            graphicsContext.setFill(new Color(1, 0, 0, 0.5));
            drawHitbox(asteroidHitbox.getX(), asteroidHitbox.getY(), asteroidHitbox);
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
      Random random = new Random();
      
      int r = 0;
      int g = 0;
      int b = 0;
      Color randomColor;
      
      for(int i = 0; i < asteroidArray.length; i++) {
         asteroidX = convertPhysicsScaletoPresentationScale(asteroidArray[i].getX());
         asteroidY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(asteroidArray[i].getY()));
         asteroidConfigNumber = asteroidArray[i].getConfigNumber();
         asteroidHitbox = asteroidArray[i].getHitBox();
         
         if(world.getAdditionalAsteroidCount() >= 5){
            r = random.nextInt(256);
            g = random.nextInt(256);
            b = random.nextInt(256);
            randomColor = Color.rgb(r, g, b);
            graphicsContext.setStroke(randomColor);
            graphicsContext.setFill(randomColor);
         }else{
            graphicsContext.setStroke(Color.WHITE);
            graphicsContext.setFill(Color.WHITE);
         }
         drawMediumAsteroid(asteroidX, asteroidY, asteroidConfigNumber);
         
         if(toggleHitbox){
            graphicsContext.setFill(new Color(1, 0, 0, 0.5));
            drawHitbox(asteroidHitbox.getX(), asteroidHitbox.getY(), asteroidHitbox);
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
      Random random = new Random();
      
      int r = 0;
      int g = 0;
      int b = 0;
      Color randomColor;
      
      for(int i = 0; i < asteroidArray.length; i++) {
         asteroidX = convertPhysicsScaletoPresentationScale(asteroidArray[i].getX());
         asteroidY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(asteroidArray[i].getY()));
         asteroidConfigNumber = asteroidArray[i].getConfigNumber();
         asteroidHitbox = asteroidArray[i].getHitBox();
         
         if(world.getAdditionalAsteroidCount() >= 5){
            r = random.nextInt(256);
            g = random.nextInt(256);
            b = random.nextInt(256);
            randomColor = Color.rgb(r, g, b);
            graphicsContext.setStroke(randomColor);
            graphicsContext.setFill(randomColor);
         }else{
            graphicsContext.setStroke(Color.WHITE);
            graphicsContext.setFill(Color.WHITE);
         }
         drawSmallAsteroid(asteroidX, asteroidY, asteroidConfigNumber);
         
         if(toggleHitbox){
            graphicsContext.setFill(new Color(1, 0, 0, 0.5));
            drawHitbox(asteroidHitbox.getX(), asteroidHitbox.getY(), asteroidHitbox);
         }
      }
   }
   
   // Render call for the score display
   public void renderScore(){
      String scoreboardString = world.getScoreString();
      double scoreboardX =  20;
      double scoreboardY =  20;
      int fontSize = 18;
      drawScore(scoreboardString, scoreboardX, scoreboardY, fontSize);
   }
   
   public void renderNumberOfAsteroids(){
      int numOfAsteroids = world.getCurrAsteroidCount();
      double indicatorX = 20;
      double indicatorY = 40;
      int fontSize = 18;
      drawAsteroidCountIndicator(numOfAsteroids, indicatorX, indicatorY, fontSize);
   }
   
   public void renderLevelIndicator(){
      int numOfAsteroids = world.getLevelNumber();
      double indicatorX = 20;
      double indicatorY = 60;
      int fontSize = 18;
      drawLevelIndicator(numOfAsteroids, indicatorX, indicatorY, fontSize);
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

   // Alien Ship Drawing Method
   private void drawAlienShip(double x, double y) {
      double adjustedX = x;
      double adjustedY = y;

      double unitFromOrigin = 0.5 * CANVAS_SCALE;
      
      //Top Trapezoid
      double topTrapX[] = new double[]{
      x - unitFromOrigin, x + unitFromOrigin, x + (2*unitFromOrigin), x - (2*unitFromOrigin), x - unitFromOrigin
      };
      double topTrapY[] = new double[]{
      y - (4*unitFromOrigin), y - (4*unitFromOrigin), y - (2*unitFromOrigin), y - (2*unitFromOrigin), y - (4*unitFromOrigin)
      };
      
      //Base Trapezoid
      double baseTrapX[] = new double[]{
      x - (2*unitFromOrigin), x + (2*unitFromOrigin), x + (6*unitFromOrigin), x - (6*unitFromOrigin), x - (2*unitFromOrigin)
      };
      double baseTrapY[] = new double[]{
      y - (2*unitFromOrigin), y - (2*unitFromOrigin), y, y, y - (2*unitFromOrigin)
      };
      
      //Base Rectangle
      double baseRectX[] = new double[]{
      x - (8*unitFromOrigin), x + (8*unitFromOrigin), x + (8*unitFromOrigin), x - (8*unitFromOrigin), x - (8*unitFromOrigin)
      };
      double baseRectY[] = new double[]{
      y, y, y + (2*unitFromOrigin), y + (2*unitFromOrigin), y
      };
      
      //Base Trapezoid Inverted
      double baseInvTrapX[] = new double[]{
      x - (6*unitFromOrigin), x + (6*unitFromOrigin), x + (2*unitFromOrigin), x - (2*unitFromOrigin), x - (6*unitFromOrigin)
      };
      double baseInvTrapY[] = new double[]{
      y + (2*unitFromOrigin), y + (2*unitFromOrigin), y + (4*unitFromOrigin), y + (4*unitFromOrigin), y + (2*unitFromOrigin)
      };
      
      int numOfVertices = 4;
      
      graphicsContext.strokePolygon(topTrapX, topTrapY, numOfVertices);
      graphicsContext.strokePolygon(baseTrapX, baseTrapY, numOfVertices);
      graphicsContext.fillPolygon(baseRectX, baseRectY, numOfVertices);
      graphicsContext.strokePolygon(baseInvTrapX, baseInvTrapY, numOfVertices);
   }
   
   private void drawHitbox(double x, double y, HitBox hitbox) {
      double adjustedX = convertPhysicsScaletoPresentationScale(x);
      double adjustedY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(y));
      double width = hitbox.getWidth() * CANVAS_SCALE;
      double height = hitbox.getHeight() * CANVAS_SCALE;
      graphicsContext.fillRect(adjustedX - (width/2), adjustedY - (height/2), width, height);
      //Get Upper Right Point
      
      double pointUpperRightX = convertPhysicsScaletoPresentationScale(hitbox.getUpperRight().getX());
      double pointUpperRightY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(hitbox.getUpperRight().getY()));
      graphicsContext.fillOval(pointUpperRightX-(5/2),pointUpperRightY-(5/2),5,5);
      
      double pointUpperLeftX = convertPhysicsScaletoPresentationScale(hitbox.getUpperLeft().getX());
      double pointUpperLeftY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(hitbox.getUpperLeft().getY()));
      graphicsContext.fillOval(pointUpperLeftX-(5/2),pointUpperLeftY-(5/2),5,5);
      
      double pointLowerLeftX = convertPhysicsScaletoPresentationScale(hitbox.getLowerLeft().getX());
      double pointLowerLeftY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(hitbox.getLowerLeft().getY()));
      graphicsContext.fillOval(pointLowerLeftX-(5/2),pointLowerLeftY-(5/2),5,5);
      
      double pointLowerRightX = convertPhysicsScaletoPresentationScale(hitbox.getLowerRight().getX());
      double pointLowerRightY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(hitbox.getLowerRight().getY()));
      graphicsContext.fillOval(pointLowerRightX-(5/2),pointLowerRightY-(5/2),5,5);
   }
   
   private double convertPhysicsScaletoPresentationScale(double location) {
      return location * CANVAS_SCALE;
   }
   
   public void drawScore(String scoreboardString, double scoreboardX, double scoreboardY, int fontSize){
      graphicsContext.setFill(Color.WHITE);
      graphicsContext.setFont(Font.font("Courier New", fontSize));
      graphicsContext.fillText("Score: "+scoreboardString, scoreboardX, scoreboardY);
   }
   
   public void drawAsteroidCountIndicator(int asteroidCount, double indicatorX, double indicatorY, int fontSize){
      graphicsContext.setFill(Color.WHITE);
      graphicsContext.setFont(Font.font("Courier New", fontSize));
      graphicsContext.fillText("Asteroids: "+asteroidCount, indicatorX, indicatorY);
   }
   
   public void drawLevelIndicator(int levelNumber, double indicatorX, double indicatorY, int fontSize){
      graphicsContext.setFill(Color.WHITE);
      graphicsContext.setFont(Font.font("Courier New", fontSize));
      graphicsContext.fillText("Level: "+levelNumber, indicatorX, indicatorY);
   }
   
   /** The physics world has a normal origin (lower left is 0,0), while the render space has typical graphics origin (upper left is 0,0) */
   private double convertPhysicsOriginToPresentationOrigin(double physicsYLocation) {
      double presentationYLocation = canvasYDimension - physicsYLocation;
      return presentationYLocation;
   }
}