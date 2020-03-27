package hu.grdg.projlab.model;

import hu.grdg.projlab.SkeletonTester;

/**
 * Main game controller, coordinates game events, player turns
 * It stores the players and the current level
 */
public class Controller {

    public void endGame(boolean b){
        SkeletonTester.call(this, b);

        SkeletonTester.creturn();
    }
}
