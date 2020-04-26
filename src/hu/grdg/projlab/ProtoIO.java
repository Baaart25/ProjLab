package hu.grdg.projlab;

/**
 * Helper class for printing to the standard output
 * Also contains helper functions to read numbers, bools, etc. from the
 * standard input.
 *
 * @author Barrow099
 */
public class ProtoIO {

    private static boolean silent = false;

    /**
     * Static class for all output messages as specified in the 8th document.
     *
     * @author Barrow099
     */
    public static class OutputMessages {
        public static final String CLEARGAME_OUT = "Game cleared";
        public static final String NEWTILE_OUT = "Tile created";
        public static final String ERR_NAME_USED = "Name already used";
        public static final String SETNEIGHBOUR_OUT = "Neighbours updated";
        public static final String ERR_TILE_NOT_FOUND = "Tile not found";
        public static final String SETNEIGHBOUR_ERR_USED = "Neighbour already set";
        public static final String INVENTORY_OUT = "Inventory: [%s]";
        public static final String ERR_PLAYER_NOT_FOUND = "Player not found";
        public static final String INSPECT_OUT = "Tile: [%s]";
        public static final String ERR_NOT_ON_TILE = "Not on tile";
        public static final String REMOVESNOW_OUT = "Snow layer removed";
        public static final String REMOVESNOW_ERR_NOT_ENOUGH = "Not enough snow";
        public static final String ESKIMO_OUT = "Eskimo created";
        public static final String SCIENTIST_OUT = "Scientist created";
        public static final String ITEM_OUT = "Item created";
        public static final String POLARBEAR_OUT = "Polarbear created";
        public static final String PUTSNOW_OUT = "Snow layer added";
        public static final String STEP_OUT_TILE = "Stepped to tile";
        public static final String STEP_OUT_HOLE = "Fall in hole";
        public static final String STEP_OUT_UNSTABLE_DIE = "Died on unstable. Game ended";
        public static final String STEP_BEAR_EAT = "Polarbear eats player";
        public static final String ERR_NEIGHBOUR_NOT_FOUND = "Neighbour not found";
        public static final String LOAD_OUT = "Game state loaded";
        public static final String LOAD_ERR_NOTFOUND = "File not found";
        public static final String LOAD_ERR_FORMAT = "Format error: %s";
        public static final String ERR_IO = "IO error";
        public static final String STAT_OUT = "ERRR -- DONT FUCKIN USE THIS -- ERRR";
        public static final String SNOWSTORM_OUT_NORMAL = "Snowstorm done";
        public static final String SNOWSTORM_OUT_PLAYERDIE = "Player died. Game ended";
        public static final String SNOWSTORM_OUT_PLAYERDAMAGE = "Player damaged";
        public static final String ADD_OUT = "Item added. Slot: %d";
        public static final String USEITEM_OUT = "Item used";
        public static final String USEITEM_ERR_NOITEM = "No such item";
        public static final String USEITEM_ERR_ITEMFAIL = "Item use failed";
        public static final String PICKUP_OUT = "Pickup successful";
        public static final String PICKUP_ERR_FORZEN = "Item frozen";
        public static final String PICKUP_ERR_NOITEM_OR_SNOW = "No item or snow on tile";
        public static final String ERR_SNOW = "Snow on tile";
        public static final String ERR_NOITEM = "No item on tile";
        public static final String SPECAB_OUT_ESKIMO = "Igloo built";
        public static final String SPECAB_OUT_SCI = "Tile scanned: %d";
        public static final String SPECAB_ERR_FAILURE = "SpecialAbility failure";
        public static final String UNFREEZE_OUT = "Item unfreezed";
        public static final String UNFREEZE_ERR_UNFRREZED = "Unfreeze failed";
    }

    /**
     * Mute the output
     */
    public static void mute() {
        silent = true;
    }

    /**
     * Unmute the output
     */
    public static void unmute() {
        silent = false;
    }

    /**
     * Prints the message to the standard output
     *
     * @author Barrow099
     */
    public static void output(String message) {
        if (!silent)
            System.out.println(message);
    }

    /**
     * Prints the formatted message to the standard output
     *
     * @author Barrow099
     */
    public static void outputf(String message, Object... args) {

        if (!silent)
            System.out.printf(message + "\n", args);
    }
}
