package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.ItemRenderer;

public class RocketPart extends Item{
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
        return null;
    }
}
