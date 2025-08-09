package Util;


import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class ParticleAbstract extends Group{

    private Label label;
    private Label simbol;
    private Circle particle;
    
    public ParticleAbstract(){
        label = new Label("Particula");
        simbol = new Label("Simbolo");
        simbol.setTextFill(Color.WHITE);
        particle = new Circle(10);
        particle.setFill(Color.BLACK);
        particle.setStroke(Color.BLACK);

        getChildren().addAll(particle,label, simbol);

        Platform.runLater(() -> {
            double ySimbol = particle.getCenterY() - (simbol.getHeight() / 2);
            double xSimbol = particle.getCenterX() - (simbol.getWidth() / 2);
            simbol.setLayoutY(ySimbol);
            simbol.setLayoutX(xSimbol);

            double yLabel = particle.getCenterY() + particle.getRadius();
            double xLabel = particle.getCenterX() - (label.getWidth() / 2);
            label.setLayoutX(xLabel);
            label.setLayoutY(yLabel + 5);
        });
    }
    
    public void setLabel(String label) {
        this.label.setText(label);
    }

    public void setSimbol(String simbol){
        this.simbol.setText(simbol);
    }

    public Circle getParticle() {
        return this.particle;
    }
    
    public void showLabel(boolean show) {
        if (show) {
            label.setVisible(show);
        }else{
            label.setVisible(show);
        }
    }
    public void showSimbol(boolean show) {
        if (show) {
            simbol.setVisible(show);
        } else {
            simbol.setVisible(show);
        }
    }

}
