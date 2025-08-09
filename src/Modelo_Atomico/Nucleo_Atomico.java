package Modelo_Atomico;

import Util.ParticleAbstract;
import javafx.scene.paint.Color;

public class Nucleo_Atomico extends ParticleAbstract {

    public Nucleo_Atomico(){
        setSimbol("+");
        setLabel("Nucleo");
        getParticle().setFill(Color.RED);
        getParticle().setRadius(12);

    }
    public double getCenterX() {
        return getParticle().getCenterX();
    }

    public double getCenterY() {
        return getParticle().getCenterY();
    }

}
