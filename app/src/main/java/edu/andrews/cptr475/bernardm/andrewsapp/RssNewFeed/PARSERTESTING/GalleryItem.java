package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.PARSERTESTING;

/** Model information about an image in a photo gallery */
public class GalleryItem {
    private String mDescription;
    private String mItem;
    private String mTitle;

    public String description() {
        return mDescription;
    }
    public void setCaption(String description) {
        mDescription = description();
    }
    public String getItem() {
        return getItem();
    }
    public void setItem(String Item) {
        mItem = Item;
    }
    
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String Title) {
        mTitle = Title;
    }

    public String toString() {
        return mDescription;
    }
}
