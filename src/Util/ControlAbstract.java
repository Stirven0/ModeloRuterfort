package Util;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;

public abstract class ControlAbstract extends AnchorPane{
    
    private Label titleLabel = new Label("Title");

    // public SimulacionAbstract sp;
    public boolean play = false;
    public boolean reboot = false;

    public ControlAbstract(String title, Color titleColor, Color borderColor, Color backgroundColor) {
        
        setBackground(Background.fill(backgroundColor));
        this.titleLabel.setText(title);
        this.titleLabel.setTextFill(titleColor);
        this.titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: Arial; -fx-padding: 0 5 0 5;");
        this.titleLabel.setBackground(getBackground());
        this.titleLabel.setLayoutX(10);
        this.titleLabel.setLayoutY(0);

        setBorder(new Border(new BorderStroke(
            borderColor, 
            BorderStrokeStyle.SOLID, 
            null, 
            new BorderWidths(2), 
            new Insets(10, 0, 0, 0)
            )));
        
        getChildren().add(titleLabel);
        setup();
    }

    public abstract void setContext(SimulacionAbstract sp);

    public abstract void setup();
}
