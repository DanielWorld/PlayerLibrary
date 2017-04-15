package com.danielpark.player.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.danielpark.player.util.DeviceUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>A {@link FrameLayout} that resizes itself to match a specified aspect ratio.</p>
 * Improved class from {@link com.google.android.exoplayer2.ui.AspectRatioFrameLayout}
 * <br><br>
 * Copyright (c) 2014-2017 op7773hons@gmail.com
 * Created by Daniel Park on 2017-04-15.
 */

public final class NewAspectRatioFrameLayout extends FrameLayout{

    /**
     * Resize modes for {@link NewAspectRatioFrameLayout}.
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RESIZE_MODE_FIT, RESIZE_MODE_FIXED_WIDTH, RESIZE_MODE_FIXED_HEIGHT, RESIZE_MODE_FILL, RESIZE_MODE_FIT_TO_SCREEN})
    public @interface ResizeMode {}

    /**
     * Either the width or height is decreased to obtain the desired aspect ratio.
     */
    public static final int RESIZE_MODE_FIT = 0;
    /**
     * The width is fixed and the height is increased or decreased to obtain the desired aspect ratio.
     */
    public static final int RESIZE_MODE_FIXED_WIDTH = 1;
    /**
     * The height is fixed and the width is increased or decreased to obtain the desired aspect ratio.
     */
    public static final int RESIZE_MODE_FIXED_HEIGHT = 2;
    /**
     * The specified aspect ratio is ignored.
     */
    public static final int RESIZE_MODE_FILL = 3;
    /**
     * The size is fit to screen forcefully while maintaining aspect ratio
     */
    public static final int RESIZE_MODE_FIT_TO_SCREEN = 4;

    /**
     * The {@link FrameLayout} will not resize itself if the fractional difference between its natural
     * aspect ratio and the requested aspect ratio falls below this threshold.
     * <p>
     * This tolerance allows the view to occupy the whole of the screen when the requested aspect
     * ratio is very close, but not exactly equal to, the aspect ratio of the screen. This may reduce
     * the number of view layers that need to be composited by the underlying system, which can help
     * to reduce power consumption.
     */
    private static final float MAX_ASPECT_RATIO_DEFORMATION_FRACTION = 0.01f;

    private float videoAspectRatio;
    private int resizeMode;

    public NewAspectRatioFrameLayout(Context context) {
        this(context, null);
    }

    public NewAspectRatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        resizeMode = RESIZE_MODE_FIT;
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                    com.danielpark.player.R.styleable.NewAspectRatioFrameLayout, 0, 0);
            try {
                resizeMode = a.getInt(com.danielpark.player.R.styleable.NewAspectRatioFrameLayout_resize_mode, RESIZE_MODE_FIT);
            } finally {
                a.recycle();
            }
        }
    }

    /**
     * Set the aspect ratio that this view should satisfy.
     *
     * @param widthHeightRatio The width to height ratio.
     */
    public void setAspectRatio(float widthHeightRatio) {
        if (this.videoAspectRatio != widthHeightRatio) {
            this.videoAspectRatio = widthHeightRatio;
            requestLayout();
        }
    }

    /**
     * Sets the resize mode.
     *
     * @param resizeMode The resize mode.
     */
    public void setResizeMode(@ResizeMode int resizeMode) {
        if (this.resizeMode != resizeMode) {
            this.resizeMode = resizeMode;
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (resizeMode == RESIZE_MODE_FILL || videoAspectRatio <= 0) {
            // Aspect ratio not set.
            return;
        }

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        float viewAspectRatio = (float) width / height;
        float aspectDeformation = videoAspectRatio / viewAspectRatio - 1;
        if (Math.abs(aspectDeformation) <= MAX_ASPECT_RATIO_DEFORMATION_FRACTION) {
            // We're within the allowed tolerance.
            return;
        }

        switch (resizeMode) {
            case RESIZE_MODE_FIXED_WIDTH:
                height = (int) (width / videoAspectRatio);
                break;
            case RESIZE_MODE_FIXED_HEIGHT:
                width = (int) (height * videoAspectRatio);
                break;
            case RESIZE_MODE_FIT_TO_SCREEN:
                width = DeviceUtil.getResolutionWidth(getContext());
                height = DeviceUtil.getResolutionHeight(getContext());

                viewAspectRatio = (float) width / height;

                float screenAspectDeformation =
                        videoAspectRatio / viewAspectRatio - 1;

                if (screenAspectDeformation > 0) {
                    height = (int) (width / videoAspectRatio);
                } else {
                    width = (int) (height * videoAspectRatio);
                }
                break;
            default:
                if (aspectDeformation > 0) {
                    height = (int) (width / videoAspectRatio);
                } else {
                    width = (int) (height * videoAspectRatio);
                }
                break;
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }
}
