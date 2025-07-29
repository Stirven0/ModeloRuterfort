package experimento;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Detector {

    private Point2D position;
    private int diametro;

    public boolean activate = false;

    public Detector(int x, int y, int diametro){
        this.position =  new Point2D(x, y);
        this.diametro = diametro;
    }

    public void draw(GraphicsContext gc){
        
        if (activate) {
            gc.setFill(Color.YELLOW);
        } else {
            gc.setFill(Color.GRAY);
        }
        gc.fillOval(position.getX() - (diametro / 2), position.getY() - (diametro / 2), diametro, diametro);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeOval(position.getX()- (diametro / 2), position.getY() - (diametro / 2), diametro, diametro);
    }

    public double getRadio(){
        return diametro/2;
    }
    public Point2D getPosition(){
        return this.position;
    }
}
