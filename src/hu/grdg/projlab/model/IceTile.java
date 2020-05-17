package hu.grdg.projlab.model;

import hu.grdg.projlab.gui.TileRenderer;
import hu.grdg.projlab.gui.render.TileRendererImpl;

/**
 * Olyan Tile, ami végtelen entity-t bír el
 * Tileból származik
 */
public class IceTile extends Tile{
    //tárolja a hozzátartozó renderert
    private static TileRenderer renderer = new TileRendererImpl(false);


    /**
     * Scans the limit of the Tile
     * @return -1 (the IceTile is unlimited)
     * @author Dorina
     */
    @Override
    public int scanLimit() {
        return -1;
    }

    /**
     * Returns the renderer of the IceTile
     * @return renderer attribute
     */
    @Override
    public TileRenderer getRenderer() {
        return renderer;
    }
}
