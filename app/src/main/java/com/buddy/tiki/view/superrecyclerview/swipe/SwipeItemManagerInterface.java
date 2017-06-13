package com.buddy.tiki.view.superrecyclerview.swipe;

import java.util.List;

public interface SwipeItemManagerInterface {

    public enum Mode {
        Single,
        Multiple
    }

    void closeAllExcept(SwipeLayout swipeLayout);

    void closeItem(int i);

    Mode getMode();

    List<Integer> getOpenItems();

    List<SwipeLayout> getOpenLayouts();

    boolean isOpen(int i);

    void openItem(int i);

    void removeShownLayouts(SwipeLayout swipeLayout);

    void setMode(Mode mode);
}
