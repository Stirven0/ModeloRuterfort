package Experimento1;

import Util.SimulacionAbstract;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class P_Alfa {
    Point2D posiscion;
    Point2D direccion;

    SimulacionAbstract simulation;

    DoubleProperty velocidad;

    public boolean destroid;
    public boolean desviate;

    public int radio = 10;
    public long lastTime;

    public P_Alfa(int x, int y, SimulacionAbstract context) {
        this.posiscion = new Point2D(x, y);
        setup(context);
    }

    public P_Alfa(Point2D position, SimulacionAbstract context) {
        this.posiscion = position;
        setup(context);
    }

    private void setup(SimulacionAbstract context) {
        this.simulation = context;
        // this.velocidad = 50;
        this.velocidad = new SimpleDoubleProperty(50);
        this.velocidad.bind(((Simulation) simulation).control.velocidaSlider.valueProperty());
        this.lastTime = System.nanoTime();
        this.direccion = new Point2D(1, 0);
        this.destroid = false;
        this.desviate = false;
    }

    public void draw() {
        if (destroid != true) {
            simulation.gc.setFill(Color.BLUE);
            simulation.gc.fillOval(posiscion.getX() - (radio / 2), posiscion.getY() - (radio / 2), radio, radio);
        }
    }

    public void desplase(long now) {
        if (destroid != true) {
            double delta = (now - lastTime) / 1_000_000_000.0;
            double dx = direccion.getX() * getVelocidad() * delta;
            double dy = direccion.getY() * getVelocidad() * delta;
            // posiscion = new Point2D(posiscion.getX() + dx, posiscion.getY());
            this.posiscion = posiscion.add(dx, dy);
            lastTime = now;
        }
    };

    public void onExitScreem() {
        if (posiscion.getX() >= simulation.canvas.getWidth() ||
                posiscion.getY() > simulation.canvas.getHeight()) {
            this.destroid = true;
        }
    }

    public void onColicionWithDetector(Detector detector) {
        if (posiscion.distance(detector.getPosition()) < detector.getRadio() + radio - 5) {
            Simulation simu = ((Simulation) simulation);
            detector.activate = true;
            this.destroid = true;
            int index = simu.detectores.indexOf(this);
            switch (index) {
                case 4:
                    simu.particulasRebotadas++;
                    break;
                case 5:
                    simu.particulasRebotadas++;
                    break;
                case 7:
                    simu.particulasRebotadas++;
                    break;
                case 8:
                    simu.particulasRebotadas++;
                    break;
            }
        }
    }

    // public void onInteractueWithNucleo(Oro oro){
    // double distance = posiscion.distance(oro.getNucleo());
    // if (distance < oro.getRadioNucleo() + radio) {
    // Point2D normal = posiscion.subtract(oro.getNucleo()).normalize();
    // // this.direccion = normal;
    // this.direccion = direccion.subtract(normal.multiply(distance));
    // this.desviate = true;
    // }
    // }

    // public void onInteractueWithNucleo(Oro oro) {
    //     double distance = posiscion.distance(oro.getNucleo());
    //     double maxInfluence = oro.getRadioNucleo() + radio;

    //     if (distance < maxInfluence && desviate == false ) {
    //         // if (desviate == false) {
    //             Simulation simule = ((Simulation) simulation);
    //             simule.particulasDesviadas++;
    //         // }

    //         Point2D normal = posiscion.subtract(oro.getNucleo()).normalize();

    //         double f = 1.0 - (distance / maxInfluence); // más cerca → mayor f
    //         // f = Math.max(0, Math.min(f, 1)); // clamp
    //         f = Math.pow(f, 0.25);

    //         this.direccion = this.direccion.multiply(1 - f).add(normal.multiply(f)).normalize();
    //         this.desviate = true;
    //     }
    // }

    public DoubleProperty velocidadPoperty() {
        return velocidad;
    }

    public double getVelocidad() {
        return velocidad.get();
    }

    public void setVelocidad(double v) {
        velocidad.set(v);
    }

    @Override
    public String toString() {
        return String.format("Particula Alfa en: x, y [%f, %f]", posiscion.getX(), posiscion.getY());
    }
}
