package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.resource.FaceUnity;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.Comparator;

final /* synthetic */ class CallActivity$$Lambda$68 implements Comparator {
    private static final CallActivity$$Lambda$68 f1212a = new CallActivity$$Lambda$68();

    private CallActivity$$Lambda$68() {
    }

    public static Comparator lambdaFactory$() {
        return a;
    }

    @Hidden
    public int compare(Object obj, Object obj2) {
        return (((FaceUnity) obj).getSalt() - ((FaceUnity) obj2).getSalt());
    }
}
