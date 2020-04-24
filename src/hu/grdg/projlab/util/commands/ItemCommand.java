package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.*;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class ItemCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        String type = getStringArgOf(inputParams, setOf("Rope", "DivingSuit","Food","Shovel", "BreakableShovel","Tent","RocketPart"));
        String fieldName = getStringArg(inputParams);
        Tile t;
        if((t = state.getTile(fieldName)) == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_TILE_NOT_FOUND);
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
        t.setFrozenItem(itm);
        ProtoIO.output(ProtoIO.OutputMessages.ITEM_OUT);
    }

    @Override
    public int getParamCount() {
        return 2;
    }
}
