package edu.utah.cs4530.rusty.battleship;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public static final String OWN_GRID_FRAGMENT_TAG = "OwnGridFragment";
    public static final String OPPONENT_GRID_FRAGMENT_TAG = "OpponentGridFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(rootLayout);

        FrameLayout ownGrid = new FrameLayout(this);
        ownGrid.setId(10);
        rootLayout.addView(ownGrid, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

        FrameLayout opponentGrid = new FrameLayout(this);
        opponentGrid.setId(11);
        rootLayout.addView(opponentGrid, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 2));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        OwnGridFragment ownGridFragment = (OwnGridFragment)
                getSupportFragmentManager().findFragmentByTag(OWN_GRID_FRAGMENT_TAG);
        OpponentGridFragment opponentGridFragment = (OpponentGridFragment)
                getSupportFragmentManager().findFragmentByTag(OPPONENT_GRID_FRAGMENT_TAG);

        if (ownGridFragment == null) {
            ownGridFragment = OwnGridFragment.newInstance();
            opponentGridFragment = OpponentGridFragment.newInstance();
            transaction.add(ownGrid.getId(), ownGridFragment, OWN_GRID_FRAGMENT_TAG);
            transaction.add(opponentGrid.getId(), opponentGridFragment, OPPONENT_GRID_FRAGMENT_TAG);
        }
        else {
            transaction.replace(ownGrid.getId(), ownGridFragment);
            transaction.replace(opponentGrid.getId(), opponentGridFragment);
        }
        transaction.commit();
    }
}
