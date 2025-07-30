package experimento;

import Util.ControlAbstract;
import Util.SimulacionAbstract;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;

// import javafx.animation.AnimationTimer;
// import javafx.scene.canvas.Canvas;
// import javafx.scene.canvas.GraphicsContext;
// import javafx.scene.layout.Background;
// import javafx.scene.layout.Pane;
// import javafx.scene.paint.Color;

public class Simulation extends SimulacionAbstract {

    public Simulation(ControlAbstract control) {
        super(control);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        
    }

    @Override
    public void reboot() {
        
    }

    
    // private Control_Simulacion control;
    // private Canvas canvas;
    // private GraphicsContext gc;
    // private AnimationTimer atimer;

    // // Elementos dela simulacion
    // private Emisor_Alfa emisorA;
    // private Oro oro;
    // private List<Detector> detectores;
    // private List<P_Alfa> p_Alfas;
    // // Estado de la simulacion

    // private Random random;

    // public SimulationPane(Control_Simulacion control) {
    //     this.control = control;
    //     this.control.setContext(this);

    //     canvas = new Canvas(800, 400);
    //     gc = canvas.getGraphicsContext2D();

    //     random = new Random();

    //     detectores = new ArrayList<>();
    //     p_Alfas = new ArrayList<>();
    //     emisorA = new Emisor_Alfa(50, 100, 30, 150);
    //     oro = new Oro(450, 100, 30, 150);
    //     // p_Alfas.add(new P_Alfa(100, 100, velocidad));
    //     setupDetectores();
    //     setBackground(Background.fill(Color.WHITE));
    //     getChildren().add(canvas);

    //     atimer = new AnimationTimer() {
    //         @Override
    //         public void handle(long arg0) {
    //             update(arg0);
    //             draw();
    //             if (control.reboot == true) {
    //                 p_Alfas.clear();
    //                 detectores.clear();
    //                 setupDetectores();
    //                 atimer.stop();
    //             }
    //         }
    //     };
    //     atimer.start();

    // }

    // private void setupDetectores() {
    //     int detecTotal = 12;
    //     int detecRadio = 130;
    //     double desface = Math.toRadians(15);
    //     for (int i = 0; i < detecTotal; i++) {
    //         double angulo = (2 * Math.PI * i / detecTotal) + desface;
    //         int x = (int) (oro.getNucleo().getX() + detecRadio * Math.cos(angulo));
    //         int y = (int) (oro.getNucleo().getY() + detecRadio * Math.sin(angulo));
    //         detectores.add(new Detector(x, y, 15));
    //     }

    // }

    // private void update(long now) {

    //     creandoPAlfas();
    //     for (P_Alfa particula : p_Alfas) {
    //         particula.desplase(now);
    //         for (Detector detector : detectores) {
    //             if (particula.isColicionWithDetector(detector)) {
    //                 p_Alfas.remove(particula);
    //             } 
    //             if (particula.isExitScreem(gc)) {
    //                 p_Alfas.remove(particula);
    //             }
                
    //         }
    //     }

        

    // }

    // private void creandoPAlfas() {
    //     while (control.intencidad > p_Alfas.size()) {
    //         int y = random.nextInt(emisorA.getY(), emisorA.getY() + emisorA.getAlto() - 10);
    //         int x = random.nextInt(emisorA.getX(), emisorA.getX() + emisorA.getAncho());
    //         P_Alfa particula = new P_Alfa(y, y);
    //         p_Alfas.add(particula);
    //     }
    // }

    // private void draw() {
    //     gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    //     emisorA.draw(gc);
    //     oro.draw(gc);
    //     for (Detector detector : detectores) {
    //         detector.draw(gc);
    //     }
    //     for (P_Alfa particula : p_Alfas) {
    //         particula.draw(gc);
    //     }
    // }

}
