package edu.utah.cs4530.rusty.battleship;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rusty on 10/24/2016.
 */
public class OwnGridFragment extends Fragment implements GridLayoutCustom.OnMissileFiredListener {
    private int _currentGameIndex = 0;
    List<GridSpaceView> _gridSpaces;
    GridLayoutCustom _gridLayout;

    public static OwnGridFragment newInstance() {
        //this stuff was included when I just tabbed to write newInstance()
//        Bundle args = new Bundle();
//
//        GalleryFragment fragment = new GalleryFragment();
//        fragment.setArguments(args);
//        return fragment;

        return new OwnGridFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        View leftSideView = new View(getActivity());
        leftSideView.setBackgroundColor(Color.BLACK);

        linearLayout.addView(leftSideView, new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

         _gridLayout = new GridLayoutCustom(getActivity());
        _gridLayout.setEnabled(false);
         _gridSpaces = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            GridSpaceView gridSpaceView = new GridSpaceView(getActivity());
            gridSpaceView.setEnabled(false);
            _gridSpaces.add(gridSpaceView);
            _gridLayout.addView(gridSpaceView);
        }
        _gridLayout.setOnMissileFiredListener(this);

        linearLayout.addView(_gridLayout, new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 3));

        View rightSideView = new View(getActivity());
        rightSideView.setBackgroundColor(Color.BLACK);

        linearLayout.addView(rightSideView, new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        return linearLayout;
    }

    @Override
    public void onMissileFired(int missileIndex) {
        int hitCode = GameObjectList.getInstance().updateGame(_currentGameIndex, missileIndex);
        //how bout instead return a List of numbers where a 0 is a miss, a 1 is a hit, then loop through and repaint all the spaces
        Map<Integer, Integer> player1State = GameObjectList.getInstance().readGame(_currentGameIndex)._player1State;
        Map<Integer, Integer> player2State = GameObjectList.getInstance().readGame(_currentGameIndex)._player2State;

        if (GameObjectList.getInstance().readGame(_currentGameIndex)._currentPlayer == 1) {
            for (Integer hitMissIndex : player1State.keySet()) {
                if (player1State.get(hitMissIndex) == 0) {
                    _gridSpaces.get(hitMissIndex).setColor(Color.WHITE);
                    _gridSpaces.get(hitMissIndex).setEnabled(false);
                } else if (player1State.get(hitMissIndex) == 1) {
                    _gridSpaces.get(hitMissIndex).setColor(Color.RED);
                    _gridSpaces.get(hitMissIndex).setEnabled(false);
                }
                else if (player1State.get(hitMissIndex) == 2) {
                    _gridSpaces.get(hitMissIndex).setColor(Color.GRAY);
                }
                else {
                    _gridSpaces.get(hitMissIndex).setColor(Color.BLUE);
                }
            }
        } else {
            for (Integer hitMissIndex : player2State.keySet()) {
                if (player2State.get(hitMissIndex) == 0) {
                    _gridSpaces.get(hitMissIndex).setColor(Color.WHITE);
                    _gridSpaces.get(hitMissIndex).setEnabled(false);

                } else if (player2State.get(hitMissIndex) == 1) {
                    _gridSpaces.get(hitMissIndex).setColor(Color.RED);
                    _gridSpaces.get(hitMissIndex).setEnabled(false);
                }
                else if (player2State.get(hitMissIndex) == 2) {
                    _gridSpaces.get(hitMissIndex).setColor(Color.GRAY);
                }
                else {
                    _gridSpaces.get(hitMissIndex).setColor(Color.BLUE);
                }
            }
        }
    }
}
