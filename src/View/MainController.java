package View;
import java.net.URL;
import java.util.ResourceBundle;

import Experimento1.Control;
import Experimento1.Simulation;
import Modelo_Atomico.Atomo;
import Modelo_Atomico.Simulacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
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

    @FXML
    private TabPane tapManager;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Initialize the main controller
        experimentoInitialize();
        modeloInitialize();
        campoInitialize();
        fallosInitialize();
        tapManager.getSelectionModel().select(1);
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
        Experimento1.Control controlPanel = new Control("Panel de Control", Color.BLACK, null, Color.LIGHTSKYBLUE);
        Experimento1.Simulation simulationPane = new Simulation(controlPanel);
        // controlPanel.setContext(simulationPane);
        
        
        
        experimentoTap.setTop(infoPane);
        experimentoTap.setCenter(simulationPane);
        experimentoTap.setBottom(controlPanel);
    }
    public void fallosInitialize() {
        // Initialize fallosTap
    }
    public void modeloInitialize() {
        
        String description = """
                Basado en su experimento, Rutherford propuso que:
                •   El atomo tiene un nucleo central y denzo con carga positiva.
                •   Los atomos orbitan al rededor del nucleo como planetas al rededor del Sol.
                •   La mayor parte del atomo es espacio vacio.
                •   El nucleo concentra casi toda la masa del atomo.""";
        InformationPane infoPanel = new InformationPane(
            "Modelo Atomico de Rutherford (1911)", 
            description, 
            Color.BLUE, 
            Color.BLACK, 
            Color.LIGHTBLUE, 
            Color.BLUE);
            
        Modelo_Atomico.Control control = new Modelo_Atomico.Control("Panel de Control", Color.BLACK, Color.BLACK, Color.LIGHTSKYBLUE);
        Modelo_Atomico.Simulacion simulacion = new Simulacion(control);

        modeloTap.setCenter(simulacion);
        modeloTap.setTop(infoPanel);
        modeloTap.setBottom(control);
    }

}

