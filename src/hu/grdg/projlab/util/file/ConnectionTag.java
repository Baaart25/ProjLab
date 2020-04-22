package hu.grdg.projlab.util.file;

/**
 * Connection tag format
 * <connection | name1,dir1:name2,dir2>
 */
public class ConnectionTag extends Tag<ConnectionClass> {
    public ConnectionTag(String textData) {
        super(textData);
    }

    @Override
    public ConnectionClass getData() throws GameLoadException {
        String part1 = textData.split(":")[0];
        String part2 = textData.split(":")[1];

        ConnectionClass cc = new ConnectionClass();
        cc.name1 = part1.split(",")[0];
        cc.name2 = part2.split(",")[0];
        cc.dir1 = Integer.parseInt(part1.split(",")[1]);
        cc.dir2 = Integer.parseInt(part2.split(",")[1]);

        return cc;
    }

    @Override
    public Tag<ConnectionClass> createFromData(ConnectionClass data) {
        return null;
    }
}
