package hu.grdg.projlab.model;

public class RocketPart extends Item{
    private Controller controller;


    public boolean useItem(){
        return controller.checkWin();
    }
}
