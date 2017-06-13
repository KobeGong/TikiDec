package com.buddy.tiki.util;

import com.buddy.tiki.model.user.TikiUser;
import java.util.Comparator;

public class PinyinComparator implements Comparator<TikiUser> {
    public int compare(TikiUser o, TikiUser t1) {
        return m1600a(o, t1);
    }

    private int m1600a(TikiUser lhs, TikiUser rhs) {
        return lhs.getPinYin().compareTo(rhs.getPinYin());
    }
}
