package Modelo_Atomico;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Electron extends Circle {

    DoubleProperty angulo;
    Nucleo_Atomico nucleo;

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
        double x = nucleo.getCenterX() + nucleo.orbitRadio * Math.cos(anguloRadianes);
        double y = nucleo.getCenterY() + nucleo.orbitRadio * Math.sin(anguloRadianes);

        setCenterX(x);
        setCenterY(y);
    }

    public Electron(Nucleo_Atomico nucleo) {
        angulo = new SimpleDoubleProperty();
        this.nucleo = nucleo;
        angulo.addListener((e) -> {
            update(((DoubleProperty) e));
        });
        setRadius(50);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);

    }
}
