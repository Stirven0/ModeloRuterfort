package View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;

public class InformationPane extends AnchorPane {

    private Label titleLabel = new Label("Title");
    private Label descriptionLabel = new Label("Description");


    public InformationPane(String title, String description, Color titleColor, Color descriptionColor,
            Color backgroundColor, Color borderColor) {

        setBackground(Background.fill(backgroundColor));

        this.titleLabel.setText(title);
        this.titleLabel.setTextFill(titleColor);
        this.titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: Arial; -fx-padding: 0 5 0 5;");
        this.titleLabel.setBackground(getBackground());
        this.titleLabel.setLayoutX(10);
        this.titleLabel.setLayoutY(0);


        this.descriptionLabel.setText(description);
        this.descriptionLabel.setTextFill(descriptionColor);
        this.descriptionLabel.setStyle("-fx-font-size: 12px; -fx-font-family: Arial;");
        this.descriptionLabel.setWrapText(true);
        this.descriptionLabel.setLayoutY(20);

        AnchorPane.setLeftAnchor(descriptionLabel, 0.0);
        AnchorPane.setRightAnchor(descriptionLabel, 0.0);

        setBorder(new Border(new BorderStroke(
            borderColor, 
            BorderStrokeStyle.SOLID, 
            null, 
            new BorderWidths(2), 
            new Insets(10, 0, 0, 0)
            )));

        getChildren().addAll(titleLabel,descriptionLabel);
    }

    public String getTitle() {
        return titleLabel.getText();
    }

    public void setTitle(String title) {
        this.titleLabel.setText(title);
    }

    public String getDescription() {
        return descriptionLabel.getText();
    }

    public void setDescription(String description) {
        this.descriptionLabel.setText(description);
    }

    public void setTitleColor(Color titleColor) {
        this.titleLabel.setTextFill(titleColor);
    }

    public void setDescriptionColor(Color descriptionColor) {
        this.descriptionLabel.setTextFill(descriptionColor);
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.setBackground(Background.fill(backgroundColor));
    }

}
