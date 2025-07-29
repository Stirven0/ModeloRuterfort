package experimento;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Oro {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    
    private Point2D nucleo;
    private double diametro;

    public Oro(int x, int y, int ancho, int alto){
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
        this.nucleo = new Point2D(x + (ancho / 2), y + (alto / 2));
        this.diametro = 15;
        
    }

    public void draw(GraphicsContext gc){
        //Show text
        gc.setFont(new Font("Arial", 10));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFill(Color.BLACK);
        gc.fillText("ORO", x + (ancho/2), y-3);
        //show rectangle
        gc.setFill(Color.YELLOWGREEN);
        gc.fillRect(x, y, ancho, alto);
        gc.setLineWidth(2);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(x, y, ancho, alto);
        //show nucleo
        gc.setFill(Color.RED);
        gc.fillOval(x + (ancho / 2) - (diametro / 2), y + (alto / 2) - (diametro / 2), diametro, diametro);
        gc.setStroke(Color.ORANGE);
        gc.setLineWidth(1);
        gc.strokeOval(x + (ancho / 2) - (diametro / 2), y + (alto / 2) - (diametro / 2), diametro, diametro);
        
        // gc.closePath();

    }

    public Point2D getNucleo() {
        return nucleo;
    }
    

}
