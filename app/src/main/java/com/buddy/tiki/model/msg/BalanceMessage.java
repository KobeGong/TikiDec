package com.buddy.tiki.model.msg;

public class BalanceMessage {
    private int diamonds;
    private boolean insufficient;
    private int tikis;

    public int getDiamonds() {
        return this.diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public boolean isInsufficient() {
        return this.insufficient;
    }

    public void setInsufficient(boolean insufficient) {
        this.insufficient = insufficient;
    }

    public int getTikis() {
        return this.tikis;
    }

    public void setTikis(int tikis) {
        this.tikis = tikis;
    }
}
