package edu.utah.cs4530.rusty.battleship;

import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Rusty on 10/24/2016.
 */
public class GameListFragment extends Fragment implements ListAdapter {
    public static GameListFragment newInstance() {
        //this stuff was included when I just tabbed to write newInstance()
//        Bundle args = new Bundle();
//
//        GalleryFragment fragment = new GalleryFragment();
//        fragment.setArguments(args);
//        return fragment;

        GameObjectList.getInstance().createNewGame();
        GameObjectList.getInstance().createNewGame();
        GameObjectList.getInstance().createNewGame();
        return new GameListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ListView listView = new ListView(getActivity());
        listView.setAdapter(this);

        return listView;

    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return GameObjectList.getInstance().getGameObjectsCount();
    }

    @Override
    public Object getItem(int i) {
        return GameObjectList.getInstance().readGame(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //This is the biggest one - most important
        //TODO: write this method to show information
        TextView textView = new TextView(getActivity());
        textView.setText("TEST text");
        textView.setBackgroundColor(Color.RED);


        return textView;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        if (getCount() == 0) {
            return true;
        }
        else return false;
    }
}
