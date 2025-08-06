package experimento;

import Util.SimulacionAbstract;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Detector {

    private SimulacionAbstract simulation;
    private Point2D position;
    private int diametro;

    public boolean activate = false;

    public Detector(int x, int y, int diametro, SimulacionAbstract contex){
        this.position =  new Point2D(x, y);
        this.diametro = diametro;
        this.simulation = contex;
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

    public void onColicionWithParticle(P_Alfa particula) {
        
        if (position.distance(particula.posiscion) < getRadio() + particula.radio - 5) {
            Simulation simu = ((Simulation) simulation);
            this.activate = true;
            particula.destroid = true;
            int index = simu.detectores.indexOf(this);
            switch (index) {
                case 4:
                    simu.particulasRebotadas++;
                    break;
                case 5:
                    simu.particulasRebotadas++;
                    break;
                case 7:
                    simu.particulasRebotadas++;
                    break;
                case 8:
                    simu.particulasRebotadas++;
                    break;
            }
        }
    }
}
