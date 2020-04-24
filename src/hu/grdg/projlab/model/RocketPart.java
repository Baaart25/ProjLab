package hu.grdg.projlab.model;

public class RocketPart extends Item{
    private Controller controller;

    public RocketPart(Controller c){
        controller = c;
        c.addRocketPart(this);
    }

    public boolean useItem(){
        return controller.checkWin();
    }
}
