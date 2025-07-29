package experimento;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class P_Alfa {
    Point2D posiscion;
    // double velocidad = 0;
    DoubleProperty velocidad = new SimpleDoubleProperty();
    long lastTime=0;

    private int radio = 10;

    public P_Alfa(int x, int y){
        this.posiscion = new Point2D(x, y);
    }

    public void draw(GraphicsContext gc){
        gc.setFill(Color.BLUE);
        gc.fillOval(posiscion.getX(), posiscion.getY(), radio, radio);
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

}
