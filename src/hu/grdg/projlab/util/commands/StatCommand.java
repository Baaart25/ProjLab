package hu.grdg.projlab.util.commands;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.*;
import hu.grdg.projlab.util.Command;
import hu.grdg.projlab.util.CommandException;
import hu.grdg.projlab.util.ProtoRuntime;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class StatCommand extends Command {
    @Override
    public void runCommand(ProtoRuntime state, List<String> inputParams) throws CommandException {
        String eName = getStringArg(inputParams);
        Entity entity = state.getEntity(eName);
        Tile tile = state.getTile(eName);
        if(entity == null && tile == null) {
            ProtoIO.output(ProtoIO.OutputMessages.ERR_PLAYER_NOT_FOUND);
        }
        try {
            if(entity != null) {
                stat(entity);
            }else if(tile != null) {
                stat(tile);
            }
        }catch (IllegalAccessException e) {
            CommandException ce = new CommandException("Stat reflection error");
            ce.initCause(e);
            throw ce;
        }

    }

    private void stat(Entity entity) throws IllegalAccessException {
        doStat(entity);
    }

    private void stat(Tile tile) throws IllegalAccessException {
        doStat(tile);
    }

    private void doStat(Object intstance) throws IllegalAccessException {
        HashMap<String, String> dataMap = new HashMap<>();

        //Get class instance from object (for ex: UnstableIceTile.class for unstable ice tile instances)
        Class clazz = intstance.getClass();

        //We iterate over the inheritance tree and print all fields until Object.class
        for(Class base = clazz; base != Object.class; base = base.getSuperclass()) {
            //Get all fields in the class
            Field[] declaredFields = base.getDeclaredFields();
            //Iterate over the fields
            for (Field declaredField : declaredFields) {
                //Get the field name
                String name = declaredField.getName();
                //Set field accessibility so we can get the value
                declaredField.setAccessible(true);
                //Get the value of the field in the specified instance
                Object valueObj = declaredField.get(intstance);
                String value = valueObj == null ? "null" : valueObj.toString();
                dataMap.put(name, value);
            }
        }
        //Get simple class name(without package)
        String tName = clazz.getSimpleName();
        String valString = dataMap.entrySet().stream().map(stringStringEntry -> String.format("%s: %s", stringStringEntry.getKey(), stringStringEntry.getValue())).collect(Collectors.joining(","));

        ProtoIO.outputf("%s stat: [%s]", tName, valString);
    }

    @Override
    public int getParamCount() {
        return 1;
    }
}
