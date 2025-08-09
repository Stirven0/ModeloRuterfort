package Modelo_Atomico;

import Util.ControlAbstract;
import javafx.scene.layout.Pane;

public class Simulacion extends Pane {

    Atomo atomo;

    public Simulacion(ControlAbstract control) {
        atomo = new Atomo(2);
        atomo.setLayoutX(400);
        atomo.setLayoutY(200);

        getChildren().add(atomo);
    }

}
