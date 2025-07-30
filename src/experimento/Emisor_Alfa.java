package experimento;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Emisor_Alfa {
    private int x;
    private int y;
    private int ancho;
    private int alto;

    public Emisor_Alfa(int x, int y, int ancho, int alto){
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
    }

    public void draw(GraphicsContext gc){
        gc.setFont(new Font("Arial", 10));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFill(Color.BLACK);
        gc.fillText("Fuente", x + (ancho/2), y-3);

        // gc.rect(x, y, ancho, alto);
        gc.setFill(Color.YELLOW);
        gc.fillRect(x, y, ancho, alto);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(x, y, ancho, alto);
        // gc.fill();
        // gc.stroke();
    }

    public int getAlto(){
        return this.alto;
    }
    public int getAncho(){
        return this.ancho;
    }
    public int getY(){
        return this.y;
    }
    public int getX(){
        return this.x;
    }
}
