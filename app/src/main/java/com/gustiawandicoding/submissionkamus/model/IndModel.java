package com.gustiawandicoding.submissionkamus.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gustiawan on 9/28/2018.
 */

public class IndModel implements Parcelable{
    private int id;
    private String word, description;

    public IndModel(String word, String description) {
        this.word = word;
        this.description = description;
    }

    public IndModel(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private IndModel(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<IndModel> CREATOR = new Creator<IndModel>() {
        @Override
        public IndModel createFromParcel(Parcel in) {
            return new IndModel(in);
        }

        @Override
        public IndModel[] newArray(int size) {
            return new IndModel[size];
        }
    };
}
