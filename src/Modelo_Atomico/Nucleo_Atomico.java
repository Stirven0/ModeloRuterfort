package Modelo_Atomico;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Nucleo_Atomico extends Circle {

    Atomo atomo;

    public Nucleo_Atomico(Atomo atomo ){
        this.atomo = atomo;
        super(12);
        setFill(Color.RED);
        setStroke(Color.BLACK);
    }
}
