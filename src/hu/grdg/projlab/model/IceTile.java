package hu.grdg.projlab.model;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.gui.ItemRenderer;
import hu.grdg.projlab.gui.TileRenderer;
import hu.grdg.projlab.gui.render.TileRendererImpl;

public class IceTile extends Tile{

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

    @Override
    public TileRenderer getRenderer() {
        return renderer;
    }
}
