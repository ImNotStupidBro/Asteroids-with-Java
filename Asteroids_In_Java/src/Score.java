// this class controls the score of the game
public class Score{
   private int totalPoints = 0;
   private int smallAsteroidsHit;
   private int mediumAsteroidsHit;
   private int largeAsteroidsHit;
   private int alienShipsHit;
   
   // these are modeled after the actual game itself
   private static final int largeMultiplier = 20;
   private static final int mediumMultiplier = 30;
   private static final int smallMultiplier = 50;
   private static final int alienMultiplier = 100;
   
   //default constructor (this one will most likely be called because at the beginning of the game nothing is hit)
   public Score(){
      this.smallAsteroidsHit = 0;
      this.mediumAsteroidsHit = 0;
      this.largeAsteroidsHit = 0;
      this.alienShipsHit = 0;
   }
   
   //constructor just incase something is hit off the bat
   public Score(int smallAsteroidsHit, int mediumAsteroidsHit, int largeAsteroidsHit, int alienShipsHit){
      this.smallAsteroidsHit = smallAsteroidsHit;
      this.mediumAsteroidsHit = mediumAsteroidsHit;
      this.largeAsteroidsHit = largeAsteroidsHit;
      this.alienShipsHit = alienShipsHit;
   }
  
   // method to add to score (pass it the total amount of ships hit and then it adds it to score)
   public void addScore(int smallAsteroids, int mediumAsteroids, int largeAsteroids, int alienShips){
      totalPoints += (smallAsteroids*smallMultiplier)+(mediumAsteroids*mediumMultiplier)+(largeAsteroids*largeMultiplier)+(alienShips*alienMultiplier);
   }
   
   public int getScoreInt(){
      return totalPoints;
   }
   
   public String getScoreString(){
      return Integer.toString(totalPoints);
   }
}