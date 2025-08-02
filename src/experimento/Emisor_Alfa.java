package experimento;

import java.util.Random;

import Util.SimulacionAbstract;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Emisor_Alfa {
    private int x;
    private int y;
    private int ancho;
    private int alto;

    private Simulation simulation;

    public Emisor_Alfa(int x, int y, int ancho, int alto, SimulacionAbstract context){
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
        this.simulation = (Simulation) context;
    }

    public P_Alfa emiter(){
        return new P_Alfa(getAreaEmiter(), simulation);
    }

    public void draw(GraphicsContext gc){
        gc.setFont(new Font("Arial", 10));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFill(Color.BLACK);
        gc.fillText("Fuente", x + (ancho/2), y-3);

        gc.setFill(Color.YELLOW);
        gc.fillRect(x, y, ancho, alto);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(x, y, ancho, alto);
    }

    public Point2D getAreaEmiter(){
        Random random = new Random();
        int xPoint = random.nextInt(x, x + ancho);
        int yPoint = random.nextInt(y, y + alto);
        return new Point2D(xPoint, yPoint);
    }

}
