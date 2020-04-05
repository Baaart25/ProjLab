package hu.grdg.projlab.save;

/**
 * A storage element in the map save system
 * @author Barrow099
 */
public class Element {

    /**
     * Element type for the elements
     * @author barrow099
     */
    public enum ElementType {

        MAPWIDTH("mapwidth"), MAPHEIGHT("mapheight"), ENTITY("entity"), TILE("tile");

        /**
         * Textual form of the type
         */
        private String text;

        /**
         * New element type
         * @param text Type string
         */
        ElementType(String text) {
            this.text = text;
        }

        /**
         * Gets the textual form of the element type
         * @return The text
         */
        public String getText() {
            return text;
        }

        /**
         * Parses element type form string form
         * @param text Text form
         * @return The pared element
         * @throws IllegalArgumentException If no type is found
         */
        public static ElementType parse(String text) {
            switch (text) {
                case "mapwidth":
                    return MAPWIDTH;
                case "mapheight":
                    return MAPHEIGHT;
                case "enity":
                    return ENTITY;
                case "tile":
                    return TILE;
                default:
                    throw new IllegalArgumentException("Invalid type string");
            }
        }
    }

    /**
     * The type of the element
     */
    private ElementType type;

    /**
     * The data of the element
     */
    private String data;

    /**
     * New element with data and type
     * @param type Element type
     * @param data Element data
     */
    public Element(ElementType type, String data) {
        this.type = type;
        this.data = data;
    }

    /**
     * Get the textual representation of this element
     * @return The element text
     */
    public String toElementString() {
        return String.format("<%s | %s>", type, data);
    }

    public ElementType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    /**
     * Parses a line of elements to an actual element array
     * @param line Element text line
     * @return The array of parsed elements
     * @author Barrow099
     */
    public static Element[] parseLine(String line) throws Exception {
        String data[] = line.split(" ");
        Element[] elements = new Element[data.length];
        for(int i = 0; i < data.length; i++){
            elements[i] = parseElement(data[i]);
        }
        return elements;
    }

    /**
     * Parses an Element from the textual representation
     * @param elementText The textual form of the element
     * @return The parsed element
     * @author Barrow099
     */
    public static Element parseElement(String elementText) throws Exception{
        if(elementText.startsWith("<") && elementText.endsWith(">")) {
            String parts[] = elementText.split(" \\| ");
            if(parts.length == 2) {
                String type = parts[0];
                String data = parts[1];
                return new Element(ElementType.parse(type), data);
            }
        }
        throw new Exception("Invalid save file format");
    }
}
