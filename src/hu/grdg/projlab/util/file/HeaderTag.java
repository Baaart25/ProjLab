package hu.grdg.projlab.util.file;

public class HeaderTag extends Tag<Integer> {

    public HeaderTag(String textData) {
        super(textData);
    }



    @Override
    public Integer getData() {
        return Integer.parseInt(textData);
    }

    //FIXME Add saving capability
    @Override
    public Tag<Integer> createFromData(Integer data) {
        return null;
    }
}
