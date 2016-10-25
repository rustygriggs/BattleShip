package edu.utah.cs4530.rusty.battleship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    //for the purpose of placing the ships and determining if they overlap
    List<Integer> _destroyer = new ArrayList<>(); // size 2
    List<Integer> _submarine = new ArrayList<>(); // size 3
    List<Integer> _cruiser = new ArrayList<>(); // size 3
    List<Integer> _battleship = new ArrayList<>(); // size 4
    List<Integer> _carrier = new ArrayList<>(); // size 5


    /**
     * default constructor. Adds 5 ships to each player's ship map
     */
    GameObject() {
        for (int i = 0; i < 100; i++) {
            _player1Guesses.put(i, false);
            _player2Guesses.put(i, false);
        }
        placeShips(5); //carrier
        placeShips(4); //battleship
        placeShips(3); //cruiser
        placeShips(3); //submarine
        placeShips(2); //destroyer
    }

    private void placeShips(int shipSize) {
        Random random = new Random();
        //make the horizontal vs vertical random
        Boolean vertical = random.nextBoolean();
        //if vertical, / 10
        for (int i = 0; i < shipSize; i++) {
            //place first end of ship
            if (i == 0) {
                if (vertical) {
                    //start in a random place on the board
                    int shipPlacement = random.nextInt(100);

                    //TODO: do this method
                }
                //if horizontal % 10
                else {

                }

            }
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
