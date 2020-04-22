package hu.grdg.projlab.util.file;

import hu.grdg.projlab.ProtoIO;
import hu.grdg.projlab.model.Tile;
import hu.grdg.projlab.util.ProtoRuntime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Used for loading and storing the game
 * --------------WARNING-----------------
 * ---------SAVE FORMAT CHANGED----------
 * Only single tags per line are allowed
 * First line is tile count
 * Second line is entity count
 * Third line is connection count
 * Then  tile count tiles
 * Then connection time connections
 * Then N lines of entities
 */
public class SavedGame {
    //TODO SET TO FALSE
    public static final boolean DEBUG_MODE = true;


    private int tile_number, connection_count, entitycount;
    private ProtoRuntime state;


    public SavedGame(ProtoRuntime state) {
        this.state = state;
    }

    private SavedGame() {
        state = new ProtoRuntime();
    }

    /**
     * Loads a SavedGame from file
     * The game state can be retrieved from the returned instance
     * @param fileName The name of the savegame file
     * @return The loaded game
     * @throws IOException if any IO error occurs
     * @throws GameLoadException if format error, or any other load error occurs
     */
    public static SavedGame load(String fileName) {
        try {
            SavedGame ins = new SavedGame();
            ins.checkFile(fileName);

            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            ins.loadHeader(reader);
            ins.loadContent(reader);

            reader.close();
            ProtoIO.output(ProtoIO.OutputMessages.LOAD_OUT);
            return ins;
        }catch (IOException | GameLoadException e) {
            ProtoIO.outputf(ProtoIO.OutputMessages.LOAD_ERR_FORMAT, e.getMessage());
        }
        return null;
    }

    public ProtoRuntime getState() {
        return state;
    }

    /**
     * Loads the save content
     * @param reader
     */
    private void loadContent(BufferedReader reader) throws IOException, GameLoadException {
        //Load tile contents
        for(int i = 0; i < tile_number; i++) {
            TileClass tc = (TileClass) TagIO.readTag(reader).getData();
            state.addTile(tc.name, tc.tile);
        }

        //Load connections
        for(int i = 0; i < connection_count; i++) {
            ConnectionClass cc = (ConnectionClass) TagIO.readTag(reader).getData();
            Tile t1 = state.getTile(cc.name1);
            Tile t2 = state.getTile(cc.name2);
            t1.setNeighbour(t2, cc.dir1);
            t2.setNeighbour(t1, cc.dir2);
        }

        for(int i = 0; i < entitycount; i++) {
            EntityClass ec = (EntityClass) TagIO.readTag(reader).getData();
            state.addEntity(ec.name, ec.entity);
            Tile t = state.getTile(ec.tile);
            t.acceptEntity(ec.entity);
        }



    }

    /**
     * Loads the save header from the file
     * @param reader The file reader
     */
    private void loadHeader(BufferedReader reader) throws IOException, GameLoadException {
        int tilenum = (int) TagIO.readTag(reader).getData();
        int connum = (int) TagIO.readTag(reader).getData();
        int ecount = (int) TagIO.readTag(reader).getData();
        tile_number = tilenum;
        connection_count = connum;
        entitycount = ecount;
        if(DEBUG_MODE) {
            ProtoIO.outputf("Map header: %d tiles, %d connections, it has %d entities", tile_number, connection_count, entitycount);
        }
    }

    /**
     * Checks if a file exists
     * @param fileName The path of the file
     * @throws IOException If the file does not exists
     */
    private void checkFile(String fileName) throws IOException {
        if(!new File(fileName).exists()) {
            throw new IOException("The savegame file does not exists");
        }
    }
}
