package com.buddy.tiki.view.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.view.loading.base.LoadingRenderer;
import org.bytedeco.javacpp.avutil;

public class CircleBroodLoadingRenderer extends LoadingRenderer {
    private final RectF f2934A = new RectF();
    private final Path f2935B = new Path();
    private final Path f2936C = new Path();
    private final Path f2937D = new Path();
    private final float[] f2938E = new float[2];
    private final float[] f2939F = new float[2];
    private final PathMeasure f2940G = new PathMeasure();
    private final PathMeasure f2941H = new PathMeasure();
    private float f2942I = 0.34f;
    private float f2943J = 0.5f;
    private float f2944K = 0.65f;
    private float f2945L = 0.833f;
    private float f2946M = 0.1f;
    private float f2947N = 0.26f;
    private float f2948O = 0.34f;
    private float f2949P = 0.42f;
    private float f2950Q = 0.5f;
    private float f2951R = 0.7f;
    private float f2952S = 0.9f;
    private float f2953T;
    private float f2954U;
    private float f2955V;
    private float f2956W;
    private float f2957X;
    private float f2958Y;
    private float f2959Z;
    private float aa;
    private float ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private int ag;
    private int ah;
    private int ai;
    private int aj;
    private int ak;
    private float al;
    private float am;
    private float an;
    private float ao;
    private float ap;
    private float aq;
    private float ar;
    private float as;
    private float at;
    private float au;
    private final Interpolator f2960e = new MotherMoveInterpolator();
    private final Interpolator f2961f = new ChildMoveInterpolator();
    private final Interpolator f2962g = new AccelerateInterpolator(0.3f);
    private final Interpolator f2963h = new AccelerateInterpolator(0.5f);
    private final Interpolator f2964i = new AccelerateInterpolator(0.8f);
    private final Interpolator f2965j = new AccelerateInterpolator(1.0f);
    private final Interpolator f2966k = new DecelerateInterpolator(0.3f);
    private final Interpolator f2967l = new DecelerateInterpolator(0.5f);
    private final Interpolator f2968m = new DecelerateInterpolator(0.8f);
    private final Interpolator f2969n = new DecelerateInterpolator(1.0f);
    private final float f2970o = 0.55152f;
    private final float f2971p = 200.0f;
    private final float f2972q = 150.0f;
    private final float f2973r = 19.0f;
    private final float f2974s = 5.0f;
    private final float f2975t = 0.8452f;
    private final int f2976u = Color.parseColor("#FFFFF532");
    private final int f2977v = Color.parseColor("#FFF0E628");
    private final int f2978w = Color.parseColor("#00000000");
    private final int f2979x = Color.parseColor("#00000000");
    private final long f2980y = 4111;
    private final Paint f2981z = new Paint();

    private class ChildMoveInterpolator implements Interpolator {
        final /* synthetic */ CircleBroodLoadingRenderer f2925a;

        private ChildMoveInterpolator(CircleBroodLoadingRenderer circleBroodLoadingRenderer) {
            this.f2925a = circleBroodLoadingRenderer;
        }

        public float getInterpolation(float input) {
            if (input < this.f2925a.f2946M) {
                return 0.0f;
            }
            if (input <= this.f2925a.f2947N) {
                return this.f2925a.f2969n.getInterpolation((input - 0.1f) * 6.25f) / 3.846f;
            }
            if (input <= this.f2925a.f2948O) {
                return 0.26f + (this.f2925a.f2965j.getInterpolation((input - 0.26f) * 12.5f) / 12.5f);
            }
            if (input <= this.f2925a.f2949P) {
                return 0.34f + (this.f2925a.f2968m.getInterpolation((input - 0.34f) * 12.5f) / 12.5f);
            }
            if (input <= this.f2925a.f2950Q) {
                return 0.42f + (this.f2925a.f2964i.getInterpolation((input - 0.42f) * 12.5f) / 12.5f);
            }
            if (input <= this.f2925a.f2951R) {
                return 0.5f + (this.f2925a.f2967l.getInterpolation((input - 0.5f) * 5.0f) / 5.0f);
            }
            if (input <= this.f2925a.f2952S) {
                return 0.7f + (this.f2925a.f2963h.getInterpolation((input - 0.7f) * 5.0f) / 3.33f);
            }
            return 1.0f;
        }
    }

    private class MotherMoveInterpolator implements Interpolator {
        final /* synthetic */ CircleBroodLoadingRenderer f2926a;

        private MotherMoveInterpolator(CircleBroodLoadingRenderer circleBroodLoadingRenderer) {
            this.f2926a = circleBroodLoadingRenderer;
        }

        public float getInterpolation(float input) {
            if (input <= this.f2926a.f2942I) {
                return this.f2926a.f2965j.getInterpolation(input * 2.941f) / 2.941f;
            }
            if (input <= this.f2926a.f2943J) {
                return 0.34f + (this.f2926a.f2969n.getInterpolation((input - 0.34f) * 6.25f) / 6.25f);
            }
            if (input <= this.f2926a.f2944K) {
                return 0.5f + (this.f2926a.f2962g.getInterpolation((input - 0.5f) * 6.666f) / 4.0f);
            }
            if (input <= this.f2926a.f2945L) {
                return 0.75f + (this.f2926a.f2966k.getInterpolation((input - 0.65f) * 5.46f) / 4.0f);
            }
            return 1.0f;
        }
    }

    public CircleBroodLoadingRenderer(Context context) {
        super(context);
        m1836a(context);
        m1845e();
    }

    private void m1836a(Context context) {
        this.c = (float) DisplayUtil.dip2px(200.0f);
        this.d = (float) DisplayUtil.dip2px(150.0f);
        this.f2955V = (float) DisplayUtil.dip2px(19.0f);
        this.f2954U = (float) DisplayUtil.dip2px(5.0f);
        this.ac = this.f2976u;
        this.ad = this.f2977v;
        this.ae = this.f2978w;
        this.af = this.f2979x;
        this.f2956W = this.f2955V;
        this.f2957X = this.f2955V;
        this.aj = (int) ((Math.sqrt((double) ((this.c * this.c) + (this.d * this.d))) / 2.0d) + 1.0d);
        this.b = 4111;
    }

    private void m1845e() {
        this.f2981z.setAntiAlias(true);
        this.f2981z.setStyle(Style.FILL);
        this.f2981z.setStrokeWidth(1.0f);
    }

    protected void mo2238a(Canvas canvas, Rect bounds) {
        int saveCount = canvas.save();
        RectF arcBounds = this.f2934A;
        arcBounds.set(bounds);
        canvas.drawColor(this.ah);
        if (this.ai > 0) {
            this.f2981z.setColor(this.ah == this.ae ? this.af : this.ae);
            canvas.drawCircle(arcBounds.centerX(), arcBounds.centerY(), (float) this.ai, this.f2981z);
        }
        this.f2981z.setColor(this.ag);
        int motherSaveCount = canvas.save();
        canvas.rotate((float) this.ak, this.f2938E[0], this.f2938E[1]);
        canvas.drawPath(m1847f(), this.f2981z);
        canvas.drawPath(m1853h(), this.f2981z);
        canvas.restoreToCount(motherSaveCount);
        int childSaveCount = canvas.save();
        canvas.rotate((float) this.ak, this.f2939F[0], this.f2939F[1]);
        canvas.drawPath(m1851g(), this.f2981z);
        canvas.restoreToCount(childSaveCount);
        canvas.restoreToCount(saveCount);
    }

    private Path m1847f() {
        this.f2935B.reset();
        this.f2935B.addOval(new RectF(this.f2938E[0] - this.f2956W, this.f2938E[1] - this.f2957X, this.f2938E[0] + this.f2956W, this.f2938E[1] + this.f2957X), Direction.CW);
        return this.f2935B;
    }

    private Path m1851g() {
        float bezierOffset = this.f2953T * 0.55152f;
        Path path = new Path();
        path.moveTo(this.f2939F[0], this.f2939F[1] - this.f2953T);
        path.cubicTo((this.f2939F[0] - bezierOffset) - this.f2958Y, this.f2939F[1] - this.f2953T, (this.f2939F[0] - this.f2953T) - this.f2958Y, (this.f2939F[1] - bezierOffset) + this.f2959Z, (this.f2939F[0] - this.f2953T) - this.f2958Y, this.f2939F[1]);
        path.cubicTo((this.f2939F[0] - this.f2953T) - this.f2958Y, (this.f2939F[1] + bezierOffset) - this.f2959Z, (this.f2939F[0] - bezierOffset) - this.f2958Y, this.f2939F[1] + this.f2953T, this.f2939F[0], this.f2939F[1] + this.f2953T);
        path.cubicTo((this.f2939F[0] + bezierOffset) + this.aa, this.f2939F[1] + this.f2953T, (this.f2939F[0] + this.f2953T) + this.aa, (this.f2939F[1] + bezierOffset) - this.ab, (this.f2939F[0] + this.f2953T) + this.aa, this.f2939F[1]);
        path.cubicTo((this.f2939F[0] + this.f2953T) + this.aa, (this.f2939F[1] - bezierOffset) + this.ab, (this.f2939F[0] + bezierOffset) + this.aa, this.f2939F[1] - this.f2953T, this.f2939F[0], this.f2939F[1] - this.f2953T);
        return path;
    }

    private Path m1853h() {
        Path path = new Path();
        float bezierOffset = this.f2956W * 0.55152f;
        float distance = (float) Math.sqrt(Math.pow((double) (this.f2938E[0] - this.f2939F[0]), 2.0d) + Math.pow((double) (this.f2938E[1] - this.f2939F[1]), 2.0d));
        if (distance <= this.f2956W + (this.f2953T * 1.2f) && distance >= this.f2956W - (this.f2953T * 1.2f)) {
            float mMotherOvalOffsetY = this.f2957X - (((this.f2957X - this.f2953T) * ((distance - (this.f2956W - (this.f2953T * 1.2f))) / ((2.0f * this.f2953T) * 1.2f))) * 0.85f);
            this.f2935B.addOval(new RectF(this.f2938E[0] - this.f2956W, this.f2938E[1] - mMotherOvalOffsetY, this.f2938E[0] + this.f2956W, this.f2938E[1] + mMotherOvalOffsetY), Direction.CW);
            float mMotherXOffset = (distance - this.f2956W) + this.f2953T;
            float distanceUltraLeft = (float) Math.sqrt(Math.pow((double) ((this.f2938E[0] - this.f2956W) - this.f2939F[0]), 2.0d) + Math.pow((double) (this.f2938E[1] - this.f2939F[1]), 2.0d));
            float distanceUltraRight = (float) Math.sqrt(Math.pow((double) ((this.f2938E[0] + this.f2956W) - this.f2939F[0]), 2.0d) + Math.pow((double) (this.f2938E[1] - this.f2939F[1]), 2.0d));
            path.moveTo(this.f2938E[0], this.f2938E[1] + mMotherOvalOffsetY);
            if (distanceUltraRight < distanceUltraLeft) {
                path.cubicTo((this.f2938E[0] + bezierOffset) + mMotherXOffset, this.f2938E[1] + mMotherOvalOffsetY, (this.f2938E[0] + distance) + this.f2953T, this.f2938E[1] + (this.f2953T * 1.5f), (this.f2938E[0] + distance) + this.f2953T, this.f2938E[1]);
                path.cubicTo((this.f2938E[0] + distance) + this.f2953T, this.f2938E[1] - (this.f2953T * 1.5f), (this.f2938E[0] + bezierOffset) + mMotherXOffset, this.f2938E[1] - mMotherOvalOffsetY, this.f2938E[0], this.f2938E[1] - mMotherOvalOffsetY);
            } else {
                path.cubicTo((this.f2938E[0] - bezierOffset) - mMotherXOffset, this.f2938E[1] + mMotherOvalOffsetY, (this.f2938E[0] - distance) - this.f2953T, this.f2938E[1] + (this.f2953T * 1.5f), (this.f2938E[0] - distance) - this.f2953T, this.f2938E[1]);
                path.cubicTo((this.f2938E[0] - distance) - this.f2953T, this.f2938E[1] - (this.f2953T * 1.5f), (this.f2938E[0] - bezierOffset) - mMotherXOffset, this.f2938E[1] - mMotherOvalOffsetY, this.f2938E[0], this.f2938E[1] - mMotherOvalOffsetY);
            }
            path.lineTo(this.f2938E[0], this.f2938E[1] + mMotherOvalOffsetY);
        }
        return path;
    }

    protected void mo2236a(float renderProgress) {
        if (!this.f2934A.isEmpty()) {
            if (this.f2936C.isEmpty()) {
                this.f2936C.set(m1856i());
                this.f2940G.setPath(this.f2936C, false);
                this.f2937D.set(m1858j());
                this.f2941H.setPath(this.f2937D, false);
            }
            float motherMoveProgress = this.f2960e.getInterpolation(renderProgress);
            this.f2940G.getPosTan(m1841d(motherMoveProgress), this.f2938E, null);
            this.f2956W = this.f2955V;
            this.f2957X = this.f2955V * m1839c(motherMoveProgress);
            float childMoveProgress = this.f2961f.getInterpolation(renderProgress);
            this.f2941H.getPosTan(m1843e(childMoveProgress), this.f2939F, null);
            m1838b(childMoveProgress);
            this.ak = (int) Math.toDegrees(Math.atan((double) ((this.f2938E[1] - this.f2939F[1]) / (this.f2938E[0] - this.f2939F[0]))));
            this.ai = m1846f(renderProgress);
            this.ag = m1852h(renderProgress);
            this.ah = m1850g(renderProgress);
        }
    }

    private void m1838b(float input) {
        this.f2953T = this.f2954U;
        this.aa = 0.0f;
        this.f2958Y = 0.0f;
        if (input <= this.f2947N) {
            if (((double) input) >= 0.25d) {
                this.f2958Y = ((1.0f - ((input - 0.25f) / 0.01f)) * this.f2953T) * 0.25f;
            } else {
                this.f2958Y = this.f2953T * 0.25f;
            }
        } else if (input <= this.f2948O) {
            if (input > 0.275f && input < 0.285f) {
                this.f2958Y = (this.f2953T * ((input - 0.275f) / 0.01f)) * 0.25f;
            } else if (input > 0.285f) {
                this.f2958Y = this.f2953T * 0.25f;
            }
        } else if (input <= this.f2949P) {
            if (input > 0.38f) {
                this.f2953T = this.f2954U * (1.0f + ((input - 0.38f) / 0.04f));
            }
        } else if (input <= this.f2950Q) {
            if (input < 0.46f) {
                this.f2953T = this.f2954U * (2.0f - ((input - 0.42f) / 0.04f));
            }
        } else if (input <= this.f2951R) {
            if (input > 0.65f) {
                this.f2953T = this.f2954U * (1.0f + ((input - 0.65f) / 0.05f));
            }
        } else if (input <= this.f2952S) {
            if (input < 0.71f) {
                this.f2953T = this.f2954U * 2.0f;
            } else if (input < 0.76f) {
                this.f2953T = this.f2954U * (2.0f - ((input - 0.71f) / 0.05f));
            }
        }
        this.ab = this.aa / 2.5f;
        this.f2959Z = this.f2958Y / 2.5f;
    }

    private float m1839c(float input) {
        float shapeProgress;
        if (input <= this.f2942I) {
            shapeProgress = input / this.f2942I;
        } else if (input <= this.f2943J) {
            shapeProgress = (input - this.f2942I) / (this.f2943J - this.f2942I);
        } else if (input <= this.f2944K) {
            shapeProgress = (input - this.f2943J) / (this.f2944K - this.f2943J);
        } else if (input <= this.f2945L) {
            shapeProgress = (input - this.f2944K) / (this.f2945L - this.f2944K);
        } else {
            shapeProgress = 1.0f;
        }
        if (shapeProgress < 0.5f) {
            return 1.0f - ((0.1548f * shapeProgress) * 2.0f);
        }
        return 0.8452f + (((shapeProgress - 0.5f) * 0.1548f) * 2.0f);
    }

    private float m1841d(float input) {
        float currentStartDistance = 0.0f;
        float currentStageDistance = 0.0f;
        float currentStateStartProgress = 0.0f;
        float currentStateEndProgress = 0.0f;
        if (input > 0.0f) {
            currentStartDistance = 0.0f + 0.0f;
            currentStageDistance = this.al;
            currentStateStartProgress = 0.0f;
            currentStateEndProgress = this.f2942I;
        }
        if (input > this.f2942I) {
            currentStartDistance += currentStageDistance;
            currentStageDistance = this.am;
            currentStateStartProgress = this.f2942I;
            currentStateEndProgress = this.f2943J;
        }
        if (input > this.f2943J) {
            currentStartDistance += currentStageDistance;
            currentStageDistance = this.an;
            currentStateStartProgress = this.f2943J;
            currentStateEndProgress = this.f2944K;
        }
        if (input > this.f2944K) {
            currentStartDistance += currentStageDistance;
            currentStageDistance = this.ao;
            currentStateStartProgress = this.f2944K;
            currentStateEndProgress = this.f2945L;
        }
        if (input > this.f2945L) {
            return currentStartDistance + currentStageDistance;
        }
        return (((input - currentStateStartProgress) / (currentStateEndProgress - currentStateStartProgress)) * currentStageDistance) + currentStartDistance;
    }

    private float m1843e(float input) {
        float currentStartDistance = 0.0f;
        float currentStageDistance = 0.0f;
        float currentStateStartProgress = 0.0f;
        float currentStateEndProgress = 0.0f;
        if (input > 0.0f) {
            currentStartDistance = 0.0f + 0.0f;
            currentStageDistance = this.ap;
            currentStateStartProgress = 0.0f;
            currentStateEndProgress = this.f2947N;
        }
        if (input > this.f2947N) {
            currentStartDistance += currentStageDistance;
            currentStageDistance = this.aq;
            currentStateStartProgress = this.f2947N;
            currentStateEndProgress = this.f2948O;
        }
        if (input > this.f2948O) {
            currentStartDistance += currentStageDistance;
            currentStageDistance = this.ar;
            currentStateStartProgress = this.f2948O;
            currentStateEndProgress = this.f2949P;
        }
        if (input > this.f2949P) {
            currentStartDistance += currentStageDistance;
            currentStageDistance = this.as;
            currentStateStartProgress = this.f2949P;
            currentStateEndProgress = this.f2950Q;
        }
        if (input > this.f2950Q) {
            currentStartDistance += currentStageDistance;
            currentStageDistance = this.at;
            currentStateStartProgress = this.f2950Q;
            currentStateEndProgress = this.f2951R;
        }
        if (input > this.f2951R) {
            currentStartDistance += currentStageDistance;
            currentStageDistance = this.au;
            currentStateStartProgress = this.f2951R;
            currentStateEndProgress = this.f2952S;
        }
        if (input > this.f2952S) {
            return currentStartDistance + currentStageDistance;
        }
        return (((input - currentStateStartProgress) / (currentStateEndProgress - currentStateStartProgress)) * currentStageDistance) + currentStartDistance;
    }

    private Path m1856i() {
        Path path = new Path();
        float centerX = this.f2934A.centerX();
        float centerY = this.f2934A.centerY();
        path.moveTo(centerX, centerY);
        path.quadTo(centerX - (this.f2956W * 2.0f), centerY, centerX - (this.f2956W * 2.0f), centerY - this.f2957X);
        this.al = m1833a(path, 0.0f);
        float currentPathLength = 0.0f + this.al;
        path.quadTo(centerX - (this.f2956W * 1.0f), centerY - this.f2957X, centerX, centerY);
        this.am = m1833a(path, currentPathLength);
        currentPathLength += this.am;
        path.quadTo(centerX, this.f2957X + centerY, centerX - (this.f2956W / 2.0f), (this.f2957X * 1.1f) + centerY);
        this.an = m1833a(path, currentPathLength);
        currentPathLength += this.an;
        path.quadTo(centerX - (this.f2956W / 2.0f), (this.f2957X * 0.6f) + centerY, centerX, centerY);
        this.ao = m1833a(path, currentPathLength);
        return path;
    }

    private Path m1858j() {
        Path path = new Path();
        float centerX = this.f2934A.centerX();
        float centerY = this.f2934A.centerY();
        path.moveTo(centerX, centerY);
        path.lineTo((this.f2956W * 0.75f) + centerX, centerY);
        this.ap = m1833a(path, 0.0f);
        float currentPathLength = 0.0f + this.ap;
        path.quadTo(centerX - (this.f2956W * 0.5f), centerY, centerX - (this.f2956W * 2.0f), centerY - this.f2957X);
        this.aq = m1833a(path, currentPathLength);
        currentPathLength += this.aq;
        path.lineTo((centerX - (this.f2956W * 2.0f)) + (this.f2956W * 0.2f), centerY - this.f2957X);
        path.quadTo(centerX - (this.f2956W * 2.5f), centerY - (this.f2957X * 2.0f), centerX - (this.f2956W * 1.5f), centerY - (this.f2957X * 2.25f));
        this.ar = m1833a(path, currentPathLength);
        currentPathLength += this.ar;
        path.quadTo(centerX - (this.f2956W * 0.2f), centerY - (this.f2957X * 2.25f), centerX, centerY);
        this.as = m1833a(path, currentPathLength);
        currentPathLength += this.as;
        path.cubicTo(centerX, this.f2957X + centerY, centerX - this.f2956W, (this.f2957X * 2.5f) + centerY, centerX - (this.f2956W * 1.5f), (this.f2957X * 2.5f) + centerY);
        this.at = m1833a(path, currentPathLength);
        currentPathLength += this.at;
        path.cubicTo(centerX - (this.f2956W * 2.0f), centerY + (this.f2957X * 2.5f), centerX - (this.f2956W * 3.0f), centerY + (this.f2957X * 0.8f), centerX, centerY);
        this.au = m1833a(path, currentPathLength);
        return path;
    }

    private int m1846f(float input) {
        int result = 0;
        if (input > 0.44f && input < 0.48f) {
            result = (int) (((input - 0.44f) / 0.04f) * ((float) this.aj));
        }
        if (input <= 0.81f || input >= 0.85f) {
            return result;
        }
        return (int) (((input - 0.81f) / 0.04f) * ((float) this.aj));
    }

    private int m1850g(float input) {
        return (input < 0.48f || input > 0.85f) ? this.ae : this.af;
    }

    private int m1852h(float input) {
        if (input < 0.5f) {
            return this.ac;
        }
        if (input < 0.75f) {
            return m1835a((input - 0.5f) / 0.2f, this.ac, this.ad);
        }
        if (input < 0.85f) {
            return this.ad;
        }
        return m1835a((input - 0.9f) / 0.1f, this.ad, this.ac);
    }

    private int m1835a(float fraction, int startValue, int endValue) {
        int startA = (startValue >> 24) & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK;
        int startR = (startValue >> 16) & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK;
        int startG = (startValue >> 8) & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK;
        int startB = startValue & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK;
        return ((((((int) (((float) (((endValue >> 24) & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK) - startA)) * fraction)) + startA) << 24) | ((((int) (((float) (((endValue >> 16) & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK) - startR)) * fraction)) + startR) << 16)) | ((((int) (((float) (((endValue >> 8) & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK) - startG)) * fraction)) + startG) << 8)) | (((int) (((float) ((endValue & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK) - startB)) * fraction)) + startB);
    }

    private float m1833a(Path path, float startD) {
        Path tempPath = new Path();
        PathMeasure pathMeasure = new PathMeasure(path, false);
        pathMeasure.getSegment(startD, pathMeasure.getLength(), tempPath, true);
        pathMeasure.setPath(tempPath, false);
        return pathMeasure.getLength();
    }

    protected void mo2237a(int alpha) {
        this.f2981z.setAlpha(alpha);
    }

    protected void mo2239a(ColorFilter cf) {
        this.f2981z.setColorFilter(cf);
    }

    protected void mo2235a() {
    }
}
