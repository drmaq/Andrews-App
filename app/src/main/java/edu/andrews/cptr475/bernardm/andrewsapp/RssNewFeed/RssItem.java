package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 *
 * @author Shemaiah Telemaque
 * @version 0.1
 */
public class RssItem {
    /**
     * Declaration of tag in xml file.
     *
     * @param title Title of the story.
     * @param link Link to the story.
     * @param pubdate Date when story is published.
     * @param description contents of the story
     */

    private String title;
    private String link;
    private String pubdate;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return title;
    }


}
