package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.PARSERTESTING;

/** Model information about an image in a photo gallery */
public class StoreItem {
    private String mDescription;
    private String mItem;
    private String mTitle;
    /** Get and Set XML Description*/
    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String description) {
        mDescription = description;
    }
    /** Get and Set XML Items*/
    public String getItem() {
        return mItem;
    }
    public void setItem(String Item) {
        mItem = Item;
    }
    /** Get and Set XML Title*/
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String Title) {
        mTitle = Title;
    }

    public String toString() {
        return mDescription + "("+ mTitle+ ")";
    }
}
