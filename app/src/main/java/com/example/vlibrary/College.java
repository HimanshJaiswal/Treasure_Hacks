package com.VIT.vlibrary;

import android.media.Image;

public class College
{
    String collegeName;
    String imageUrl;
    public College(String name, String url)
    {
        collegeName = name;
        imageUrl = url;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public College() {
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
