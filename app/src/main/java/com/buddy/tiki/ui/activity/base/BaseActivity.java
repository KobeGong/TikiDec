package com.buddy.tiki.ui.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import butterknife.ButterKnife;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.service.base.DataLayer;
import com.buddy.tiki.util.DisplayUtil;
import com.buddy.tiki.util.phonetype.MeizuUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;
import io.realm.Realm;
import java.lang.reflect.Field;
import org.bytedeco.javacpp.swscale;

public abstract class BaseActivity extends RxAppCompatActivity {
    private DataLayer f1112a = DataLayer.getInstance();
    protected Realm f1113c;

    @LayoutRes
    protected abstract int mo2115a();

    protected abstract void mo2116a(Bundle bundle);

    @IdRes
    protected abstract int mo2117b();

    private static void m426a(Context destContext) {
        if (destContext != null) {
            InputMethodManager imm = (InputMethodManager) destContext.getSystemService("input_method");
            if (imm != null) {
                String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView", "mServedInputConnection"};
                for (String param : arr) {
                    try {
                        Field f = imm.getClass().getDeclaredField(param);
                        if (!f.isAccessible()) {
                            f.setAccessible(true);
                        }
                        Object obj_get = f.get(imm);
                        if (obj_get != null && (obj_get instanceof View)) {
                            if (((View) obj_get).getContext() == destContext) {
                                f.set(imm, null);
                            } else {
                                return;
                            }
                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        }
    }

    public DataLayer getDataLayer() {
        return this.f1112a;
    }

    protected void onCreate(Bundle savedInstanceState) {
        m430b(savedInstanceState);
        super.onCreate(savedInstanceState);
        this.f1113c = Realm.getDefaultInstance();
        setContentView(mo2115a());
        ButterKnife.bind((Activity) this);
        mo2116a(savedInstanceState);
        ActivityManager.getInstance().onActivityCreate(this);
    }

    protected void m430b(Bundle savedInstanceState) {
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f1113c.close();
        ActivityManager.getInstance().onActivityDestroy(this);
        m426a((Context) this);
    }

    public void addFragment(@NonNull Fragment fragment) {
        if (mo2117b() > 0) {
            addFragment(fragment, true);
        }
    }

    public void addFragment(@NonNull Fragment fragment, boolean usedAnim) {
        if (mo2117b() > 0) {
            if (usedAnim) {
                getSupportFragmentManager().beginTransaction().setCustomAnimations(C0376R.anim.push_left_in, C0376R.anim.push_left_out, C0376R.anim.push_right_in, C0376R.anim.push_right_out).replace(mo2117b(), fragment, fragment.getClass().getName()).addToBackStack(fragment.getClass().getName()).commitAllowingStateLoss();
            } else {
                addFragment(fragment, 0, 0, 0, 0);
            }
        }
    }

    public void addFragment(@NonNull Fragment fragment, @AnimRes int enter, @AnimRes int exit) {
        addFragment(fragment, enter, exit, 0, 0);
    }

    public void addFragment(@NonNull Fragment fragment, @AnimRes int enter, @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(enter, exit, popEnter, popExit).replace(mo2117b(), fragment, fragment.getClass().getName()).addToBackStack(fragment.getClass().getName()).commitAllowingStateLoss();
    }

    public void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            hideKeyBoard();
            getSupportFragmentManager().popBackStack();
            return;
        }
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (4 != keyCode || getSupportFragmentManager().getBackStackEntryCount() != 1) {
            return super.onKeyDown(keyCode, event);
        }
        finish();
        return true;
    }

    public void hideKeyBoard(@NonNull View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            hideKeyBoard(view);
        }
    }

    public void showKeyBoard(@NonNull View view) {
        ((InputMethodManager) getSystemService("input_method")).showSoftInput(view, 2);
    }

    public void launchActivity(@NonNull Class cls) {
        launchActivity(cls, null);
    }

    public void launchActivity(@NonNull Class cls, int flags) {
        launchActivity(cls, null, flags);
    }

    public void launchActivity(@NonNull Class cls, @Nullable Bundle args) {
        Intent intent = new Intent(this, cls);
        if (args != null) {
            intent.putExtra("PARAMS_KEY", args);
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            bundle = bundle.getBundle("PARAM_KEY_FROM_NOTIFICATION");
            if (bundle != null) {
                intent.putExtra("PARAM_KEY_FROM_NOTIFICATION", bundle);
            }
        }
        startActivity(intent);
        overridePendingTransition(C0376R.anim.activity_bottom_in, C0376R.anim.activity_still);
    }

    public void launchActivity(@NonNull Class cls, @Nullable Bundle args, int flags) {
        Intent intent = new Intent(this, cls);
        intent.setFlags(flags);
        if (args != null) {
            intent.putExtra("PARAMS_KEY", args);
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            bundle = bundle.getBundle("PARAM_KEY_FROM_NOTIFICATION");
            if (bundle != null) {
                intent.putExtra("PARAM_KEY_FROM_NOTIFICATION", bundle);
            }
        }
        startActivity(intent);
        overridePendingTransition(C0376R.anim.activity_bottom_in, C0376R.anim.activity_still);
    }

    public void launchActivityForResult(@NonNull Class cls, int requestCode) {
        launchActivityForResult(cls, requestCode, null);
    }

    public void launchActivityForResult(@NonNull Class cls, int requestCode, @Nullable Bundle args) {
        Intent intent = new Intent(this, cls);
        if (args != null) {
            intent.putExtra("PARAMS_KEY", args);
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            bundle = bundle.getBundle("PARAM_KEY_FROM_NOTIFICATION");
            if (bundle != null) {
                intent.putExtra("PARAM_KEY_FROM_NOTIFICATION", bundle);
            }
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(C0376R.anim.activity_bottom_in, C0376R.anim.activity_still);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(C0376R.anim.activity_still, C0376R.anim.activity_bottom_out);
    }

    public void hideStatusBar() {
        LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= swscale.SWS_SPLINE;
        getWindow().setAttributes(attrs);
        getWindow().addFlags(swscale.SWS_LANCZOS);
    }

    public void showStatusBar() {
        LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= -1025;
        getWindow().setAttributes(attrs);
        getWindow().addFlags(swscale.SWS_LANCZOS);
    }

    public void hideNavigationBar() {
        getWindow().getDecorView().setSystemUiVisibility(6);
    }

    public void showNavigationBar() {
        getWindow().getDecorView().setSystemUiVisibility(0);
    }

    @Nullable
    public Bundle getArguments() {
        if (getIntent() != null) {
            return getIntent().getBundleExtra("PARAMS_KEY");
        }
        return null;
    }

    protected void m431d(View view) {
        if (view != null && DisplayUtil.hasNaviBar(this) && !MeizuUtils.isMeizu()) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof MarginLayoutParams) {
                ((MarginLayoutParams) layoutParams).bottomMargin += DisplayUtil.getNavigationBarHeight(this);
                view.setLayoutParams(layoutParams);
                return;
            }
            throw new IllegalArgumentException("not support other layoutParams");
        }
    }

    protected void m432e(View view) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof MarginLayoutParams) {
                ((MarginLayoutParams) layoutParams).topMargin += getResources().getDimensionPixelOffset(C0376R.dimen.status_bar_height);
                view.setLayoutParams(layoutParams);
                return;
            }
            throw new IllegalArgumentException("not support other layoutParams");
        }
    }
}
