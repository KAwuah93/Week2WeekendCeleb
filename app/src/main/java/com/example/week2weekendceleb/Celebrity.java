package com.example.week2weekendceleb;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Denote that this is an entity
@Entity( tableName = "celebrity_table")
public class Celebrity implements Parcelable {

    //Create autogenerating id
    @PrimaryKey(autoGenerate = true)
    private int _id;

    private String name;
    private String industry;
    private String description;
    private String relevantUrl;

    protected Celebrity(Parcel in) {
        _id = in.readInt();
        name = in.readString();
        industry = in.readString();
        description = in.readString();
        relevantUrl = in.readString();
    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

    public Celebrity() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelevantUrl() {
        return relevantUrl;
    }

    public void setRelevantUrl(String relevantUrl) {
        this.relevantUrl = relevantUrl;
    }

    public Celebrity(String name, String industry, String description, String relevantUrl) {
        this.name = name;
        this.industry = industry;
        this.description = description;
        this.relevantUrl = relevantUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_id);
        parcel.writeString(name);
        parcel.writeString(industry);
        parcel.writeString(description);
        parcel.writeString(relevantUrl);
    }
}
