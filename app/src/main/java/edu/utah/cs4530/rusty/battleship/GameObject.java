package edu.utah.cs4530.rusty.battleship;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rusty on 10/13/2016.
 */
public class GameObject implements Serializable {
    Map<Integer, Boolean> _player1Ships = new HashMap<>();
    Map<Integer, Boolean> _player2Ships = new HashMap<>();

    Map<Integer, Boolean> _player1Guesses = new HashMap<>();
    Map<Integer, Boolean> _player2Guesses = new HashMap<>();


}
