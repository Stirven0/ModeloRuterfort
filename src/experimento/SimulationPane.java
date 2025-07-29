package experimento;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SimulationPane extends Pane {

    private Control_Simulacion control;
    private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer atimer;

    // Elementos dela simulacion
    private Emisor_Alfa emisorA;
    private Oro oro;
    private List<Detector> detectores;
    private List<P_Alfa> p_Alfas;
    // Estado de la simulacion
    public int estadoActual = 2;
    private int e_reiniciando = 0;
    private int e_corriendo = 1;
    private int e_pausado = 2;

    // private long velocidad;
    private double intencidad;

    private long delta = 0;
    private Random random;

    public SimulationPane(Control_Simulacion control) {
        this.control = control;
        // velocidad = (long) control.getVelocidad();
        intencidad = control.getIntencidad();
        canvas = new Canvas(800, 400);
        gc = canvas.getGraphicsContext2D();

        random = new Random();

        detectores = new ArrayList<>();
        p_Alfas = new ArrayList<>();
        emisorA = new Emisor_Alfa(50, 100, 30, 150);
        oro = new Oro(450, 100, 30, 150);
        // p_Alfas.add(new P_Alfa(100, 100, velocidad));
        setupControl();
        setupDetectores();
        setBackground(Background.fill(Color.WHITE));
        getChildren().add(canvas);

        atimer = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                // Pausado
                if (estadoActual == e_pausado) {
                    draw();
                    System.out.println("la simulacion esta Pausada");
                }
                // Simulando
                if (estadoActual == e_corriendo) {
                    System.out.println("la simulacion esta corriendo");
                    update(arg0);
                    draw();
                }
            }
        };
        atimer.start();

    }

    private void setupControl() {

        control.playEvent(e -> {
            this.estadoActual = e_corriendo;
            ((Button) e.getSource()).setText("hola");
            atimer.start();
        });
        control.pauseEvent(e -> {
            this.estadoActual = e_pausado;
        });
        control.rebootEvent(e -> {
            this.estadoActual = e_reiniciando;
            p_Alfas.clear();
            for (Detector detector : detectores) {
                detector.activate = false;
            }

            System.out.println("la simulacion se esta reiniciando");
            atimer.stop();
        });

        control.velocidadEvent(e -> {
            // ((Slider)e.getSource()).
            for (P_Alfa particu : p_Alfas) {
                Slider s = ((Slider) e.getSource());
                particu.velocidadPoperty().bind(s.valueProperty());
                // particu.setVelocidad((long)((Slider) e.getSource()).getValue());
            }
            // velocidad = (long)((Slider) e.getSource()).getValue();
        });
        control.intencidadEvent(e -> {
            intencidad = ((Slider) e.getSource()).getValue();
        });
    }

    private void setupDetectores() {
        int detecTotal = 12;
        int detecRadio = 130;
        double desface = Math.toRadians(15);
        for (int i = 0; i < detecTotal; i++) {
            double angulo = (2 * Math.PI * i / detecTotal) + desface;
            int x = (int) (oro.getNucleo().getX() + detecRadio * Math.cos(angulo));
            int y = (int) (oro.getNucleo().getY() + detecRadio * Math.sin(angulo));
            detectores.add(new Detector(x, y, 15));
        }

    }

    private void update(long now) {

        creandoPAlfas();
        for (P_Alfa particula : p_Alfas) {
            particula.desplase(now);
            for (Detector detector : detectores) {
                if (particula.isColicionWithDetector(detector)) {
                    p_Alfas.remove(particula);
                } 
                if (particula.isExitScreem(gc)) {
                    p_Alfas.remove(particula);
                }
                
            }
        }

        

    }

    private void creandoPAlfas() {
        while (intencidad > p_Alfas.size()) {
            int y = random.nextInt(emisorA.getTop(), emisorA.getButton() - 10);
            P_Alfa particula = new P_Alfa(emisorA.getBorderRight(), y);
            p_Alfas.add(particula);
        }
    }

    private void draw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        emisorA.draw(gc);
        oro.draw(gc);
        for (Detector detector : detectores) {
            detector.draw(gc);
        }
        for (P_Alfa particula : p_Alfas) {
            particula.draw(gc);
        }
    }

}
