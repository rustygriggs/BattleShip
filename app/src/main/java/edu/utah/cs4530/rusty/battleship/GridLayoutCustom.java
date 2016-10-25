package edu.utah.cs4530.rusty.battleship;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rusty on 10/24/2016.
 */
public class GridLayoutCustom extends ViewGroup {
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

        //TODO: figure out child height and width based on view dimensions
        int childWidth = getWidth() / _gridSize;
        int childHeight  = getHeight() / _gridSize;

        for (int childIndex = 0; childIndex < childCount; childIndex++) {

            Rect childRect = new Rect();
            childRect.left = (childIndex % 10) * childWidth;
            childRect.right = childRect.left + childWidth;
            childRect.top = (childIndex / 10) * childHeight;
            childRect.bottom = childRect.top + childHeight;

            View childView = getChildAt(childIndex);
            childView.layout(childRect.left, childRect.top, childRect.right, childRect.bottom);
        }
    }
}
