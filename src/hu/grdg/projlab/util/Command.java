package hu.grdg.projlab.util;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.Entity;
import hu.grdg.projlab.model.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class Command {
    /**
     * Runs the command with the current game state and the user provided arguments
     * @param state Current game state
     * @param inputParams User args
     */
    public abstract void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException;

    /**
     * Returns the required parameter count for this command. The system ensures
     * that the is executed with the required number of args.
     * @return The number of required params
     */
    public abstract int getParamCount();


    /**
     * Create a set of elements
     * @param args The set elements
     * @param <T> Type of the set elements
     * @return The set
     */
    protected <T> HashSet<T> setOf(T ... args) {
        HashSet<T> set = new HashSet<>(args.length);
        set.addAll(Arrays.asList(args));
        return set;
    }

    /**
     * Returns the next string argument from the list
     * @param inputParams The list of args
     * @return The next string arg
     * @throws CommandException if there are no elements in the list
     */
    protected String getStringArg(List<String> inputParams) throws CommandException {
        if(inputParams.size() > 0) {
            String p =  inputParams.get(0);
            inputParams.remove(0);
            return p;
        }
        throw new CommandException("A string argument is required but was not provided");
    }

    /**
     * Returns the next string argument from the list and ensures that the arg is
     * a valid element of the provided set
     * @param inputParams The list of args
     * @param valueSet The set of valid elements
     * @return The string arg
     */
    protected String getStringArgOf(List<String> inputParams, HashSet<String> valueSet) throws CommandException {
        String val = getStringArg(inputParams);
        if(valueSet.contains(val)) {
            return val;
        }
        throw new CommandException(String.format("The provided arg (%s) is not in [%s]", val, String.join(",", valueSet)));
    }

    /**
     * Gets an int argument from the input
     * @param inputParams The list of args
     * @return The int arg
     * @throws CommandException if the next arg is not an integer
     */
    protected int getIntArg(List<String> inputParams) throws CommandException {
        String val = "";
        try {
            val = getStringArg(inputParams);
            return Integer.parseInt(val);
        }catch (CommandException e) {
            throw new CommandException("An int argument is required but was not provided");
        }catch (NumberFormatException e) {
            throw new CommandException(String.format("Integer was required but %s is not a valid integer", val));
        }
    }


    /**
     * Finds the named player
     * @param pName The name of the player
     * @param state The current game state
     * @return The player or null
     */
    protected Player getPlayer(String pName, ProtoRuntime state) {
        Entity e = state.getEntity(pName);
        if(!(e instanceof Player)) {
            return null;
        }

        //The cast is safe, its checked above
        return (Player) e;
    }
}
