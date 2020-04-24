package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.*;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class AddCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        String pName = getStringArg(inputParams);
        String type = getStringArgOf(inputParams, setOf("Rope", "DivingSuit","Food","Shovel", "BreakableShovel","Tent","RocketPart"));

        Player player = getPlayer(pName, state);
        if(player == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_PLAYER_NOT_FOUND);
            return;
        }

        Item itm = null;
        switch (type) {
            case "Rope":
                itm = new Rope();
                break;
            case "DivingSuit":
                itm = new DivingSuit();
                break;
            case "Food":
                itm = new Food();
                break;
            case "Shovel":
                itm = new Shovel();
                break;
            case "BreakableShovel":
                itm = new BreakableShovel();
                break;
            case "Tent":
                itm = new Tent();
                break;
            case "RocketPart":
                itm = new RocketPart(state.getController());
                break;
        }
        if(itm != null) {
            int slot = player.addItem(itm);
            itm.setFuckinOwner(player);

            ProtoIO.outputf(ProtoIO.OutputMessages.ADD_OUT, slot);
        }

    }

    @Override
    public int getParamCount() {
        return 2;
    }
}
