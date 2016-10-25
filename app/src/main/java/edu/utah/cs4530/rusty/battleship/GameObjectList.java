package edu.utah.cs4530.rusty.battleship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * **Data Model**
 * Singleton class used to contain all the game objects in the game.
 *
 * Created by Rusty on 10/13/2016.
 */
public class GameObjectList extends Object implements Serializable {
    //Singleton class
    private static GameObjectList _instance;

    /**
     * Used to get the one instance of this class.
     * @return the single instance of Gallery that has been instantiated.
     */
    public static GameObjectList getInstance() {
        if (_instance == null) {
            _instance = new GameObjectList();
        }
        return _instance;
    }

    /**
     * setter for the Gallery.
     * @param gameObjects will replace the old instance with this one.
     */
    public static void setInstance(GameObjectList gameObjects) {
        _instance = gameObjects;
    }

    List<GameObject> _gameList = new ArrayList<>();

    int getGameObjectsCount() {
        return _gameList.size();
    }




    //These following methods are apparently needed for the user to control the data model

    /**
     * create method
     */
    void createNewGame() {
        _gameList.add(new GameObject());
    }

    /**
     * read method
     * @param gameIndex
     * @return
     */
    GameObject readGame(int gameIndex) {
        return _gameList.get(gameIndex);
    }

    /**
     * update (play) method. Might need more parameters
     * @param gameIndex
     */
    void updateGame(int gameIndex) {

    }

    /**
     * delete method
     * @param gameIndex
     */
    void deleteGame(int gameIndex) {

    }


}