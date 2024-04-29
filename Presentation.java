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
      graphicsContext.setStroke(Color.WHITE);
      renderShip();
      renderAsteroids();
      renderLazers();
      renderScore();
      
      stage.show();     
   }
   
   public void renderShip(){
      Ship shipToMove = world.getShip();
      double shipX = 0.0;
      double shipY = 0.0;
      HitBox shipHitbox;
      
      shipX = convertPhysicsScaletoPresentationScale(shipToMove.getX());
      shipY = convertPhysicsOriginToPresentationOrigin(convertPhysicsScaletoPresentationScale(shipToMove.getY()));
      shipHitbox = shipToMove.getHitBox();
      drawShip(shipX, shipY);
      
      if(toggleHitbox){
         graphicsContext.setFill(new Color(1, 0, 0, 0.5));
         drawHitbox(shipX, shipY, shipHitbox);
      }
   }
   
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
   
   public void renderScore(){
      String scoreboardString = world.getScoreString();
      double scoreboardX =  20;
      double scoreboardY =  20;
      int fontSize = 18;
      drawScore(scoreboardString, scoreboardX, scoreboardY, fontSize);
      
   }
   
   private void drawAsteroid(double x, double y, int CONFIGNUM) {
      double adjustedX = x;
      double adjustedY = y;
      double transformedX[];
      double transformedY[];
      int numOfVertices;
      int configurationNum = CONFIGNUM;
      if(configurationNum == 0){
         transformedX = new double[]{
            x - (4.0 * CANVAS_SCALE), x - (1.0 * CANVAS_SCALE), x + (2.0 * CANVAS_SCALE), x + (4.0 * CANVAS_SCALE), 
            x + (4.0 * CANVAS_SCALE), x + (2.0 * CANVAS_SCALE), x + (0.0 * CANVAS_SCALE), x + (0.0 * CANVAS_SCALE), 
            x - (2.0 * CANVAS_SCALE), x - (4.0 * CANVAS_SCALE), x - (2.0 * CANVAS_SCALE), x - (4.0 * CANVAS_SCALE)};
            
         transformedY = new double[]{
            y + (1.0 * CANVAS_SCALE), y + (4.0 * CANVAS_SCALE), y + (4.0 * CANVAS_SCALE), y + (1.0 * CANVAS_SCALE),
            y - (2.0 * CANVAS_SCALE), y - (4.0 * CANVAS_SCALE), y - (4.0 * CANVAS_SCALE), y - (1.0 * CANVAS_SCALE),
            y - (4.0 * CANVAS_SCALE), y - (1.0 * CANVAS_SCALE), y + (0.0 * CANVAS_SCALE), y + (1.0 * CANVAS_SCALE)};
            
         numOfVertices = 11;
      }else if(configurationNum == 1){
         transformedX = new double[]{
         x - (3.0 * CANVAS_SCALE), x - (4.0 * CANVAS_SCALE), x - (2.0 * CANVAS_SCALE), x + (0.0 * CANVAS_SCALE), 
         x + (2.0 * CANVAS_SCALE), x + (4.0 * CANVAS_SCALE), x + (2.0 * CANVAS_SCALE), x + (4.0 * CANVAS_SCALE), 
         x + (2.0 * CANVAS_SCALE), x - (1.0 * CANVAS_SCALE), x - (2.0 * CANVAS_SCALE), x - (4.0 * CANVAS_SCALE),
         x - (3.0 * CANVAS_SCALE)};
         
         transformedY = new double[]{
         y + (0.0 * CANVAS_SCALE), y + (2.0 * CANVAS_SCALE), y + (4.0 * CANVAS_SCALE), y + (3.0 * CANVAS_SCALE),
         y + (4.0 * CANVAS_SCALE), y + (2.0 * CANVAS_SCALE), y + (1.0 * CANVAS_SCALE), y - (1.0 * CANVAS_SCALE),
         y - (4.0 * CANVAS_SCALE), y - (3.0 * CANVAS_SCALE), y - (4.0 * CANVAS_SCALE), y - (2.0 * CANVAS_SCALE),
         y + (0.0 * CANVAS_SCALE)};
         
         numOfVertices = 12;
      }else{
         transformedX = new double[]{
         x - (4.0 * CANVAS_SCALE), x - (4.0 * CANVAS_SCALE), x - (1.0 * CANVAS_SCALE), x - (2.0 * CANVAS_SCALE), 
         x + (1.0 * CANVAS_SCALE), x + (4.0 * CANVAS_SCALE), x + (4.0 * CANVAS_SCALE), x + (1.0 * CANVAS_SCALE), 
         x + (4.0 * CANVAS_SCALE), x + (2.0 * CANVAS_SCALE), x + (1.0 * CANVAS_SCALE), x - (3.0 * CANVAS_SCALE),
         x - (4.0 * CANVAS_SCALE)};
         
         transformedY = new double[]{
         y - (1.0 * CANVAS_SCALE), y + (2.0 * CANVAS_SCALE), y + (2.0 * CANVAS_SCALE), y + (4.0 * CANVAS_SCALE),
         y + (4.0 * CANVAS_SCALE), y + (2.0 * CANVAS_SCALE), y + (1.0 * CANVAS_SCALE), y - (0.0 * CANVAS_SCALE),
         y - (1.0 * CANVAS_SCALE), y - (4.0 * CANVAS_SCALE), y - (3.0 * CANVAS_SCALE), y - (4.0 * CANVAS_SCALE),
         y - (1.0 * CANVAS_SCALE)};
         
         numOfVertices = 12;
      }
      graphicsContext.strokePolygon(transformedX, transformedY, numOfVertices);
   }
   
   private void drawShip(double x, double y) {
      double adjustedX = x;
      double adjustedY = y;
      double transformedX[] = new double[]{
      x - (0.0 * CANVAS_SCALE), x - (1.0 * CANVAS_SCALE), x - (0.75 * CANVAS_SCALE), x + (0.75 * CANVAS_SCALE), 
      x + (1.0 * CANVAS_SCALE), x + (0.0 * CANVAS_SCALE)};
      
      double transformedY[] = new double[]{
      y - (1.5 * CANVAS_SCALE), y + (1.5 * CANVAS_SCALE), y + (0.75 * CANVAS_SCALE), y + (0.75 * CANVAS_SCALE),
      y + (1.5 * CANVAS_SCALE), y - (1.5 * CANVAS_SCALE)};
      
      int numOfVertices = 5;
      
      graphicsContext.strokePolygon(transformedX, transformedY, numOfVertices);
   }
   
   public void drawLazer(double x, double y, double radius){
      double adjustedX = x - radius;
      double adjustedY = y - radius;
      double diameter = 2.0 * radius;
      graphicsContext.fillOval(adjustedX, adjustedY, diameter, diameter);
   }
   
   //could be changed so 4 corners of hitbox are parameters
   private void drawHitbox(double x, double y, HitBox hitbox) {
      double adjustedX = x;
      double adjustedY = y;
      double width = hitbox.getWidth()*10;
      double height = hitbox.getHeight()*10;
      graphicsContext.fillRect(adjustedX, adjustedY, width, height);
   }
   
   
   public void drawScore(String scoreboardString, double scoreboardX, double scoreboardY, int fontSize){
      graphicsContext.setFill(Color.WHITE);
      graphicsContext.setFont(Font.font("Courier New", fontSize));
      graphicsContext.fillText("Score: "+scoreboardString, scoreboardX, scoreboardY);
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