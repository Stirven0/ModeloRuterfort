package experimento;

import java.util.ArrayList;
import java.util.List;

import Util.SimulacionAbstract;

public class Simulation extends SimulacionAbstract {

    public Control control;

    // Elementos dela simulacion
    public Emisor_Alfa emisorA;
    public Oro oro;
    public List<Detector> detectores;
    public List<P_Alfa> p_Alfas;

    public Simulation(Control control) {
        super(control);
        this.control = control;
        this.control.setContext(this);

        emisorA = new Emisor_Alfa(50, 100, 30, 150, this);
        oro = new Oro(450, 100, 30, 150);
        detectores = new ArrayList<>();
        p_Alfas = new ArrayList<>();
        // p_Alfas.add(new P_Alfa(emisorA.getAreaEmiter(), this));

        setupDetectores();
    }

    @Override
    public void update(long now) {
        creandoPAlfas();
        for (P_Alfa particula : p_Alfas) {

            particula.desplase(now);
            // System.out.println(particula.toString());

            if (particula.isExitScreem(gc)) {
                p_Alfas.remove(particula);
                break;
            }
            for (Detector detector : detectores) {
                if (particula.isColicionWithDetector(detector)) {
                    p_Alfas.remove(particula);
                    break;
                }
            }
        }
    }

    @Override
    public void draw() {
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

    public void setupDetectores() {
        if (detectores != null) {
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

    }

    private void creandoPAlfas() {
        if (oro != null && emisorA != null) {
            while (control.intencidaSlider.getValue() > p_Alfas.size()) {
                p_Alfas.add(emisorA.emiter());
            }
        }
    }

}
