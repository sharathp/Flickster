package com.sharathp.flickster.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Video implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("name")
    private String mName;

    @SerializedName("size")
    private String mSize;

    @SerializedName("source")
    private String mSource;

    @SerializedName("type")
    private String mType;

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public String getSize() {
        return mSize;
    }

    public void setSize(final String size) {
        mSize = size;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(final String source) {
        mSource = source;
    }

    public String getType() {
        return mType;
    }

    public void setType(final String type) {
        mType = type;
    }
}