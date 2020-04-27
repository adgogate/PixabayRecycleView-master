package com.example.pixabayrecyclerview;

public class ExampleItem {
    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public ExampleItem(String imageURL, String Creator, int likes)
    {
        mImageUrl = imageURL;
        mCreator = Creator;
        mLikes = likes;
    }

    public String getImageUrl()
    {
        return mImageUrl;
    }

    public String getCreator()
    {
        return mCreator;
    }
    public int getLikeCount()
    {
        return mLikes;
    }

}
