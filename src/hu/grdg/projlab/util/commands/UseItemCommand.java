package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.Item;
import hu.grdg.projlab.model.Player;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

//FIXME Direction is not used for DivingSuit
public class UseItemCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        String pName = getStringArg(inputParams);
        int slot = getIntArg(inputParams);

        int arg = -1;
        if(inputParams.size() > 0 && !inputParams.get(0).equals("null"))
            arg = getIntArg(inputParams);

        Player p;
        if((p = getPlayer(pName,state)) == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_PLAYER_NOT_FOUND);
            return;
        }

        try {
            Item item = p.getInventory().get(slot - 1);
            boolean succ = item.useItem();
            if(succ)
                ProtoIO.output(ProtoIO.OutputMessages.USEITEM_OUT);
            else
                ProtoIO.output(ProtoIO.OutputMessages.USEITEM_ERR_ITEMFAIL);
        } catch (Exception e) {
            e.printStackTrace();
            ProtoIO.output(ProtoIO.OutputMessages.USEITEM_ERR_NOITEM);
        }

    }

    @Override
    public int getParamCount() {
        return 2;
    }
}
