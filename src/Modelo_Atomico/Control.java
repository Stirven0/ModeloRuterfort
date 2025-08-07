package Modelo_Atomico;

import Util.ControlAbstract;
import Util.SimulacionAbstract;
import javafx.scene.paint.Color;

public class Control extends ControlAbstract {

    public Control(String title, Color titleColor, Color borderColor, Color backgroundColor) {
        super(title, titleColor, borderColor, backgroundColor);
    }

    @Override
    public void setContext(SimulacionAbstract sp) {
    }

    @Override
    public void setup() {
    }

}
