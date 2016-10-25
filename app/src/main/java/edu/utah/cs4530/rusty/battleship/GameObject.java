package edu.utah.cs4530.rusty.battleship;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Rusty on 10/13/2016.
 */
public class GameObject implements Serializable {
    Map<Integer, Boolean> _player1Ships = new HashMap<>();
    Map<Integer, Boolean> _player2Ships = new HashMap<>();

    Map<Integer, Boolean> _player1Guesses = new HashMap<>();
    Map<Integer, Boolean> _player2Guesses = new HashMap<>();

    /**
     * default constructor. Adds 5 ships to each player's ship map
     */
    GameObject() {
        for (int i = 0; i < 100; i++) {
            _player1Guesses.put(i, false);
            _player2Guesses.put(i, false);
        }
        placeShips(5);
        placeShips(4);
        placeShips(3);
        placeShips(3);
        placeShips(2);
    }

    private void placeShips(int shipSize) {
        Random random = new Random();
        Boolean vertical = random.nextBoolean();
        //if vertical, / 10
        if (vertical) {
            random.nextInt(shipSize);
            //TODO: do this method
        }
        //if horizontal % 10
        else {

        }
    }

    public Map<Integer, Boolean> getPlayer1Ships() {
        return _player1Ships;
    }

    public Map<Integer, Boolean> getPlayer2Ships() {
        return _player2Ships;
    }

    public Map<Integer, Boolean> getPlayer1Guesses() {
        return _player1Guesses;
    }

    public Map<Integer, Boolean> getPlayer2Guesses() {
        return _player2Guesses;
    }

    public void player1LaunchesMissile(int index) {

    }

    public void player2LaunchesMissile(int index) {

    }
}
