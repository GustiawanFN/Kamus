package com.gustiawandicoding.submissionkamus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EngModel implements Parcelable{
    private int id;
    private String word, description;

    public EngModel(String word, String description) {
        this.word = word;
        this.description = description;
    }

    public EngModel() {
    }
    private EngModel(Parcel in) {
        id = in.readInt();
        word = in.readString();
        description = in.readString();
    }

    public static final Creator<EngModel> CREATOR = new Creator<EngModel>() {
        @Override
        public EngModel createFromParcel(Parcel in) {
            return new EngModel(in);
        }

        @Override
        public EngModel[] newArray(int size) {
            return new EngModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(word);
        dest.writeString(description);
    }
}
