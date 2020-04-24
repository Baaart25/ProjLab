package hu.grdg.projlab.model;

//Ez nincs a doksiban xD
public class Tent extends Item {
    private int life;

    /**
     * Sátor használata:
     * egy sátrat rak arra a mezőre ahol a tulajdonosa
     * áll, és gondoskodik arról, hogy az adott Tent feliratkozásra kerüljön a
     * RemoveTent osztályra.
     * @return a sikerességével tér vissza (false ha már van sátor az adott mezőn)
     * @author Dani
     */
    public boolean useItem(){
        Tile tile = owner.getCurrentTile();
        boolean res = tile.buildTent();
        if(res) {
            RemoveTent.getInstance().addTent(this);
        }
        return res;
    }

    /**Csökkenti eggyel a sátor élettartamát
     * @author Dani
     */
    public void decreaseLife() {
        life--;
        if(life == 0) {
            Tile tile = owner.getCurrentTile();
            tile.removeTent();
            RemoveTent.getInstance().removeTent(this);
        }
    }

    /**EZ MÉG NEM SZEREPELT DOKSIBAN!
     * Beállítja a paraméterként kapott értékre a sátor élettartamát
     * @param life
     * @author Dani
     */
    public void setLife(int life) {
        this.life = life;
    }

}