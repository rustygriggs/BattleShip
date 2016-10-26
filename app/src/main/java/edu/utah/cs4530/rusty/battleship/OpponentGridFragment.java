package edu.utah.cs4530.rusty.battleship;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rusty on 10/24/2016.
 */
public class OpponentGridFragment extends Fragment implements GridLayoutCustom.OnMissileFiredListener {
    List<GridSpaceView> _gridSpaces = new ArrayList<>();
    private int _currentGameIndex = 0;


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
        GridLayoutCustom gridLayout = new GridLayoutCustom(getActivity());
        for (int i = 0; i < 100; i++) {
            GridSpaceView gridSpaceView = new GridSpaceView(getActivity());
            _gridSpaces.add(gridSpaceView);
            gridLayout.addView(gridSpaceView);
        }
        gridLayout.setOnMissileFiredListener(this);
        return gridLayout;
    }

    @Override
    public void onMissileFired(int missileIndex) {
        //_gridSpaces.get(index);
        //TODO: talk with model.
        Log.i("OppGridFragment", "the missileFiredListener in OppenentGridFragment is functioning");
        GameObjectList.getInstance().updateGame(_currentGameIndex, missileIndex);
    }
}
