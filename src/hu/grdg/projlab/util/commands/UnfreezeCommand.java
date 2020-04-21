package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.Proto;
import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.Item;
import hu.grdg.projlab.model.Player;
import hu.grdg.projlab.model.Tile;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

import static hu.grdg.projlab.ProtoIO.OutputMessages.*;

public class UnfreezeCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        String pName = getStringArg(inputParams);

        Player p;
        if((p = getPlayer(pName, state)) == null) {
            ProtoIO.output(ERR_PLAYER_NOT_FOUND);
            return;
        }

        Tile tile = p.getCurrentTile();
        if(tile == null) {
            ProtoIO.output(ERR_NOT_ON_TILE);
            return;
        }

        //FIXME BAD OUTPUT IN DOCS
        Item fItem;
        if((fItem = tile.getFrozenItem()) == null) {
            ProtoIO.output(ERR_SNOW);
            return;
        }

        if(fItem.unfreeze()) {
            ProtoIO.output(UNFREEZE_OUT);
        }else {
            //FIXME Not in docs
            ProtoIO.output(UNFREEZE_ERR_UNFRREZED);
        }

    }

    @Override
    public int getParamCount() {
        return 1;
    }
}
