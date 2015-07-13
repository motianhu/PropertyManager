package com.smona.app.propertymanager.source.listview;

public enum PropertyWorkMode {
    // 没有刷新
    MODE_NULL(0x00),
    // 可以上拉可以下拉
    BOTH(0x01),
    // 可以上拉
    FOOTER(0x01 << 1),
    // 可以下拉
    HEADER(0x01 << 2);

    int mValue;

    PropertyWorkMode(int value) {
        this.mValue = value;
    }

    static public PropertyWorkMode getDefault() {
        return FOOTER;
    }

    public int getValue() {
        return mValue;
    }

    public boolean permitRefreshHeader() {
        return this == HEADER || this == BOTH;
    }

    public boolean permitRefreshFooter() {
        return this == FOOTER || this == BOTH;
    }
}
