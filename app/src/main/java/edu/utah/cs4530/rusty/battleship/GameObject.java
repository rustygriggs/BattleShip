package edu.utah.cs4530.rusty.battleship;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Rusty on 10/13/2016.
 */
public class GameObject implements Serializable {
    public static final String CARRIER = "carrier";
    public static final String BATTLESHIP = "battleship";
    public static final String CRUISER = "cruiser";
    public static final String SUBMARINE = "submarine";
    public static final String DESTROYER = "destroyer";
    public static final int HIT = 1;
    public static final int MISS = 0;
    public static final int DISALLOW = -1;
    public static final int SHIP_LOCATION = 2;

    OnGameUpdatedListener _gameUpdatedListener = null;

    public interface OnGameUpdatedListener {
        void onGameUpdated(int hitCode, int missileIndex, Set<Integer> allShips);
    }

    public void setOnGameUpdatedListener(OnGameUpdatedListener listener) {
        _gameUpdatedListener = listener;
    }

    Set<Integer> allShips = new HashSet<>();

    Set<Integer> _player1Ships;
    Set<Integer> _player2Ships;

    Set<Integer> _player1Guesses = new HashSet<>();
    Set<Integer> _player2Guesses = new HashSet<>();

    int _currentPlayer = 1;

    Random random = new Random();

    //for the purpose of placing the ships and determining if they overlap
    Set<Integer> _destroyer = new HashSet<>(); // size 2
    Set<Integer> _submarine = new HashSet<>(); // size 3
    Set<Integer> _cruiser = new HashSet<>(); // size 3
    Set<Integer> _battleship = new HashSet<>(); // size 4
    Set<Integer> _carrier = new HashSet<>(); // size 5


    /**
     * default constructor. Adds 5 ships to each player's ship map
     */
    GameObject() {
        for (int player = 1; player <= 2; player++) {
            placeShip(player, 5, CARRIER); //carrier
            placeShip(player, 4, BATTLESHIP); //battleship
            placeShip(player, 3, CRUISER); //cruiser
            placeShip(player, 3, SUBMARINE); //submarine
            placeShip(player, 2, DESTROYER); //destroyer
            if (player == 1) {
                _player1Ships = new HashSet<>(allShips);
            }
            else {
                _player2Ships = new HashSet<>(allShips);
            }
            allShips.clear();
        }
    }

    private void placeShip(int player, int shipSize, String shipType) {
        //make the horizontal vs vertical random
        //start over:
        Boolean vertical = random.nextBoolean();
        int startingPosition = getStartingPosition(shipSize, vertical);
        Set<Integer> tempSet = new HashSet<>();
        int currentShipPos = startingPosition;
        checkShipCollisions(shipSize, shipType, currentShipPos, vertical, player);

        for (int i = 0; i < shipSize; i++) {
            if (vertical) {
                //this should check to make sure none of ships are overlapping
                if (!allShips.contains(currentShipPos))
                    tempSet.add(currentShipPos);
                allShips.add(currentShipPos);
                currentShipPos = currentShipPos - 10;
            }
            else {
                if (!allShips.contains(currentShipPos)) {
                    tempSet.add(currentShipPos);
                }
                allShips.add(currentShipPos);
                currentShipPos = currentShipPos - 1;
            }
//            _gameUpdatedListener.onGameUpdated(SHIP_LOCATION, currentShipPos, allShips);
        }
        //TODO: decide if I really need this. I don't think I do (I'm using allShips instead)
        switch (shipType) {
            case CARRIER:
                _carrier = tempSet;
                break;
            case BATTLESHIP:
                _battleship = tempSet;
                break;
            case SUBMARINE:
                _submarine = tempSet;
                break;
            case CRUISER:
                _cruiser = tempSet;
                break;
            case DESTROYER:
                _destroyer = tempSet;
                break;
            default:
                break;
        }
    }

    private void checkShipCollisions(int shipSize, String shipType, int nextShipPos, Boolean vertical, int player) {
        int checkPositionTemp = nextShipPos;
        if (vertical) {
            for (int j = 0; j < shipSize; j++) {
                if (allShips.contains(checkPositionTemp)) {
                    //just start the method over which will get a new starting position which will eventually work right?
                    placeShip(player, shipSize, shipType);
                }
                if (vertical) {
                    checkPositionTemp = checkPositionTemp - 10;
                }
                else {
                    checkPositionTemp = checkPositionTemp - 1;
                }
            }
        }
    }

    private int getStartingPosition(int shipSize, Boolean vertical) {
        int sp;
        if (vertical) {
            sp = ThreadLocalRandom.current().nextInt((shipSize - 1) * 10, 100);
        }
        else {
            do {
                sp = random.nextInt(100);
            } while (sp % 10 < shipSize - 1);
        }

        return sp;
    }

    public Set<Integer> getPlayer1Ships() {
        return _player1Ships;
    }

    public Set<Integer> getPlayer2Ships() {
        return _player2Ships;
    }

    public Set<Integer> getPlayer1Guesses() {
        return _player1Guesses;
    }

    public Set<Integer> getPlayer2Guesses() {
        return _player2Guesses;
    }

    public int player1LaunchesMissile(int index) {
        int hitCode;
        if (_player1Guesses.contains(index)) {
            //TODO: send a notification that they can't pick this spot or something like that
            hitCode = DISALLOW;
        }
        else if (_player2Ships.contains(index)) {
            //HIT! return red color
            _player1Guesses.add(index);
            _currentPlayer = 2;
            hitCode = HIT;
        }
        else {
            //MISS! return white color
            _player1Guesses.add(index);
            _currentPlayer = 2;
            hitCode = MISS;
        }
        _gameUpdatedListener.onGameUpdated(hitCode, index, allShips);
        return hitCode;
    }

    public int player2LaunchesMissile(int index) {
        int hitCode;
        if (_player2Guesses.contains(index)) {
            //TODO: send a notification that they can't pick this spot or something like that
            hitCode = DISALLOW;
        }
        else if (_player1Ships.contains(index)) {
            //HIT! return red color
            _player2Guesses.add(index);
            _currentPlayer = 1;
            hitCode = HIT;
        }
        else {
            //MISS! return white color
            _player2Guesses.add(index);
            _currentPlayer = 1;
            hitCode = MISS;
        }
        _gameUpdatedListener.onGameUpdated(hitCode, index, allShips);
        return hitCode;
    }
}
