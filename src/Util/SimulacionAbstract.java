package Util;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public abstract class SimulacionAbstract extends Pane{

    public Canvas canvas;
    public GraphicsContext gc;
    public AnimationTimer atimer;

    public Random random;

    public SimulacionAbstract(ControlAbstract control) {
        this.random = new Random();
        this.canvas = new Canvas(800, 400);
        this.gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
        atimer = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                update(arg0);
                draw();
            }
        };
    }

    public abstract void update(long now);

    public abstract void draw();

}
