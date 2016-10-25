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
public class OwnGridFragment extends Fragment {
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

        GridLayoutCustom gridLayout = new GridLayoutCustom(getActivity());
        List<GridSpaceView> gridSpaces = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            GridSpaceView gridSpaceView = new GridSpaceView(getActivity());
            gridSpaces.add(gridSpaceView);
            gridLayout.addView(gridSpaceView);
        }

        linearLayout.addView(gridLayout, new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 3));

        View rightSideView = new View(getActivity());
        rightSideView.setBackgroundColor(Color.BLACK);

        linearLayout.addView(rightSideView, new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        return linearLayout;
    }
}
