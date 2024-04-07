package com.example.mysplashscreen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
* CirclePagerIndicatorDecoration is retrieved from StacKOverFlow and provided by the following users:
 * David Medenjak, lbgupta, JCDecary, and others
 * https://stackoverflow.com/questions/33841363/how-to-make-a-page-indicator-for-horizontal-recyclerview
 *
*/
public class CirclePagerIndicatorDecoration extends RecyclerView.ItemDecoration {

    private int colorActive = ContextCompat.getColor(BottomNavActivity.getAppContext(), R.color.black);
    private int colorInactive = ContextCompat.getColor(BottomNavActivity.getAppContext(), R.color.darkgrey);

    private static final float DP = Resources.getSystem().getDisplayMetrics().density;

    /**
     * Height of the space the indicator takes up at the bottom of the view.
     */
    private final int mIndicatorHeight = (int) (DP * 16);

    /**
     * Indicator stroke width.
     */
    private final float mIndicatorStrokeWidth = DP * 4;

    /**
     * Indicator width.
     */
    private final float mIndicatorItemLength = DP * 4;
    /**
     * Padding between indicators.
     */
    private final float mIndicatorItemPadding = mIndicatorHeight;

    /**
     * Some more natural animation interpolation
     */
    private final Interpolator mInterpolator = (Interpolator) new AccelerateDecelerateInterpolator();

    private final Paint mPaint = new Paint();

    // starPaint is here in the event we want to do different types of scroll indicators
    // to serve as a reminder for the users
    private final Paint starPaint = new Paint();

    Path path = new Path();
    public CirclePagerIndicatorDecoration() {
        mPaint.setStrokeWidth(mIndicatorStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int itemCount = parent.getAdapter().getItemCount();

        // center horizontally, calculate width and subtract half from center
        float totalLength = mIndicatorItemLength * itemCount;
        float paddingBetweenItems = Math.max(0, itemCount - 1) * mIndicatorItemPadding;
        float indicatorTotalWidth = totalLength + paddingBetweenItems;
        float indicatorStartX = (parent.getWidth() - indicatorTotalWidth) / 2F;

        // set indicator to be at the top
        float indicatorPosY = 100;

        drawInactiveIndicators(c, indicatorStartX, indicatorPosY, itemCount);

        // find active page (which should be highlighted)
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int activePosition = layoutManager.findFirstVisibleItemPosition();
        if (activePosition == RecyclerView.NO_POSITION) {
            return;
        }

        // find offset of active page (if the user is scrolling)
        final View activeChild = layoutManager.findViewByPosition(activePosition);
        int left = activeChild.getLeft();
        int width = activeChild.getWidth();

        // on swipe the active item will be positioned from [-width, 0]
        // interpolate offset for smooth animation
        float progress = mInterpolator.getInterpolation(left * -1 / (float) width);

        drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress, itemCount);
    }

    private void drawInactiveIndicators(Canvas c, float indicatorStartX, float indicatorPosY, int itemCount) {
        mPaint.setColor(colorInactive);

        // width of item indicator including padding
        final float itemWidth = mIndicatorItemLength + mIndicatorItemPadding;

        float start = indicatorStartX;
        for (int i = 0; i < itemCount; i++) {
            // draw circle for every item
            c.drawCircle(start, indicatorPosY,mIndicatorHeight/2F,mPaint);
            start += itemWidth;
        }
    }

    private void drawHighlights(Canvas c, float indicatorStartX, float indicatorPosY,
                                int highlightPosition, float progress, int itemCount) {
        mPaint.setColor(colorActive);

        // width of item indicator including padding
        final float itemWidth = mIndicatorItemLength + mIndicatorItemPadding;

        if (progress == 0F) {
            // no swipe, draw a normal indicator
            float highlightStart = indicatorStartX + itemWidth * highlightPosition;
            c.drawCircle(highlightStart, indicatorPosY, mIndicatorHeight / 2F, mPaint);
        } else {
            float highlightStart = indicatorStartX + itemWidth * highlightPosition;
            // calculate partial highlight
            float partialLength = mIndicatorItemLength * progress + mIndicatorItemPadding*progress;

            // draw the cut off highlight
            c.drawCircle(highlightStart + partialLength, indicatorPosY, mIndicatorItemLength, mPaint);

        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = mIndicatorHeight;
    }

}
