package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.Item;
import hu.grdg.projlab.model.Player;
import hu.grdg.projlab.model.Tile;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class PickupCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        String pName = getStringArg(inputParams);

        Player player = getPlayer(pName, state);
        if(player == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_PLAYER_NOT_FOUND);
            return;
        }

        Tile tile = player.getCurrentTile();
        if(tile == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_NOT_ON_TILE);
            return;
        }

        Item itm = tile.getFrozenItem();
        if(itm == null) {
            ProtoIO.output(ProtoIO.OutputMessages.PICKUP_ERR_NOITEM_OR_SNOW);
            return;
        }

        if(itm.pickedUp(player))
            ProtoIO.output(ProtoIO.OutputMessages.PICKUP_OUT);
        else
            ProtoIO.output(ProtoIO.OutputMessages.PICKUP_ERR_FORZEN);

    }

    @Override
    public int getParamCount() {
        return 1;
    }
}
