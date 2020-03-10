package hu.grdg.projlab.model;

/**
 * Direction used for navigating in game
 */
public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    /**
     * Returns the natural opposite direction for the current direction
     * @return The opposite direction
     */
    public Direction opposite() {
        switch (this) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
        }
        return NORTH;
    }
}
