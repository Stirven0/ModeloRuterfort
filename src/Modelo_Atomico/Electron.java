package Modelo_Atomico;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Electron extends Circle {

    DoubleProperty angulo;
    Atomo atomo;

    Circle orbita;

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

        double anguloRadianes = Math.toRadians(e.get());
        // double anguloRadianes = Math.toRadians(180);
        double x = atomo.nucleo.getCenterX() + orbita.getRadius() * Math.cos(anguloRadianes);
        double y = atomo.nucleo.getCenterY() + orbita.getRadius() * Math.sin(anguloRadianes);

        setCenterX(x);
        setCenterY(y);
    }

    public Electron(Atomo atomo) {
        angulo = new SimpleDoubleProperty();
        this.atomo = atomo;
        this.orbita = new Circle(100);
        angulo.addListener((e) -> {
            update(((DoubleProperty) e));
        });
        setRadius(7);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);

    }
}
