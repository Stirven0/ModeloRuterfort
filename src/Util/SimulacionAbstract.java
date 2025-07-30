package Util;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public abstract class SimulacionAbstract extends Pane{

    private ControlAbstract control;
    private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer atimer;

    private Random random;

    public SimulacionAbstract(ControlAbstract control) {
        this.control = control;
        this.control.setContext(this);
        this.random = new Random();
        this.canvas = new Canvas(800, 400);
        this.gc = canvas.getGraphicsContext2D();

        atimer = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                update();
                draw();
                if (control.reboot == true) {
                    reboot();
                }
            }
        };
        atimer.start();
    }
    
    public abstract void update();
    public abstract void draw(); 
    public abstract void reboot();

}
