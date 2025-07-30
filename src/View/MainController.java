package View;
import java.net.URL;
import java.util.ResourceBundle;

import experimento.Control;
import experimento.Simulation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class MainController implements Initializable{

    @FXML
    private BorderPane campoTap;

    @FXML
    private BorderPane experimentoTap;

    @FXML
    private BorderPane fallosTap;

    @FXML
    private BorderPane modeloTap;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Initialize the main controller
        campoInitialize();
        experimentoInitialize();
        fallosInitialize();
        modeloInitialize();
    }

    public void campoInitialize() {
        // Initialize campoTap
    }
    public void experimentoInitialize() {
        InformationPane infoPane = new InformationPane(
            "Modelo Atómico de Rutherford",
            "El modelo atómico de Rutherford describe el átomo como un núcleo central denso y cargado positivamente, rodeado por electrones en órbita. Este modelo fue propuesto tras el experimento de dispersión de partículas alfa.",
            Color.BLUE,
            Color.BLACK,
            Color.LIGHTSTEELBLUE,
            Color.BLUE
        );
        Control controlPanel = new Control("Panel de Control", Color.BLACK, null, Color.LIGHTSKYBLUE);
        Simulation simulationPane = new Simulation(controlPanel);
        
        
        
        experimentoTap.setTop(infoPane);
        experimentoTap.setCenter(simulationPane);
        experimentoTap.setBottom(controlPanel);
    }
    public void fallosInitialize() {
        // Initialize fallosTap
    }
    public void modeloInitialize() {
        // Initialize modeloTap
    }

}

