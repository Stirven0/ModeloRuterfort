package Modelo_Atomico;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Nucleo_Atomico extends Circle {

    public Circle orbita;
    public double orbitRadio;

    public Nucleo_Atomico( double radio){
        super(radio);
        orbitRadio = 150;
        setFill(Color.RED);
        setStroke(Color.BLACK);
        setupOrbita();
    }

    private void setupOrbita(){
        orbita = new Circle(orbitRadio);
        orbita.centerXProperty().bind(this.centerXProperty());
        orbita.centerYProperty().bind(this.centerYProperty());
        orbita.setFill(Color.TRANSPARENT);
        orbita.setStroke(Color.GRAY);
    }


}
