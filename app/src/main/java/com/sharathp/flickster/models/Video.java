package com.sharathp.flickster.models;

import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("id")
    private String mId;

    @SerializedName("key")
    private String mKey;

    @SerializedName("name")
    private String mName;

    @SerializedName("site")
    private String mSite;

    @SerializedName("size")
    private int mSize;

    @SerializedName("type")
    private String mType;

    public String getId() {
        return mId;
    }

    public void setId(final String id) {
        mId = id;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(final String key) {
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public String getSite() {
        return mSite;
    }

    public void setSite(final String site) {
        mSite = site;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(final int size) {
        mSize = size;
    }

    public String getType() {
        return mType;
    }

    public void setType(final String type) {
        mType = type;
    }
}