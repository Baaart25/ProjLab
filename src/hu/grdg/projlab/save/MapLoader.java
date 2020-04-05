package hu.grdg.projlab.save;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class MapLoader {

    private final String fileName;

    public MapLoader(String fileName) throws FileNotFoundException {
        if(! new File(fileName).exists())
            throw new FileNotFoundException("Save file not found: " + fileName);

        this.fileName = fileName;
    }

    /**
     * Loads the map from the file
     */
    public void load() {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            Element[] header = parseHeader(reader);
            int width = Integer.parseInt(header[0].getData());
            int height = Integer.parseInt(header[1].getData());

            ArrayList<Element> entities = new ArrayList<>();
            ArrayList<String> mapLines = new ArrayList<>(height);
            ArrayList<ArrayList<Element>> mapData = new ArrayList<>();

            String line;
            while((line = reader.readLine()) != null) {
                if(line.split(" ").length > 1) {
                    //We have a map line
                    mapLines.add(line);
                }else {
                    //We have a entity line
                    entities.add(Element.parseElement(line.strip()));
                }
            }

            if(mapLines.size() != height)
                throw new Exception("Invalid map file: map line count mismatch");

            int lc = 0;
            for(String mapLine : mapLines) {
                Element[] data = Element.parseLine(mapLine);
                if(data.length != width)
                    throw new Exception("Invalid map file: map width mismatch");
                mapData.set(lc, new ArrayList<>(Arrays.asList(data)));

                lc++;
            }

            Logger.getLogger("APP").info(String.format("Loaded a %dx%d map with %d entities", width, height, entities.size()));

        }catch (IOException e) {
            Logger.getLogger("ERROR").severe("IO error while loading save file");
            e.printStackTrace();
        }catch (Exception ex) {
            Logger.getLogger("ERROR").severe("Fatal error while loading save file");
            ex.printStackTrace();
        }
    }

    /**
     * Reads and parses the saved map header
     * @param reader The reader from where the data is being read
     * @return The header elements
     */
    private Element[] parseHeader(BufferedReader reader) throws Exception {
        String line = reader.readLine();

        Element[] e = Element.parseLine(line);
        if(e.length != 2 || e[0].getType() != Element.ElementType.MAPWIDTH || e[1].getType() != Element.ElementType.MAPHEIGHT)
            throw new Exception("Invalid save header");

        return e;
    }


}
