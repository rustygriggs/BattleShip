package edu.utah.cs4530.rusty.battleship;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public static final String OPPONENT_GRID_FRAGMENT_TAG = "GridsFragment";
    public static final String GAME_LIST_FRAGMENT_TAG = "GameListFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        setContentView(rootLayout);

        LinearLayout gridsLayout = new LinearLayout(this);
        gridsLayout.setOrientation(LinearLayout.VERTICAL);

        FrameLayout opponentGrid = new FrameLayout(this);
        opponentGrid.setId(11);
        gridsLayout.addView(opponentGrid, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 2));

        FrameLayout gameListView = new FrameLayout(this);
        gameListView.setId(12);

        rootLayout.addView(gameListView, new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        rootLayout.addView(gridsLayout, new LinearLayout.LayoutParams(
                        0, ViewGroup.LayoutParams.MATCH_PARENT, 3));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        GridsFragment gridsFragment = (GridsFragment)
                getSupportFragmentManager().findFragmentByTag(OPPONENT_GRID_FRAGMENT_TAG);
        GameListFragment gameListFragment = (GameListFragment)
                getSupportFragmentManager().findFragmentByTag(GAME_LIST_FRAGMENT_TAG);

        if (gridsFragment == null) {
            gridsFragment = GridsFragment.newInstance();
            gameListFragment = GameListFragment.newInstance();
            transaction.add(opponentGrid.getId(), gridsFragment, OPPONENT_GRID_FRAGMENT_TAG);
            transaction.add(gameListView.getId(), gameListFragment, GAME_LIST_FRAGMENT_TAG);
        }
        else {
            transaction.replace(opponentGrid.getId(), gridsFragment);
            transaction.replace(gameListView.getId(), gameListFragment);
        }
        transaction.commit();
    }
}
