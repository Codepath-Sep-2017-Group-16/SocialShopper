package com.codepath.socialshopper.socialshopper.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by saripirala on 10/28/17.
 */

public class TimeLineModel implements Parcelable {

    private String mMessage;
    private String mDate;
    private String mStatus;
    private String paymentStatus;
    public static String listID;

    public TimeLineModel() {
    }

    public TimeLineModel(String mMessage, String mDate, String status) {
        this.mMessage = mMessage;
        this.mDate = mDate;
        this.mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getmStatus(){ return mStatus; }

    public void semMessage(String message) {
        this.mMessage = message;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mMessage);
        dest.writeString(this.mDate);
    }

    protected TimeLineModel(Parcel in) {
        this.mMessage = in.readString();
        this.mDate = in.readString();
        int tmpMStatus = in.readInt();
    }

    public static final Parcelable.Creator<TimeLineModel> CREATOR = new Parcelable.Creator<TimeLineModel>() {
        @Override
        public TimeLineModel createFromParcel(Parcel source) {
            return new TimeLineModel(source);
        }

        @Override
        public TimeLineModel[] newArray(int size) {
            return new TimeLineModel[size];
        }
    };
}
