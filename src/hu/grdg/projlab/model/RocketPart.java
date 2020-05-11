package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;
import hu.grdg.projlab.gui.render.RocketPartRenderer;

public class RocketPart extends Item{
    private int partType;
    private RocketPartRenderer renderer;

    private Controller controller;

    public RocketPart(Controller c, int _partType){
        partType = _partType;
        renderer = new RocketPartRenderer(partType);
        controller = c;
        //FIXME
        //c.addRocketPart(this);
    }

    public boolean useItem(){
        return controller.checkWin();
    }

    @Override
    public ItemRenderer getRenderer() {
        return renderer;
    }
}
