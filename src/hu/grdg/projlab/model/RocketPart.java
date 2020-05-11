package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;
import hu.grdg.projlab.gui.render.RocketPartRenderer;

public class RocketPart extends Item{
    private static RocketPartRenderer renderer = new RocketPartRenderer();

    private Controller controller;

    public RocketPart(Controller c){
        controller = c;
        c.addRocketPart(this);
    }

    public boolean useItem(){
        return controller.checkWin();
    }

    @Override
    public ItemRenderer getRenderer() {
        return renderer;
    }
}
