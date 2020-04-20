package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.ProtoRuntime;

import java.util.List;

public class ClearGameCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) {
        state.reset();
        ProtoIO.output(ProtoIO.OutputMessages.CLEARGAME_OUT);
    }

    @Override
    public int getParamCount() {
        return 0;
    }
}
