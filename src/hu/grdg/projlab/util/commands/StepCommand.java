package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.Entity;
import hu.grdg.projlab.model.Tile;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class StepCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        String eName = getStringArg(inputParams);
        int direction = getIntArg(inputParams);

        Entity entity = state.getEntity(eName);
        if(entity == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_PLAYER_NOT_FOUND);
            return;
        }

        Tile current = entity.getCurrentTile();
        if(current == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_NOT_ON_TILE);
            return;
        }

        Tile neighbour = current.getNeighbour(direction);
        if(neighbour == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_NEIGHBOUR_NOT_FOUND);
            return;
        }

        entity.move(direction);
    }

    @Override
    public int getParamCount() {
        return 2;
    }
}
