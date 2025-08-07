package Experimento1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Util.SimulacionAbstract;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Simulation extends SimulacionAbstract {

    public Control control;

    // Elementos dela simulacion
    public Emisor_Alfa emisorA;
    public Oro oro;
    public List<Detector> detectores;
    public LinkedList<P_Alfa> p_Alfas;

    //Contadores de las leyendas
    public int particulasDesviadas;
    public int particulasLansadas;
    public int particulasRebotadas;

    public Simulation(Control control) {
        super(control);
        this.control = control;
        this.control.setContext(this);
        this.particulasDesviadas = 0;
        this.particulasLansadas = 0;
        this.particulasRebotadas = 0;

        emisorA = new Emisor_Alfa(50,175, 30, 50, this);
        emisorA.alinemetCenter(true);
        oro = new Oro(450, 100, 30, 150, this);
        detectores = new ArrayList<>();
        p_Alfas = new LinkedList<>();

        setupDetectores();
        atimer.start();
    }

    @Override
    public void update(long now) {

        emisorA.emiter(now); //Emitimos la particula cada cierto tiempo

        for(P_Alfa particula : p_Alfas){ //detectamos si la particla  choca con un detector o sale de la pantalla para activar la bandera "destroid"
            particula.desplase(now);
            oro.onInteractueWithParticle(particula);
            for (Detector detector : detectores) {
                detector.onColicionWithParticle(particula);
            }
            particula.onExitScreem();
        }

        Iterator<P_Alfa> iter = p_Alfas.iterator(); //removemos la particula que tenga activa la bandera "destroid" dela lista
        while (iter.hasNext()) {
            P_Alfa particula = iter.next();
            if (particula.destroid == true) {
                iter.remove();
            }
        }
    }

    @Override
    public void draw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        emisorA.draw();
        oro.draw(gc);
        for (Detector detector : detectores) {
            detector.draw(gc);
        }
        for (P_Alfa particula : p_Alfas) {
            if (particula != null){
                particula.draw();
            }
        }
        stadisticas();
    }

    public void setupDetectores() {
        if (detectores != null) {
            int detecTotal = 12;
            int detecRadio = 130;
            double desface = Math.toRadians(0);
            for (int i = 0; i < detecTotal; i++) {
                if (i == 6) {
                    continue;
                }
                double angulo = (2 * Math.PI * i / detecTotal) + desface;
                int x = (int) (oro.getNucleo().getX() + detecRadio * Math.cos(angulo));
                int y = (int) (oro.getNucleo().getY() + detecRadio * Math.sin(angulo));
                detectores.add(new Detector(x, y, 15, this));
            }
        }

    }

    public void stadisticas(){
        double maxX = canvas.getWidth();
        double maxY = canvas.getHeight();

        double x = maxX - 150;
        double y = (maxY / 2) - (10); // despues del signo "-" viene e auste en vertical
        double interlineado = 10;
        //Show text
        gc.setFont(new Font("Arial", 10));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFill(Color.BLACK);

        gc.fillText("Estadisticas del Experimento", x, y);
        gc.fillText("Particulas Lansadas: " + particulasLansadas, x, y + interlineado * 1);
        gc.fillText("Particulas Desviadas: "+ particulasDesviadas, x,  y + interlineado * 2);
        gc.fillText("Particulas Rebotadas: "+ particulasRebotadas, x,  y + interlineado * 3);


        gc.fillText("Leyendas", x,  y + interlineado * 4);
        
        gc.setFill(Color.BLACK);
        gc.fillText("Particula α", x,  y + interlineado * 5);
        gc.setFill(Color.BLUE);
        gc.fillOval(x + 55, y + interlineado * 5 - 8, 10, 10);

        gc.setFill(Color.BLACK);
        gc.fillText("Nucleo", x,  y + interlineado * 6);
        gc.setFill(Color.RED);
        gc.fillOval(x + 50, y + interlineado * 6 - 8, 7.5, 7.5);
        gc.setStroke(Color.ORANGE);
        gc.fillOval(x + 50, y + interlineado * 6 - 8, 7.5, 7.5);

        gc.setFill(Color.BLACK);
        gc.fillText("Detector", x,  y + interlineado * 7);
        gc.setFill(Color.GRAY);
        gc.fillOval(x + 50, y + interlineado * 7 - 8, 7.5, 7.5);
        



    }
}
