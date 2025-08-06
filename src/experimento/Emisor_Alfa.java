package experimento;

import java.util.Random;

import Util.SimulacionAbstract;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Emisor_Alfa {
    private double x;
    private double y;
    private Point2D posicion;
    private int ancho;
    private int alto;
    public boolean alinementCenter;

    private SimulacionAbstract simulation;

    public Long lastTime;
    private DoubleProperty intecidad;

    public Emisor_Alfa(int x, int y, int ancho, int alto, SimulacionAbstract context) {
        this.posicion = new Point2D(x, y);
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
        this.simulation = context;
        this.intecidad = new SimpleDoubleProperty();
        this.intecidad.bind(((Simulation) simulation).control.intencidaSlider.valueProperty());
        this.lastTime = System.nanoTime();
        this.alinementCenter = false;
    }

    public void emiter(long now) {

        Simulation simule = (Simulation) simulation;
        // if (simule.p_Alfas.size() < 1) {
            double delta = (now - lastTime) / 1_000_000_000.0;
            double dx = getIntecidad() * delta;
    
            if (dx >= 1) {
                lastTime = now;
                simule.p_Alfas.add(new P_Alfa(getAreaEmiter(), simulation));
                simule.particulasLansadas++;
            }
        // }
    }

    public void draw() {
        simulation.gc.setFont(new Font("Arial", 10));
        simulation.gc.setTextAlign(TextAlignment.CENTER);
        simulation.gc.setFill(Color.BLACK);
        simulation.gc.fillText("Fuente", x + (ancho / 2), y - 3);
        simulation.gc.setFill(Color.YELLOW);
        simulation.gc.fillRect(x, y, ancho, alto);
        simulation.gc.setStroke(Color.BLACK);
        simulation.gc.setLineWidth(2);
        simulation.gc.strokeRect(x, y, ancho, alto);

    }

    public Point2D getAreaEmiter() {
        Random random = new Random();
        double xPoint = random.nextDouble(x, x + ancho);
        double yPoint = random.nextDouble(y, y + alto);
        return new Point2D(xPoint, yPoint);
    }

    public void alinemetCenter(boolean  b){
        if (b) {
            x = posicion.getX() - (ancho / 2);
            y = posicion.getY() - (alto / 2);
        }
        
    }

    public DoubleProperty intecidadPoperty() {
        return intecidad;
    }

    public double getIntecidad() {
        return intecidad.get();
    }

    public void setIntecidad(double v) {
        intecidad.set(v);
    }
}
