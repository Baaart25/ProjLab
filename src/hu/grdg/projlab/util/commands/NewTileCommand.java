package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.Proto;
import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.HoleTile;
import hu.grdg.projlab.model.IceTile;
import hu.grdg.projlab.model.UnstableIceTile;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class NewTileCommand extends Command {

    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        String tileName = getStringArg(inputParams);
        String type = getStringArgOf(inputParams, setOf("h","u","i"));

        if(state.getTile(tileName) != null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_NAME_USED);
            return;
        }

        switch (type) {
            case "u": {
                int limit = getIntArg(inputParams);
                state.addTile(tileName, new UnstableIceTile(limit));
            }
                break;
            case "h":
                state.addTile(tileName, new HoleTile());
                break;
            case "i":
                state.addTile(tileName, new IceTile());

        }
        ProtoIO.output(ProtoIO.OutputMessages.NEWTILE_OUT);
    }




    /**
     * This command requires 2 arguments and an optional
     * A name, a type and a number
     */
    @Override
    public int getParamCount() {
        return 2;
    }
}
