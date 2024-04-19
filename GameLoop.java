import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer{
    private World world;
    private Presentation presentation;
    private long lastTime;

    public GameLoop(World world, Presentation presentation) {
        this.world = world;
        this.presentation = presentation;
        lastTime = System.nanoTime();
    }

    @Override
    public void handle(long now) {

        // do the physics
        long timeElapsed = now - lastTime;
        world.run(timeElapsed);
        lastTime = now;

        // render the ball
        presentation.render();
    }
    
}
