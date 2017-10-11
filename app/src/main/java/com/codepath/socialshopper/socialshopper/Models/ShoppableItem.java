package com.codepath.socialshopper.socialshopper.Models;

import org.parceler.Parcel;

/**
 * Created by gumapathi on 10/11/2017.
 */
@Parcel(analyze={ShoppableItem.class})
public class ShoppableItem {
    String mItemName;
    String mItemIconFileName;

    public String getmItemName() {
        return mItemName;
    }

    public void setmItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    public String getmItemIconFileName() {
        return mItemIconFileName;
    }

    public void setmItemIconFileName(String mItemIconFileName) {
        this.mItemIconFileName = mItemIconFileName;
    }

    public ShoppableItem() {
    }

    public ShoppableItem(String mItemName, String mItemIconFileName) {
        this.mItemName = mItemName;
        this.mItemIconFileName = mItemIconFileName;
    }
}