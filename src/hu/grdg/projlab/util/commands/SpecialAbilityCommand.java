package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.Direction;
import hu.grdg.projlab.model.Player;
import hu.grdg.projlab.model.Scientist;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;


//FIXME HOW THE FUCK WE WANT TO USE THE DIRECTION
public class SpecialAbilityCommand extends Command {

    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        String pName = getStringArg(inputParams);

        int dir = 0;
        if(inputParams.size() > 0 && !inputParams.get(0).equals("null"))
          dir = getIntArg(inputParams);

        Player p;
        if((p = getPlayer(pName, state)) == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_PLAYER_NOT_FOUND);
            return;
        }

        if(p instanceof Scientist && dir == 0) {
            throw new CommandException("Required int parameter was not provided");
        }
        Direction.direction = dir;
        if(p.specialAbility()) {

        }else {
            ProtoIO.output(ProtoIO.OutputMessages.SPECAB_ERR_FAILURE);
        }
    }

    @Override
    public int getParamCount() {
        return 2;
    }
}
