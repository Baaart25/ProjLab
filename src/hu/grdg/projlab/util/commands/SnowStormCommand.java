package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.SnowStorm;
import hu.grdg.projlab.model.Tile;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hu.grdg.projlab.ProtoIO.OutputMessages.*;

public class SnowStormCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        if(inputParams.size() % 2 != 0) {
            throw new CommandException("The command requires a list of tile-name and amount pairs");
        }

       HashMap<Tile, Integer> pairs = new HashMap<>();
        while(inputParams.size() > 0) {
            String name = getStringArg(inputParams);
            int amount = getIntArg(inputParams);

            Tile t;
            if((t = state.getTile(name)) == null) {
                ProtoIO.output(ERR_TILE_NOT_FOUND);
                return;
            }

            pairs.put(t, amount);
        }

        //FIXME Fix the fuckin test order
        ProtoIO.output(SNOWSTORM_OUT_NORMAL);

        for (Map.Entry<Tile, Integer> tileIntegerEntry : pairs.entrySet()) {
            SnowStorm.getInstance().doStorm(tileIntegerEntry.getKey(), tileIntegerEntry.getValue());
        }


    }

    @Override
    public int getParamCount() {
        return 2;
    }
}
