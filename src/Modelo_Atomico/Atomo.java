package Modelo_Atomico;

import java.util.ArrayList;
import java.util.List;

import Util.ControlAbstract;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Atomo extends Pane {

    Nucleo_Atomico nucleo;
    List<Circle> orbitas;
    List<Electron> electrones;

    int nivelesOrbit;

    public Atomo(ControlAbstract control) {

        orbitas = new ArrayList<>();
        electrones = new ArrayList<>();
        nivelesOrbit = 0;

        nucleo = new Nucleo_Atomico(10);
        nucleo.setCenterX(300);
        nucleo.setCenterY(150);

        Electron electron = new Electron(nucleo);
        electron.setRadius(15);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(electron.anguloProperty(), 0)),
                new KeyFrame(Duration.seconds(4),
                        new KeyValue(electron.anguloProperty(), 360)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        setupOrbitas();
        setupElectrones();
        setupAnimation();

        getChildren().addAll(nucleo);
        getChildren().addAll(orbitas);
        getChildren().addAll(electrones);
    }

    private void setupAnimation() {
        Platform.runLater(() -> {
            ParallelTransition parallel = new ParallelTransition();
            for (Electron electron : electrones) {
                for (Circle orbita : orbitas) {
                    int index = orbitas.indexOf(orbita);
                    int maxE = 2 * (index * index) + 1;

                    for (int j = 0; j <= maxE; j++) {

                        PathTransition transition = new PathTransition(Duration.seconds(2),
                                orbita,
                                electron);
                        transition.setCycleCount(Animation.INDEFINITE);
                        transition.setAutoReverse(false);
                        transition.setInterpolator(Interpolator.LINEAR);
                        // transition.play();
                        parallel.getChildren().add(transition);
                    }

                }
            }
            parallel.play();
        });

    }

    private void setupElectrones() {
        for (int i = 1; i <= nivelesOrbit; i++) {
            int maxE = 2 * (i * i);
            for (int j = 1; j <= maxE; j++) {
                Electron electron = new Electron(nucleo);
                double algulo = 2 * Math.PI * j / maxE;
                electron.setAngulo(algulo);
                electrones.add(electron);
            }
        }
    }

    private void setupOrbitas() {
        double radio = 100;
        for (nivelesOrbit = 0; nivelesOrbit < 3; nivelesOrbit++) {
            Circle orbita = new Circle();
            orbita.setRadius(radio);
            orbita.setCenterX(nucleo.getCenterX());
            orbita.setCenterY(nucleo.getCenterY());
            orbita.setFill(Color.TRANSPARENT);
            orbita.setStroke(Color.GRAY);
            orbitas.add(orbita);
            radio += 50;
        }
    }

}
