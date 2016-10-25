package edu.utah.cs4530.rusty.battleship;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rusty on 10/24/2016.
 */
public class GridLayoutCustom extends ViewGroup implements GridSpaceView.OnMissileFiredListener{
    /**
     * _gridSize should represent the number of columns and rows.
     * These should always be the same.
     */
    int _gridSize;

    public GridLayoutCustom(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int childCount = getChildCount();
        _gridSize = (int) Math.sqrt((double) childCount);

        int childWidth = getWidth() / _gridSize;
        int childHeight  = getHeight() / _gridSize;

        for (int childIndex = 0; childIndex < childCount; childIndex++) {

            Rect childRect = new Rect();
            childRect.left = (childIndex % _gridSize) * childWidth;
            childRect.right = childRect.left + childWidth;
            childRect.top = (childIndex / _gridSize) * childHeight;
            childRect.bottom = childRect.top + childHeight;

            GridSpaceView childView = (GridSpaceView) getChildAt(childIndex);
            childView.setOnMissileFiredListener(this);
            childView.layout(childRect.left, childRect.top, childRect.right, childRect.bottom);
        }
    }

    @Override
    public void onMissileFired(GridSpaceView gridSpaceView) {
        //TODO: what to do when missile if fired
        Log.i("Missile", "Missile fired and onMissileFiredListener is functioning properly");
    }
}
