package com.buddy.tiki.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.view.WrapContentDraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

public class YouFragment_ViewBinding implements Unbinder {
    private YouFragment f2310b;

    @UiThread
    public YouFragment_ViewBinding(YouFragment target, View source) {
        this.f2310b = target;
        target.mCollapsingToolbarLayout = (CollapsingToolbarLayout) Utils.findRequiredViewAsType(source, C0376R.id.collapsing_toolbar_layout, "field 'mCollapsingToolbarLayout'", CollapsingToolbarLayout.class);
        target.mAppbarLayout = (AppBarLayout) Utils.findRequiredViewAsType(source, C0376R.id.appbar_layout, "field 'mAppbarLayout'", AppBarLayout.class);
        target.mToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0376R.id.toolbar, "field 'mToolbar'", Toolbar.class);
        target.mDiamond = Utils.findRequiredView(source, C0376R.id.profile_diamond, "field 'mDiamond'");
        target.mDiamondNum = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.diamond_num, "field 'mDiamondNum'", AppCompatTextView.class);
        target.mTCoin = Utils.findRequiredView(source, C0376R.id.profile_currency, "field 'mTCoin'");
        target.mTNum = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.t_num, "field 'mTNum'", AppCompatTextView.class);
        target.mUserInfo = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.user_info, "field 'mUserInfo'", AppCompatTextView.class);
        target.mTikiNum = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.tiki_num, "field 'mTikiNum'", AppCompatTextView.class);
        target.mUserNick = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.user_nick, "field 'mUserNick'", AppCompatTextView.class);
        target.mPromotionCodeView = Utils.findRequiredView(source, C0376R.id.promotion_code_view, "field 'mPromotionCodeView'");
        target.mUserAvatar = (SimpleDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.avatar, "field 'mUserAvatar'", SimpleDraweeView.class);
        target.mInviteFriend = Utils.findRequiredView(source, C0376R.id.invite_friend, "field 'mInviteFriend'");
        target.mBackVideoBtn = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0376R.id.back_video_btn, "field 'mBackVideoBtn'", AppCompatImageView.class);
        target.mBackVideoLayout = Utils.findRequiredView(source, C0376R.id.back_video_layout, "field 'mBackVideoLayout'");
        target.mSettingButton = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.setting_btn, "field 'mSettingButton'", AppCompatTextView.class);
        target.mGameButton = Utils.findRequiredView(source, C0376R.id.game_btn, "field 'mGameButton'");
        target.mPalIndex = Utils.findRequiredView(source, C0376R.id.pal_index, "field 'mPalIndex'");
        target.mPalIndexBanner = (WrapContentDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.pal_index_banner, "field 'mPalIndexBanner'", WrapContentDraweeView.class);
        target.mInviteFriendBanner = (WrapContentDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.invite_friend_banner, "field 'mInviteFriendBanner'", WrapContentDraweeView.class);
        target.mGameBanner = (WrapContentDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.game_banner, "field 'mGameBanner'", WrapContentDraweeView.class);
        target.mPromotionBanner = (WrapContentDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.promotion_banner, "field 'mPromotionBanner'", WrapContentDraweeView.class);
        target.mGameTitle = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.game_title, "field 'mGameTitle'", AppCompatTextView.class);
        target.mRankButton = Utils.findRequiredView(source, C0376R.id.rank_btn, "field 'mRankButton'");
        target.mRankBanner = (WrapContentDraweeView) Utils.findRequiredViewAsType(source, C0376R.id.rank_banner, "field 'mRankBanner'", WrapContentDraweeView.class);
        target.mRootLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(source, C0376R.id.root_layout, "field 'mRootLayout'", SwipeRefreshLayout.class);
        target.mRushModeSwitch = (SwitchCompat) Utils.findRequiredViewAsType(source, C0376R.id.rush_mode_switch, "field 'mRushModeSwitch'", SwitchCompat.class);
        target.mRushModeLayout = Utils.findRequiredView(source, C0376R.id.rush_mode_layout, "field 'mRushModeLayout'");
        target.mTMiLayout = Utils.findRequiredView(source, C0376R.id.t_mi_layout, "field 'mTMiLayout'");
        target.mTMiNum = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0376R.id.t_mi_num, "field 'mTMiNum'", AppCompatTextView.class);
        target.mExchangeStoreLayout = Utils.findRequiredView(source, C0376R.id.store_layout, "field 'mExchangeStoreLayout'");
    }

    @CallSuper
    public void unbind() {
        YouFragment target = this.f2310b;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f2310b = null;
        target.mCollapsingToolbarLayout = null;
        target.mAppbarLayout = null;
        target.mToolbar = null;
        target.mDiamond = null;
        target.mDiamondNum = null;
        target.mTCoin = null;
        target.mTNum = null;
        target.mUserInfo = null;
        target.mTikiNum = null;
        target.mUserNick = null;
        target.mPromotionCodeView = null;
        target.mUserAvatar = null;
        target.mInviteFriend = null;
        target.mBackVideoBtn = null;
        target.mBackVideoLayout = null;
        target.mSettingButton = null;
        target.mGameButton = null;
        target.mPalIndex = null;
        target.mPalIndexBanner = null;
        target.mInviteFriendBanner = null;
        target.mGameBanner = null;
        target.mPromotionBanner = null;
        target.mGameTitle = null;
        target.mRankButton = null;
        target.mRankBanner = null;
        target.mRootLayout = null;
        target.mRushModeSwitch = null;
        target.mRushModeLayout = null;
        target.mTMiLayout = null;
        target.mTMiNum = null;
        target.mExchangeStoreLayout = null;
    }
}
