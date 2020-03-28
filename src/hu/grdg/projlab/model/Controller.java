package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

/**
 * Main game controller, coordinates game events, player turns
 * It stores the players and the current level
 */
public class Controller {
    private Player[] players;
    private Level level;

    public void endGame(boolean b){
        SkeletonTester.call(this, b);

        SkeletonTester.creturn();
    }

    /**
     * Megnézi, hogy a győzelemhez a feltételek fennállnak-e.
     * @return győztek-e
     * @author Dani
     */
    public boolean checkWin() {
        SkeletonTester.call(this);

        //összes játékos egy táblán áll e
        Tile refTile = players[0].getCurrentTile();
        for (Player player : players) {
            if(refTile != player.getCurrentTile()) {
                SkeletonTester.creturn(false);
                return false;
            }
        }

        //üsszes RocketPart valamelyik játékosnál van e
        RocketPart[] rocketParts = level.getRocketParts();
        for (RocketPart rocketPart : rocketParts) {
            if(rocketPart.getOwner() == null) {
                SkeletonTester.creturn(false);
                return false;
            }
        }

        /*ha idáig eljutunk, akkor teljesült, hogy egy mezőn áll minden játékos
          és, hogy az összes RocketPart valakinél van, azaz nyerhetnek! */
        endGame(true);

        SkeletonTester.creturn(true);
        return true;
    }

    /**
     * Beállítható vele a controller level-je
     * (ez a függvény csak a skeletonhoz kell!)
     * @param l level paraméter
     * @author Dani
     */
    public void setLevel(Level l) {
        SkeletonTester.call(this, l);
        level = l;
        SkeletonTester.creturn();
    }

    /**
     * Beállítható vele a players (játékosok listája)
     * (ez a függvény csak a skeletonhoz kelL!)
     * @param ps player tömb paraméter
     * @author Dani
     */
    public void setPlayers(Player[] ps) {
        SkeletonTester.call(this, ps);
        players = ps;
        SkeletonTester.creturn();
    }
}