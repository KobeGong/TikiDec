package com.buddy.tiki.model.im;

import org.parceler.Parcel;

@Parcel
public class MatchResult {
    int onlines;
    String passport;

    public int getOnlines() {
        return this.onlines;
    }

    public void setOnlines(int onlines) {
        this.onlines = onlines;
    }

    public String getPassport() {
        return this.passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
