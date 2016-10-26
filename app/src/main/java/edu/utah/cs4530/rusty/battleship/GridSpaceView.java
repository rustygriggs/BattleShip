package edu.utah.cs4530.rusty.battleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.Set;

/**
 * Created by Rusty on 10/24/2016.
 */
public class GridSpaceView extends View {
    int _gridSpaceColor = Color.BLUE;
    int _gridIndex;

    public GridSpaceView(Context context) {
        super(context);
    }

    public GridSpaceView(Context context, int gridIndex) {
        super(context);
        _gridIndex = gridIndex;
    }

    public interface OnGridSpaceTouchedListener {
        void onGridSpaceTouched(GridSpaceView gridSpaceView);
    }

    OnGridSpaceTouchedListener _onGridSpaceTouchedListener = null;

    public void setOnGridSpaceTouchedListener(OnGridSpaceTouchedListener listener) {
        _onGridSpaceTouchedListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setBackgroundColor(_gridSpaceColor);

        RectF gridSpaceRect = new RectF();
        gridSpaceRect.left = getPaddingLeft();
        gridSpaceRect.right = getWidth() - getPaddingRight();
        gridSpaceRect.top = getPaddingTop();
        gridSpaceRect.bottom = getHeight() - getPaddingBottom();

        Paint gridLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridLine.setColor(Color.BLACK);
        gridLine.setStrokeWidth(getWidth() * .05f);
        gridLine.setStyle(Paint.Style.STROKE);
        canvas.drawRect(gridSpaceRect, gridLine);
    }

    public void setColor (int color) {
        _gridSpaceColor = color;
        invalidate();
    }

    public int getColor() {
        return _gridSpaceColor;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            _onGridSpaceTouchedListener.onGridSpaceTouched(this);
        }
        return true;
    }


}
