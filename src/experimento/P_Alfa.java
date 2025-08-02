package experimento;

import Util.SimulacionAbstract;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class P_Alfa {
    Point2D posiscion;

    Simulation simulation;

    DoubleProperty velocidad;

    // double velocidad;
    long lastTime;

    private int radio = 10;

    public P_Alfa(int x, int y, SimulacionAbstract context){
        this.posiscion = new Point2D(x, y);
        setup(context);
    }
    public P_Alfa(Point2D position, SimulacionAbstract context){
        this.posiscion = position;
        setup(context);
    }

    private void setup(SimulacionAbstract context){
        this.lastTime = System.nanoTime();
        this.simulation = (Simulation) context;
        // this.velocidad = 50;
        this.velocidad = new SimpleDoubleProperty(50);
        this.velocidad.bind(simulation.control.velocidaSlider.valueProperty());
    }

    public void draw(GraphicsContext gc){
        gc.setFill(Color.BLUE);
        gc.fillOval(posiscion.getX() - (radio / 2), posiscion.getY() - (radio / 2), radio, radio);
    }

    public void desplase(long now){
        double delta = (now - lastTime)/1_000_000_000.0;
        double dx = getVelocidad() * delta;
        posiscion = new Point2D(posiscion.getX() + dx, posiscion.getY());
        lastTime = now;
    };

    public boolean isExitScreem(GraphicsContext gc){

        Canvas canvas = gc.getCanvas();
        if(posiscion.getX() >= canvas.getWidth()){
            return true;
        }
        if (posiscion.getY() > canvas.getHeight()) {
            return true;
        }
        return false;
    }

    public boolean isColicionWithDetector(Detector detector){
        if (posiscion.distance(detector.getPosition()) < detector.getRadio()) {
            detector.activate = true;
            return true;
        } else {
            return false;
        }
    }
    
    public DoubleProperty velocidadPoperty(){
        return velocidad;
    }
    public double getVelocidad(){
        return velocidad.get();
    }
    public void setVelocidad(double v){
        velocidad.set(v);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("Particula Alfa en: x, y [%f, %f]", posiscion.getX(), posiscion.getY());
    }
}
