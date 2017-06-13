package com.buddy.tiki.protocol.web;

import android.support.v4.util.ArrayMap;
import com.buddy.tiki.model.base.HttpResult;
import com.buddy.tiki.model.payment.SendGiftResult;
import com.buddy.tiki.model.user.FlwApplyResponse;
import com.buddy.tiki.model.user.SyncFriends;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FollowApi {
    @FormUrlEncoded
    @POST("/top/i_follow_action/apply_friend")
    Observable<HttpResult<Boolean>> applyFriendAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_follow_action/apply_friend_for_search")
    Observable<HttpResult<Boolean>> applyFriendForSearchAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_follow_query/get_applys")
    Observable<HttpResult<FlwApplyResponse>> applysQuery(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_follow_action/pass_applys")
    Observable<HttpResult<Boolean>> passApplysAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_follow_action/send_gift")
    Observable<HttpResult<Boolean>> sendGiftAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_follow_action/send_gift_v2")
    Observable<HttpResult<SendGiftResult>> sendGiftActionV2(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_follow_action/send_gift_inchat")
    Observable<HttpResult<SendGiftResult>> sendGiftInChat(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_follow_action/set_mark")
    Observable<HttpResult<Boolean>> setMarkAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_follow_query/sync_friends")
    Observable<HttpResult<SyncFriends>> syncFriendsQuery(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_follow_action/unfollow")
    Observable<HttpResult<Boolean>> unfollowAction(@FieldMap ArrayMap<String, Object> arrayMap);

    @FormUrlEncoded
    @POST("/top/i_follow_action/use_border")
    Observable<HttpResult<Boolean>> userBorderAction(@FieldMap ArrayMap<String, Object> arrayMap);
}
