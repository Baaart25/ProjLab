package hu.grdg.projlab.model;

public class PolarBear extends Entity{

    /**
     * Steps in given direction
     * @param direction Direction of step
     * @author Geri
     */
    @Override
    public void move(int direction) {
        Tile stepTile = currentTile.getNeighbour(direction);
        currentTile.removeEntity(this);

        stepTile.acceptEntity(this);
        stepTile.bearAttack();
    }

    /**
     * Does nothing, can't be damaged
     * @param i Amount of damage
     * @author Dorina, Geri
     */
    @Override
    public void damage(int i) {

    }

    /**
     * Does nothing, cannot die
     * @author Dorina, Geri
     */
    @Override
    public void die() {

    }

    /**
     * Does nothing, cannot suffocate
     * @author Dorina, Geri
     */
    @Override
    public void fallInWater() {

    }

    /**
     * Does nothing, cannot be saved
     * @param t Not relevant
     * @return false
     * @author Dorina, Geri
     */
    @Override
    public boolean savedFromWater(Tile t) {
        return false;
    }
}
