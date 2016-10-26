package edu.utah.cs4530.rusty.battleship;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Rusty on 10/24/2016.
 */
public class OpponentGridFragment extends Fragment implements GridLayoutCustom.OnMissileFiredListener {
    List<GridSpaceView> _gridSpaces = new ArrayList<>();
    private int _currentGameIndex = 0;
    GridLayoutCustom _gridLayout;


    public static OpponentGridFragment newInstance() {
        //this stuff was included when I just tabbed to write newInstance()
//        Bundle args = new Bundle();
//
//        GalleryFragment fragment = new GalleryFragment();
//        fragment.setArguments(args);
//        return fragment;
        return new OpponentGridFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _gridLayout = new GridLayoutCustom(getActivity());
        for (int i = 0; i < 100; i++) {
            GridSpaceView gridSpaceView = new GridSpaceView(getActivity());
            _gridSpaces.add(gridSpaceView);
            _gridLayout.addView(gridSpaceView);
        }
        _gridLayout.setOnMissileFiredListener(this);
        return _gridLayout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameObjectList.getInstance().createNewGame();
    }

    @Override
    public void onMissileFired(int missileIndex) {
        //_gridSpaces.get(index);
        //TODO: talk with model.
        Log.i("OppGridFragment", "the missileFiredListener in OppenentGridFragment is functioning");
        int hitCode = GameObjectList.getInstance().updateGame(_currentGameIndex, missileIndex);
        //how bout instead return a List of numbers where a 0 is a miss, a 1 is a hit, then loop through and repaint all the spaces
        Map<Integer, Integer> player1State = GameObjectList.getInstance().readGame(_currentGameIndex)._player1State;
        Map<Integer, Integer> player2State = GameObjectList.getInstance().readGame(_currentGameIndex)._player2State;

        if (GameObjectList.getInstance().readGame(_currentGameIndex)._currentPlayer == 1) {
            for (Integer hitMissIndex : player1State.keySet()) {
                if (player1State.get(hitMissIndex) == 0) {
                    _gridSpaces.get(hitMissIndex).setColor(Color.WHITE);
                } else if (player1State.get(hitMissIndex) == 1) {
                    _gridSpaces.get(hitMissIndex).setColor(Color.RED);
                } else {
                    _gridSpaces.get(hitMissIndex).setColor(Color.BLUE);
                }
            }
        } else {
            for (Integer hitMissIndex : player2State.keySet()) {
                if (player2State.get(hitMissIndex) == 0) {
                    _gridSpaces.get(hitMissIndex).setColor(Color.WHITE);
                } else if (player2State.get(hitMissIndex) == 1) {
                    _gridSpaces.get(hitMissIndex).setColor(Color.RED);
                } else {
                    _gridSpaces.get(hitMissIndex).setColor(Color.BLUE);
                }
            }
        /*
        if (hitCode == GameObject.MISS) {
            _gridSpaces.get(missileIndex).setColor(Color.WHITE);
        }
        else if (hitCode == GameObject.HIT) {
            _gridSpaces.get(missileIndex).setColor(Color.RED);
        }
        else if (hitCode == 2) {
            //_gridLayout.setGridSpaceColor(allShips);
        }
        else {

        }
        */
        }
    }
}
