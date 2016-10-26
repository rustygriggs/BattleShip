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
import java.util.Set;

/**
 * Created by Rusty on 10/24/2016.
 */
public class OpponentGridFragment extends Fragment implements GridLayoutCustom.OnMissileFiredListener,
                                                              GameObject.OnGameUpdatedListener {
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
        GameObjectList.getInstance().readGame(_currentGameIndex).setOnGameUpdatedListener(this);
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
        if (hitCode == 0) {
            _gridSpaces.get(missileIndex).setColor(Color.WHITE);
        }
        else if (hitCode == 1) {
            _gridSpaces.get(missileIndex).setColor(Color.RED);
        }
        else if (hitCode == 2) {
            //_gridLayout.setGridSpaceColor(allShips);
        }
        else {

        }
    }

    @Override
    public void onGameUpdated(int hitCode, int missileIndex, Set<Integer> allShips) {
        if (hitCode == 0) {
            _gridSpaces.get(missileIndex).setColor(Color.WHITE);
        }
        else if (hitCode == 1) {
            _gridSpaces.get(missileIndex).setColor(Color.RED);
        }
        else if (hitCode == 2) {
            _gridLayout.setGridSpaceColor(allShips);
        }
        else {

        }
    }
}
