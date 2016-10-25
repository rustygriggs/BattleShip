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

/**
 * Created by Rusty on 10/24/2016.
 */
public class OpponentGridFragment extends Fragment {
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
        List<GridSpaceView> gridSpaces = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            GridSpaceView gridSpaceView = new GridSpaceView(getActivity());
            gridSpaces.add(gridSpaceView);
            gridLayout.addView(gridSpaceView);
        }
        return gridLayout;
    }
}
