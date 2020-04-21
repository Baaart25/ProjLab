package hu.grdg.projlab.model;

public class IceTile extends Tile{

    /**
     * Scans the limit of the Tile
     * @return -1 (the IceTile is unlimited)
     * @author Dorina
     */
    @Override
    public int scanLimit() {
        return -1;
    }
}
