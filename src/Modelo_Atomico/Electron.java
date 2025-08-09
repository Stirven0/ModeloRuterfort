package Modelo_Atomico;

import Util.ParticleAbstract;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Electron extends ParticleAbstract {

    DoubleProperty angulo;
    Circle orbita;
    Nucleo_Atomico nucleo;

    public Nucleo_Atomico getNucleo() {
        return nucleo;
    }

    public void setNucleo(Nucleo_Atomico nucleo) {
        this.nucleo = nucleo;
    }

    public Circle getOrbita() {
        return orbita;
    }

    public void setOrbita(Circle orbita) {
        this.orbita = orbita;
    }

    public DoubleProperty anguloProperty() {
        return angulo;
    }

    public double getAngulo() {
        return this.angulo.get();
    }

    public void setAngulo(double value) {
        this.angulo.set(value);
        update(angulo);
    }

    private void update(DoubleProperty e) {

        if (nucleo != null) {
            double anguloRadianes = Math.toRadians(e.get());
            double x = nucleo.getCenterX() + orbita.getRadius() * Math.cos(anguloRadianes);
            double y = nucleo.getCenterY() + orbita.getRadius() * Math.sin(anguloRadianes);
    
            setLayoutX(x);
            setLayoutY(y);
            
        }else{System.out.println("No existe un nucleo");}
    }

    public Electron() {
        angulo = new SimpleDoubleProperty();
        orbita = new Circle(100);

        getParticle().setFill(Color.BLUE);
        getParticle().setRadius(7);
        setSimbol("-");
        setLabel("Electron");

        angulo.addListener((e) -> {
            update(((DoubleProperty) e));
        });

    }
}
