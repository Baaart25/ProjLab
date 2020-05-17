package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.EntityRenderer;
import hu.grdg.projlab.gui.render.ScientistRenderer;

import javax.swing.*;

public class Scientist extends Player{

    private EntityRenderer renderer = new ScientistRenderer(this);

    /**
     * Sets the maximum temperature in player's constructor
     * @author Geri
     */
    public Scientist(Controller controller){
        super(4,controller);
    }

    /**
     * Scientist scan a tile at a direction
     * @return if the scanning was successful
     * @author Dorina
     */
    @Override
    public boolean specialAbility() {
        if(isInWater)
            return false;
        String[] options = new String[]{"North","East","South","West"};
        int res = JOptionPane.showOptionDialog(null, "Choose direction", "Input", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        Tile t = currentTile.getNeighbour(res);
        if(t != null) {
            int limit = t.scanLimit();
            //ProtoIO.outputf(ProtoIO.OutputMessages.SPECAB_OUT_SCI, limit);
            String limits = limit == -1 ? "Unlimited" : String.valueOf(limit);
            JOptionPane.showMessageDialog(null, String.format("The limit of the tile is: %s",limits));
            return true;
        }
        return false;

    }

    /**
     * Returns the renderer of the Scientist
     * @return renderer attribute
     */
    @Override
    public EntityRenderer getRenderer() {
        return renderer;
    }
}
