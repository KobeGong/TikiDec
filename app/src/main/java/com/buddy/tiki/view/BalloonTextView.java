package com.buddy.tiki.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import com.buddy.tiki.C0376R;
import com.geekint.flying.log.Logger;
import org.bytedeco.javacpp.postproc;
import org.bytedeco.javacpp.swresample;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class BalloonTextView extends TextView {
    private static final Logger f2486a = Logger.getInstance(BalloonTextView.class.getSimpleName());
    private Paint f2487b;
    private Path f2488c;
    private int f2489d;
    private int f2490e;
    private float f2491f;
    private float f2492g;
    private float f2493h;
    private float f2494i;
    private int f2495j;
    private int f2496k;
    private float f2497l;
    private float f2498m;
    private boolean f2499n;

    public BalloonTextView(Context context) {
        this(context, null);
    }

    public BalloonTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BalloonTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f2487b = new Paint();
        this.f2487b.setAntiAlias(true);
        TypedArray a = context.obtainStyledAttributes(attrs, C0376R.styleable.BalloonTextView, defStyle, 0);
        this.f2491f = (float) a.getDimensionPixelSize(0, 12);
        this.f2492g = (float) a.getDimensionPixelSize(1, 12);
        this.f2494i = (float) a.getDimensionPixelSize(4, 24);
        this.f2493h = (float) a.getDimensionPixelSize(5, 12);
        this.f2495j = a.getInt(6, 0);
        this.f2489d = a.getColor(3, -16711681);
        this.f2490e = a.getColor(2, this.f2489d);
        this.f2496k = a.getInt(7, 2);
        this.f2499n = a.getBoolean(8, false);
        a.recycle();
    }

    public static Path getRightCenterBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * ry) + tailWidth > height) {
                ry = (height - tailWidth) / 2.0f;
            }
            if (rx > width / 2.0f) {
                rx = width / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            float halfHeightMinusTail = (height - tailWidth) / 2.0f;
            float halfHeightMinusCornersAndTail = (heightMinusCorners - tailWidth) / 2.0f;
            path.moveTo((width - rx) + tailHeight, bottom);
            path.rQuadTo(rx, 0.0f, rx, -ry);
            path.rLineTo(0.0f, -heightMinusCorners);
            path.rQuadTo(0.0f, -ry, -rx, -ry);
            path.rLineTo(-widthMinusCorners, 0.0f);
            if (conformToOriginalPost) {
                path.rLineTo(-rx, 0.0f);
                path.rLineTo(0.0f, halfHeightMinusTail);
                path.rLineTo(-tailHeight, tailWidth / 2.0f);
                path.rLineTo(tailHeight, tailWidth / 2.0f);
                path.rLineTo(0.0f, halfHeightMinusTail);
                path.rLineTo(-rx, 0.0f);
            } else {
                path.rQuadTo(-rx, 0.0f, -rx, ry);
                path.rLineTo(0.0f, halfHeightMinusCornersAndTail);
                path.rLineTo(-tailHeight, tailWidth / 2.0f);
                path.rLineTo(tailHeight, tailWidth / 2.0f);
                path.rLineTo(0.0f, halfHeightMinusCornersAndTail);
                path.rQuadTo(0.0f, ry, rx, ry);
            }
            path.rLineTo(widthMinusCorners, 0.0f);
            path.close();
        }
        return path;
    }

    public static Path getRightStartBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * ry) + tailWidth > height) {
                ry = (height - tailWidth) / 2.0f;
            }
            if (rx > width / 2.0f) {
                rx = width / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            float halfHeightMinusTail = (height - tailWidth) / 2.0f;
            float halfHeightMinusCornersAndTail = (heightMinusCorners - tailWidth) / 2.0f;
            path.moveTo((width - rx) + tailHeight, bottom);
            path.rQuadTo(rx, 0.0f, rx, -ry);
            path.rLineTo(0.0f, -heightMinusCorners);
            path.rQuadTo(0.0f, -ry, -rx, -ry);
            path.rLineTo(-widthMinusCorners, 0.0f);
            if (conformToOriginalPost) {
                path.rLineTo(-rx, 0.0f);
                path.rLineTo(-tailHeight, 0.0f);
                path.rLineTo(tailHeight, tailWidth / 2.0f);
                path.rLineTo(0.0f, halfHeightMinusTail);
                path.rLineTo(0.0f, halfHeightMinusTail);
                path.rLineTo(0.0f, tailWidth / 2.0f);
                path.rLineTo(rx, 0.0f);
            } else {
                path.rLineTo(-rx, 0.0f);
                path.rLineTo(-tailHeight, 0.0f);
                path.rLineTo(tailHeight, tailWidth / 2.0f);
                path.rLineTo(0.0f, halfHeightMinusTail);
                path.rLineTo(0.0f, halfHeightMinusTail);
                path.rQuadTo(0.0f, ry, rx, ry);
            }
            path.rLineTo(widthMinusCorners, 0.0f);
            path.close();
        }
        return path;
    }

    public static Path getRightEndBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * ry) + tailWidth > height) {
                ry = (height - tailWidth) / 2.0f;
            }
            if (rx > width / 2.0f) {
                rx = width / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            float halfHeightMinusTail = (height - tailWidth) / 2.0f;
            float halfHeightMinusCornersAndTail = (heightMinusCorners - tailWidth) / 2.0f;
            path.moveTo((width - rx) + tailHeight, bottom);
            path.rQuadTo(rx, 0.0f, rx, -ry);
            path.rLineTo(0.0f, -heightMinusCorners);
            path.rQuadTo(0.0f, -ry, -rx, -ry);
            path.rLineTo(-widthMinusCorners, 0.0f);
            if (conformToOriginalPost) {
                path.rLineTo(-rx, 0.0f);
                path.rLineTo(0.0f, halfHeightMinusTail);
                path.rLineTo(0.0f, halfHeightMinusTail);
                path.rLineTo(0.0f, tailWidth / 2.0f);
                path.rLineTo(-tailHeight, tailWidth / 2.0f);
                path.rLineTo(tailHeight, 0.0f);
                path.rLineTo(rx, 0.0f);
            } else {
                path.rQuadTo(-rx, 0.0f, -rx, ry);
                path.rLineTo(0.0f, halfHeightMinusCornersAndTail);
                path.rLineTo(0.0f, halfHeightMinusTail);
                path.rLineTo(0.0f, tailWidth / 2.0f);
                path.rLineTo(-tailHeight, tailWidth / 2.0f);
                path.rLineTo(tailHeight, 0.0f);
            }
            path.rLineTo(widthMinusCorners, 0.0f);
            path.close();
        }
        return path;
    }

    public static Path getLeftCenterBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * ry) + tailWidth > height) {
                ry = (height - tailWidth) / 2.0f;
            }
            if (rx > width / 2.0f) {
                rx = width / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            float halfHeightMinusTail = (height - tailWidth) / 2.0f;
            float halfHeightMinusCornersAndTail = (heightMinusCorners - tailWidth) / 2.0f;
            path.moveTo(rx, top);
            path.rQuadTo(-rx, 0.0f, -rx, ry);
            path.rLineTo(0.0f, heightMinusCorners);
            path.rQuadTo(0.0f, ry, rx, ry);
            path.rLineTo(widthMinusCorners, 0.0f);
            if (conformToOriginalPost) {
                path.rLineTo(rx, 0.0f);
                path.rLineTo(0.0f, -halfHeightMinusTail);
                path.rLineTo(tailHeight, (-tailWidth) / 2.0f);
                path.rLineTo(-tailHeight, (-tailWidth) / 2.0f);
                path.rLineTo(0.0f, -halfHeightMinusTail);
                path.rLineTo(-rx, 0.0f);
            } else {
                path.rQuadTo(rx, 0.0f, rx, -ry);
                path.rLineTo(0.0f, -halfHeightMinusCornersAndTail);
                path.rLineTo(tailHeight, (-tailWidth) / 2.0f);
                path.rLineTo(-tailHeight, (-tailWidth) / 2.0f);
                path.rLineTo(0.0f, -halfHeightMinusCornersAndTail);
                path.rQuadTo(0.0f, -ry, -rx, -ry);
            }
            path.rLineTo(-widthMinusCorners, 0.0f);
            path.close();
        }
        return path;
    }

    public static Path getLeftStartBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * ry) + tailWidth > height) {
                ry = (height - tailWidth) / 2.0f;
            }
            if (rx > width / 2.0f) {
                rx = width / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            float halfHeightMinusTail = (height - tailWidth) / 2.0f;
            float halfHeightMinusCornersAndTail = (heightMinusCorners - tailWidth) / 2.0f;
            path.moveTo(rx, top);
            path.rQuadTo(-rx, 0.0f, -rx, ry);
            path.rLineTo(0.0f, heightMinusCorners);
            path.rQuadTo(0.0f, ry, rx, ry);
            path.rLineTo(widthMinusCorners, 0.0f);
            if (conformToOriginalPost) {
                path.rLineTo(rx, 0.0f);
                path.rLineTo(tailHeight, 0.0f);
                path.rLineTo(-tailHeight, (-tailWidth) / 2.0f);
                path.rLineTo(0.0f, -halfHeightMinusTail);
                path.rLineTo(0.0f, -halfHeightMinusTail);
                path.rLineTo(0.0f, (-tailWidth) / 2.0f);
                path.rLineTo(-rx, 0.0f);
            } else {
                path.rLineTo(rx, 0.0f);
                path.rLineTo(tailHeight, 0.0f);
                path.rLineTo(-tailHeight, (-tailWidth) / 2.0f);
                path.rLineTo(0.0f, -halfHeightMinusTail);
                path.rLineTo(0.0f, -halfHeightMinusCornersAndTail);
                path.rLineTo(0.0f, (-tailWidth) / 2.0f);
                path.rQuadTo(0.0f, -ry, -rx, -ry);
            }
            path.rLineTo(-widthMinusCorners, 0.0f);
            path.close();
        }
        return path;
    }

    public static Path getLeftEndBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * ry) + tailWidth > height) {
                ry = (height - tailWidth) / 2.0f;
            }
            if (rx > width / 2.0f) {
                rx = width / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            float halfHeightMinusTail = (height - tailWidth) / 2.0f;
            float halfHeightMinusCornersAndTail = (heightMinusCorners - tailWidth) / 2.0f;
            path.moveTo(rx, top);
            path.rQuadTo(-rx, 0.0f, -rx, ry);
            path.rLineTo(0.0f, heightMinusCorners);
            path.rQuadTo(0.0f, ry, rx, ry);
            path.rLineTo(widthMinusCorners, 0.0f);
            if (conformToOriginalPost) {
                path.rLineTo(rx, 0.0f);
                path.rLineTo(0.0f, -halfHeightMinusTail);
                path.rLineTo(0.0f, (-tailWidth) / 2.0f);
                path.rLineTo(0.0f, -halfHeightMinusTail);
                path.rLineTo(tailHeight, (-tailWidth) / 2.0f);
                path.rLineTo(-tailHeight, 0.0f);
                path.rLineTo(-rx, 0.0f);
            } else {
                path.rQuadTo(rx, 0.0f, rx, -ry);
                path.rLineTo(0.0f, -halfHeightMinusCornersAndTail);
                path.rLineTo(0.0f, (-tailWidth) / 2.0f);
                path.rLineTo(0.0f, -halfHeightMinusTail);
                path.rLineTo(tailHeight, (-tailWidth) / 2.0f);
                path.rLineTo(-tailHeight, 0.0f);
            }
            path.rLineTo(-widthMinusCorners, 0.0f);
            path.close();
        }
        return path;
    }

    public static Path getDownCenterBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * rx) + tailWidth > width) {
                rx = (width - tailWidth) / 2.0f;
            }
            if (ry > height / 2.0f) {
                ry = height / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            path.moveTo(right, (bottom + tailHeight) - ry);
            path.rQuadTo(0.0f, ry, -rx, ry);
            path.rLineTo(-widthMinusCorners, 0.0f);
            path.rQuadTo(-rx, 0.0f, -rx, -ry);
            path.rLineTo(0.0f, -heightMinusCorners);
            if (conformToOriginalPost) {
                path.rLineTo(0.0f, -ry);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, -tailHeight);
                path.rLineTo(tailWidth / 2.0f, tailHeight);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(0.0f, ry);
            } else {
                path.rQuadTo(0.0f, -ry, rx, -ry);
                path.rLineTo(halfWidthMinusCornersAndTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, -tailHeight);
                path.rLineTo(tailWidth / 2.0f, tailHeight);
                path.rLineTo(halfWidthMinusCornersAndTail, 0.0f);
                path.rQuadTo(rx, 0.0f, rx, ry);
            }
            path.rLineTo(0.0f, heightMinusCorners);
            path.close();
        }
        return path;
    }

    public static Path getDownStartBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * rx) + tailWidth > width) {
                rx = (width - tailWidth) / 2.0f;
            }
            if (ry > height / 2.0f) {
                ry = height / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            path.moveTo(right, (bottom + tailHeight) - ry);
            path.rQuadTo(0.0f, ry, -rx, ry);
            path.rLineTo(-widthMinusCorners, 0.0f);
            path.rQuadTo(-rx, 0.0f, -rx, -ry);
            path.rLineTo(0.0f, -heightMinusCorners);
            if (conformToOriginalPost) {
                path.rLineTo(0.0f, -ry);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, 0.0f);
                path.rLineTo(tailWidth / 2.0f, -tailHeight);
                path.rLineTo(0.0f, tailHeight);
                path.rLineTo(0.0f, ry);
            } else {
                path.rQuadTo(0.0f, -ry, rx, -ry);
                path.rLineTo(halfWidthMinusCornersAndTail, 0.0f);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, 0.0f);
                path.rLineTo(tailWidth / 2.0f, -tailHeight);
                path.rLineTo(0.0f, tailHeight);
            }
            path.rLineTo(0.0f, heightMinusCorners);
            path.close();
        }
        return path;
    }

    public static Path getDownEndBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * rx) + tailWidth > width) {
                rx = (width - tailWidth) / 2.0f;
            }
            if (ry > height / 2.0f) {
                ry = height / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            path.moveTo(right, (bottom + tailHeight) - ry);
            path.rQuadTo(0.0f, ry, -rx, ry);
            path.rLineTo(-widthMinusCorners, 0.0f);
            path.rQuadTo(-rx, 0.0f, -rx, -ry);
            path.rLineTo(0.0f, -heightMinusCorners);
            if (conformToOriginalPost) {
                path.rLineTo(0.0f, -ry);
                path.rLineTo(0.0f, -tailHeight);
                path.rLineTo(tailWidth / 2.0f, tailHeight);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, 0.0f);
                path.rLineTo(0.0f, ry);
            } else {
                path.rLineTo(0.0f, -ry);
                path.rLineTo(0.0f, -tailHeight);
                path.rLineTo(tailWidth / 2.0f, tailHeight);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(halfWidthMinusCornersAndTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, 0.0f);
                path.rQuadTo(rx, 0.0f, rx, ry);
            }
            path.rLineTo(0.0f, heightMinusCorners);
            path.close();
        }
        return path;
    }

    public static Path getUpCenterBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * rx) + tailWidth > width) {
                rx = (width - tailWidth) / 2.0f;
            }
            if (ry > height / 2.0f) {
                ry = height / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            path.moveTo(right, top + ry);
            path.rQuadTo(0.0f, -ry, -rx, -ry);
            path.rLineTo(-widthMinusCorners, 0.0f);
            path.rQuadTo(-rx, 0.0f, -rx, ry);
            path.rLineTo(0.0f, heightMinusCorners);
            if (conformToOriginalPost) {
                path.rLineTo(0.0f, ry);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, tailHeight);
                path.rLineTo(tailWidth / 2.0f, -tailHeight);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(0.0f, -ry);
            } else {
                path.rQuadTo(0.0f, ry, rx, ry);
                path.rLineTo(halfWidthMinusCornersAndTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, tailHeight);
                path.rLineTo(tailWidth / 2.0f, -tailHeight);
                path.rLineTo(halfWidthMinusCornersAndTail, 0.0f);
                path.rQuadTo(rx, 0.0f, rx, -ry);
            }
            path.rLineTo(0.0f, -heightMinusCorners);
            path.close();
        }
        return path;
    }

    public static Path getUpStartBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * rx) + tailWidth > width) {
                rx = (width - tailWidth) / 2.0f;
            }
            if (ry > height / 2.0f) {
                ry = height / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            path.moveTo(right, top + ry);
            path.rQuadTo(0.0f, -ry, -rx, -ry);
            path.rLineTo(-widthMinusCorners, 0.0f);
            path.rQuadTo(-rx, 0.0f, -rx, ry);
            path.rLineTo(0.0f, heightMinusCorners);
            if (conformToOriginalPost) {
                path.rLineTo(0.0f, ry);
                path.rLineTo(0.0f, tailHeight);
                path.rLineTo(tailWidth / 2.0f, -tailHeight);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, 0.0f);
                path.rLineTo(0.0f, -ry);
            } else {
                path.rLineTo(0.0f, ry);
                path.rLineTo(0.0f, tailHeight);
                path.rLineTo(tailWidth / 2.0f, -tailHeight);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(halfWidthMinusCornersAndTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, 0.0f);
                path.rQuadTo(rx, 0.0f, rx, -ry);
            }
            path.rLineTo(0.0f, -heightMinusCorners);
            path.close();
        }
        return path;
    }

    public static Path getUpEndBalloon(float left, float top, float right, float bottom, float rx, float ry, float tailHeight, float tailWidth, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (width > 0.0f && height > 0.0f) {
            if (tailWidth > width) {
                tailWidth = width;
            }
            if ((2.0f * rx) + tailWidth > width) {
                rx = (width - tailWidth) / 2.0f;
            }
            if (ry > height / 2.0f) {
                ry = height / 2.0f;
            }
            float widthMinusCorners = width - (2.0f * rx);
            float heightMinusCorners = height - (2.0f * ry);
            float halfWidthMinusCornersAndTail = (widthMinusCorners - tailWidth) / 2.0f;
            float halfWidthMinusTail = (width - tailWidth) / 2.0f;
            path.moveTo(right, top + ry);
            path.rQuadTo(0.0f, -ry, -rx, -ry);
            path.rLineTo(-widthMinusCorners, 0.0f);
            path.rQuadTo(-rx, 0.0f, -rx, ry);
            path.rLineTo(0.0f, heightMinusCorners);
            if (conformToOriginalPost) {
                path.rLineTo(0.0f, ry);
                path.rLineTo(tailWidth / 2.0f, 0.0f);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, tailHeight);
                path.rLineTo(0.0f, -tailHeight);
                path.rLineTo(0.0f, -ry);
            } else {
                path.rQuadTo(0.0f, ry, rx, ry);
                path.rLineTo(tailWidth / 2.0f, 0.0f);
                path.rLineTo(halfWidthMinusCornersAndTail, 0.0f);
                path.rLineTo(halfWidthMinusTail, 0.0f);
                path.rLineTo(tailWidth / 2.0f, tailHeight);
                path.rLineTo(0.0f, -tailHeight);
            }
            path.rLineTo(0.0f, -heightMinusCorners);
            path.close();
        }
        return path;
    }

    public static Path getRoundedRect(float left, float top, float right, float bottom, float rx, float ry, boolean conformToOriginalPost) {
        Path path = new Path();
        if (rx < 0.0f) {
            rx = 0.0f;
        }
        if (ry < 0.0f) {
            ry = 0.0f;
        }
        float width = right - left;
        float height = bottom - top;
        if (rx > width / 2.0f) {
            rx = width / 2.0f;
        }
        if (ry > height / 2.0f) {
            ry = height / 2.0f;
        }
        float widthMinusCorners = width - (2.0f * rx);
        float heightMinusCorners = height - (2.0f * ry);
        path.moveTo(right, top + ry);
        path.rQuadTo(0.0f, -ry, -rx, -ry);
        path.rLineTo(-widthMinusCorners, 0.0f);
        path.rQuadTo(-rx, 0.0f, -rx, ry);
        path.rLineTo(0.0f, heightMinusCorners);
        if (conformToOriginalPost) {
            path.rLineTo(0.0f, ry);
            path.rLineTo(width, 0.0f);
            path.rLineTo(0.0f, -ry);
        } else {
            path.rQuadTo(0.0f, ry, rx, ry);
            path.rLineTo(widthMinusCorners, 0.0f);
            path.rQuadTo(rx, 0.0f, rx, -ry);
        }
        path.rLineTo(0.0f, -heightMinusCorners);
        path.close();
        return path;
    }

    protected void onDraw(Canvas canvas) {
        float rectW = 0.0f;
        float rectH = 0.0f;
        switch (this.f2495j) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                rectW = this.f2497l;
                rectH = this.f2498m - this.f2493h;
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                rectW = this.f2497l - this.f2493h;
                rectH = this.f2498m;
                break;
        }
        switch (this.f2495j) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                if (this.f2496k != 1) {
                    if (this.f2496k != 3) {
                        this.f2488c = getUpCenterBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                        break;
                    } else {
                        this.f2488c = getUpEndBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                        break;
                    }
                }
                this.f2488c = getUpStartBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                break;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                if (this.f2496k != 1) {
                    if (this.f2496k != 3) {
                        this.f2488c = getDownCenterBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                        break;
                    } else {
                        this.f2488c = getDownEndBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                        break;
                    }
                }
                this.f2488c = getDownStartBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                if (this.f2496k != 1) {
                    if (this.f2496k != 3) {
                        this.f2488c = getLeftCenterBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                        break;
                    } else {
                        this.f2488c = getLeftEndBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                        break;
                    }
                }
                this.f2488c = getLeftStartBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                if (this.f2496k != 1) {
                    if (this.f2496k != 3) {
                        this.f2488c = getRightCenterBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                        break;
                    } else {
                        this.f2488c = getRightEndBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                        break;
                    }
                }
                this.f2488c = getRightStartBalloon(0.0f, 0.0f, rectW, rectH, this.f2491f, this.f2492g, this.f2493h, this.f2494i, this.f2499n);
                break;
        }
        this.f2487b.setColor(this.f2489d);
        canvas.drawPath(this.f2488c, this.f2487b);
        this.f2487b.setColor(this.f2490e);
        float textBgX = 0.0f;
        float textBgY = 0.0f;
        switch (this.f2495j) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                textBgX = this.f2491f;
                textBgY = this.f2492g;
                break;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                textBgX = this.f2491f;
                textBgY = this.f2492g + this.f2493h;
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                textBgX = this.f2491f;
                textBgY = this.f2492g;
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                textBgX = this.f2491f + this.f2493h;
                textBgY = this.f2492g;
                break;
        }
        canvas.drawRect(textBgX, textBgY, (textBgX + rectW) - (this.f2491f * 2.0f), (textBgY + rectH) - (this.f2492g * 2.0f), this.f2487b);
        this.f2487b.setColor(getCurrentTextColor());
        this.f2487b.setTextSize(getTextSize());
        String text = getText().toString();
        float textWidth = this.f2487b.measureText(text);
        FontMetrics fontMetrics = this.f2487b.getFontMetrics();
        float textHeight = fontMetrics.descent - fontMetrics.ascent;
        float textX = 0.0f;
        float textY = 0.0f;
        switch (this.f2495j) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                textX = this.f2491f;
                textY = this.f2492g;
                break;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                textX = this.f2491f;
                textY = this.f2492g + this.f2493h;
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                textX = this.f2491f + this.f2493h;
                textY = this.f2492g;
                break;
        }
        Rect textRect = new Rect();
        Gravity.apply(getGravity(), (int) textWidth, (int) textHeight, new Rect(0, 0, (int) (((rectW - (this.f2491f * 2.0f)) - ((float) getPaddingLeft())) - ((float) getPaddingRight())), (int) (((rectH - (this.f2492g * 2.0f)) - ((float) getPaddingTop())) - ((float) getPaddingBottom()))), textRect);
        canvas.drawText(text, textX + ((float) (textRect.left + getPaddingLeft())), textY + (((float) (textRect.top + getPaddingTop())) - fontMetrics.ascent), this.f2487b);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(1080, postproc.PP_CPU_CAPS_MMX), MeasureSpec.makeMeasureSpec(1920, postproc.PP_CPU_CAPS_MMX));
        int nW = getMeasuredWidth();
        int nH = getMeasuredHeight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (MeasureSpec.getMode(widthMeasureSpec) != postproc.PP_CPU_CAPS_3DNOW || MeasureSpec.getMode(heightMeasureSpec) != postproc.PP_CPU_CAPS_3DNOW) {
            if (MeasureSpec.getMode(widthMeasureSpec) != postproc.PP_CPU_CAPS_3DNOW) {
                if (MeasureSpec.getMode(heightMeasureSpec) != postproc.PP_CPU_CAPS_3DNOW) {
                    switch (this.f2495j) {
                        case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                        case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                            this.f2497l = ((float) width) + (this.f2491f * 2.0f);
                            this.f2498m = (((float) height) + (this.f2492g * 2.0f)) + this.f2493h;
                            break;
                        case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                        case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                            this.f2497l = (((float) width) + (this.f2491f * 2.0f)) + this.f2493h;
                            this.f2498m = ((float) height) + (this.f2492g * 2.0f);
                            break;
                        default:
                            break;
                    }
                }
                switch (this.f2495j) {
                    case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                    case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                        this.f2497l = ((float) width) + (this.f2491f * 2.0f);
                        this.f2498m = (float) height;
                        break;
                    case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                        this.f2497l = (((float) width) + (this.f2491f * 2.0f)) + this.f2493h;
                        this.f2498m = (float) height;
                        break;
                    default:
                        break;
                }
            }
            switch (this.f2495j) {
                case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    this.f2497l = (float) width;
                    this.f2498m = (((float) height) + (this.f2492g * 2.0f)) + this.f2493h;
                    break;
                case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                    this.f2497l = (float) width;
                    this.f2498m = ((float) height) + (this.f2492g * 2.0f);
                    break;
                default:
                    break;
            }
        }
        float f;
        switch (this.f2495j) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                this.f2497l = (float) width;
                this.f2498m = (float) height;
                if ((this.f2491f * 2.0f) + ((float) nW) > ((float) width)) {
                    this.f2491f = Math.max(0.0f, ((float) (width - nW)) / 2.0f);
                }
                if (((this.f2492g * 2.0f) + ((float) nH)) + this.f2493h > ((float) height)) {
                    f = ((float) (height - nH)) / 3.0f;
                    this.f2493h = f;
                    this.f2492g = Math.max(0.0f, f);
                    break;
                }
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                this.f2497l = (float) width;
                this.f2498m = (float) height;
                if ((this.f2492g * 2.0f) + ((float) nH) > ((float) height)) {
                    this.f2492g = Math.max(0.0f, ((float) (height - nH)) / 2.0f);
                }
                if (((this.f2491f * 2.0f) + ((float) nW)) + this.f2493h > ((float) width)) {
                    f = ((float) (width - nW)) / 3.0f;
                    this.f2493h = f;
                    this.f2491f = Math.max(0.0f, f);
                    break;
                }
                break;
        }
        setMeasuredDimension((int) this.f2497l, (int) this.f2498m);
    }
}
