package Experimento1;


import Util.ControlAbstract;
import Util.SimulacionAbstract;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Control extends ControlAbstract {

    private Simulation simulacion;
    
    private Button playButton, pauseButton, rebootButton;
    public Slider velocidaSlider, intencidaSlider;

    private double defaultValue;
    
    public double velocidad;
    public double intencidad;

    public Control(String title, Color titleColor, Color borderColor, Color backgroundColor) {
        super(title, titleColor, borderColor, backgroundColor);

    }
    

    @Override
    public void setup() {
        this.defaultValue = 50.0;
        // this.velocidad = defaultValue;
        // this.intencidad = defaultValue;

        Label controlLabel = new Label("Control");
        playButton = new Button("Play");
        playButton.setPrefWidth(60);
        pauseButton = new Button("Pause");
        pauseButton.setPrefWidth(60);
        rebootButton = new Button("Reboot");
        rebootButton.setPrefWidth(60);

        HBox row1 = new HBox(controlLabel,playButton,pauseButton,rebootButton);
        row1.setAlignment(Pos.CENTER);
        HBox.setMargin(controlLabel, new Insets(0, 10, 0, 0));
        HBox.setMargin(playButton, new Insets(0, 10, 0, 0));
        HBox.setMargin(pauseButton, new Insets(0, 10, 0, 0));
        // HBox.setMargin(rebootButton, new Insets(0, 0, 0, 0));
        VBox.setMargin(row1, new Insets(10, 0, 0, 0));

        Label velicidadLabel = new Label("Velocidad");
        velocidaSlider = new Slider(0.0, 100.0, defaultValue);
        velocidaSlider.setMajorTickUnit(20);
        velocidaSlider.setMinorTickCount(10);
        velocidaSlider.setShowTickLabels(true);
        velocidaSlider.setShowTickMarks(true);
        velocidaSlider.setPrefWidth(200);

        HBox row2 = new HBox(velicidadLabel,velocidaSlider);
        HBox.setMargin(velicidadLabel, new Insets(0,10,0,0));
        row2.setAlignment(Pos.CENTER);
        VBox.setMargin(row2, new Insets(10, 0, 0, 0));


        Label intencidadLabel = new Label("Intecidad");
        intencidaSlider = new Slider(0.0, 100.0, 1);
        intencidaSlider.setMajorTickUnit(20);
        intencidaSlider.setMinorTickCount(10);
        intencidaSlider.setShowTickLabels(true);
        intencidaSlider.setShowTickMarks(true);
        // intencidadLabel.getStylesheets().add(getClass().getResource("slider-style.css").toExternalForm());
        intencidaSlider.setPrefWidth(200);

        HBox row3 = new HBox(intencidadLabel, intencidaSlider);
        HBox.setMargin(intencidadLabel, new Insets(0, 10, 0, 0));
        row3.setAlignment(Pos.CENTER);
        VBox.setMargin(row3, new Insets(10, 0, 0, 0));

        VBox vBox = new VBox(row1, row2, row3);
        super.getChildren().add(vBox);
        setTopAnchor(vBox, 0.0);
        setLeftAnchor(vBox, 0.0);
        setRightAnchor(vBox, 0.0);
        setBottomAnchor(vBox, 0.0);
        setMinHeight(150);

        playButton.setOnAction(e -> {
            play = true;
            reboot = false;
            playButton.setDisable(true);
            pauseButton.setDisable(false);
            // simulacion.atimer.start();
            simulacion.emisorA.lastTime = System.nanoTime();
            for (P_Alfa particu : simulacion.p_Alfas) {
                particu.lastTime = System.nanoTime();
            }
            simulacion.atimer.start();
        });
        pauseButton.setOnAction(e -> {
            play = false;
            reboot = false;
            playButton.setDisable(false);
            pauseButton.setDisable(true);
            simulacion.atimer.stop();
            // simulacion.atimer.stop();
            
        });
        rebootButton.setOnAction(e -> {
            play = false;
            reboot = true;
            playButton.setDisable(false);
            pauseButton.setDisable(true);
            simulacion.atimer.stop();
            simulacion.particulasDesviadas = 0;
            simulacion.particulasLansadas = 0;
            simulacion.particulasRebotadas = 0;
            simulacion.detectores.clear();
            simulacion.p_Alfas.clear();
            simulacion.setupDetectores();
            simulacion.draw();
            
        });
        intencidaSlider.setOnMouseDragged(e -> {
            intencidad = intencidaSlider.getValue();
            // System.out.println(intencidad);
        });
        velocidaSlider.setOnMouseDragged(e -> {
            velocidad = velocidaSlider.getValue();
            // System.out.println(velocidad);
        });

    }

    @Override
    public void setContext(SimulacionAbstract sp) {
        this.simulacion = (Simulation) sp;
    }
}
