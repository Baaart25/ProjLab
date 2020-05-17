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
        public static final String STEP_OUT_TILE = "Stepped to tile";
        public static final String STEP_OUT_HOLE = "Fall in hole";
        public static final String SNOWSTORM_OUT_PLAYERDIE = "Player died. Game ended";
        public static final String SNOWSTORM_OUT_PLAYERDAMAGE = "Player damaged";
        public static final String SPECAB_OUT_ESKIMO = "Igloo built";
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
}
