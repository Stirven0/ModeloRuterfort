package Modelo_Atomico;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Atomo extends Group {

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

        nucleo = new Nucleo_Atomico(this);

        setupOrbitas();
        parallel.play();

        getChildren().addAll(nucleo);
        getChildren().addAll(orbitas);
        getChildren().addAll(electrones);
        // Platform.runLater(() -> {
        //     System.out.println("Electrones: " + electrones.size());
        //     for (Electron electron : electrones) {
        //         System.out.println("Electron[" + electrones.indexOf(electron) + "]" + electron.getOrbita().getRadius());
        //     }
        // });
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
            Electron electron = new Electron(this);
            electron.setOrbita(orbita);
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
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(3 + timeAnimaion), keyValue2);
        Timeline time = new Timeline(keyFrame1, keyFrame2);
        time.setCycleCount(Animation.INDEFINITE);
        parallel.getChildren().add(time);

    }
}
