package Modelo_Atomico;

import java.util.ArrayList;
import java.util.List;

import Util.ParticleAbstract;
import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Atomo extends ParticleAbstract {

    public Nucleo_Atomico nucleo;
    ParallelTransition parallel;
    public List<Electron> electrones;
    public List<Circle> orbitas;

    public int maxOrbi;

    public Atomo(int maxOrbi) {


        orbitas = new ArrayList<>();
        electrones = new ArrayList<>();
        this.maxOrbi = maxOrbi;
        parallel = new ParallelTransition();

        nucleo = new Nucleo_Atomico();
        nucleo.showLabel(false);

        setupOrbitas();
        parallel.play();

        getChildren().addAll(nucleo);
        getChildren().addAll(orbitas);
        getChildren().addAll(electrones);
        
        setSimbol("");
        setLabel("Atomo");
        getParticle().setFill(Color.TRANSPARENT);
        getParticle().setStroke(Color.TRANSPARENT);
        double minRadio = orbitas.getLast().getRadius();
        getParticle().setRadius(minRadio + 20);

    }

    private void setupOrbitas() {
        int radioIni = 100;
        for (int i = 0; i < maxOrbi; i++) {
            Circle orbita = new Circle(radioIni);
            orbita.setCenterX(nucleo.getCenterX());
            orbita.setCenterY(nucleo.getCenterY());
            orbita.setFill(Color.TRANSPARENT);
            orbita.setStroke(Color.GRAY);
            orbitas.add(orbita);
            setupElectrones(orbita);
            radioIni += 50;
        }
    }

    private void setupElectrones(Circle orbita) {

        int i = orbitas.indexOf(orbita) + 1;
        int maxE = 2 * (i * i);
        double angElectr = 360 / maxE;

        for (int k = 1; k <= maxE; k++) {
            Electron electron = new Electron();
            electron.setOrbita(orbita);
            electron.setNucleo(nucleo);
            electron.showLabel(false);
            electron.setAngulo(angElectr * k);
            setupAnimation(electron);
            electrones.add(electron);
        }
    }

    private void setupAnimation(Electron electron) {
        double timeAnimaion = orbitas.indexOf(electron.getOrbita());
        KeyValue keyValue1 = new KeyValue(electron.angulo, 0 + electron.getAngulo());
        KeyValue keyValue2 = new KeyValue(electron.angulo, 360 + electron.getAngulo());
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(5 + timeAnimaion), keyValue2);
        Timeline time = new Timeline(keyFrame1, keyFrame2);
        time.setCycleCount(Animation.INDEFINITE);
        parallel.getChildren().add(time);

    }
}
