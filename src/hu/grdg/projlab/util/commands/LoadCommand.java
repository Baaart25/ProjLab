package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.model.Entity;
import hu.grdg.projlab.model.Tile;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoInputSystem;
import hu.grdg.projlab.util.ProtoRuntime;
import hu.grdg.projlab.util.file.SavedGame;

import java.util.List;
import java.util.Map;

public class LoadCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        state.reset();
        SavedGame sg = SavedGame.load(getStringArg(inputParams));
        ProtoRuntime pr = sg.getState();

        for (Map.Entry<String, Tile> tile : pr.getTiles()) {
            state.addTile(tile.getKey(), tile.getValue());
        }

        for (Map.Entry<String, Entity> entity : pr.getEntities()) {
            state.addEntity(entity.getKey(), entity.getValue());
        }
    }

    @Override
    public int getParamCount() {
        return 1;
    }
}
