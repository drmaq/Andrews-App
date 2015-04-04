package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed;

import android.text.Html;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SAX tag handler
 *
 * @author Shemaiah Telemaque
 * @version 0.1
 */
public class RssParserHandler extends DefaultHandler {
   // StringBuilder builder;
    /**
     * Arrays
     *
     * @param rssItems Stores xml download.
     */
    private List<RssItem> rssItems;
    /**
     * References
     *
     * @param currentItem used to reference item while parsing
     */

    private RssItem currentItem;
    /**
     * Declaring parsing indicators
     *
     * @param parsingTitle Parsing title for article title.
     * @param parsingLink Parsing the link to the article.
     * @param parsingPubdate Parsing the date the story was published.
     * @param parsingDescription Parsing the contents of the story.
     */
    private boolean parsingTitle;
    private boolean parsingLink;
    private boolean parsingPubdate;
    private boolean parsingDescription;

    public RssParserHandler() {
        rssItems = new ArrayList<>();
    }

    public List<RssItem> getItems() {
        Collections.reverse(rssItems);


        //Html.fromHtml(rssItems);
       //Html.;
        return rssItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            currentItem = new RssItem();
        }
        if ("title".equals(qName)) {
            parsingTitle = true;
        }
        if ("link".equals(qName)) {
            parsingLink = true;
        }
        if ("description".equals(qName)) {
            parsingDescription = true;
        }
        if ("pubDate".equals(qName)) {
            parsingPubdate = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {

            rssItems.add(currentItem);
            currentItem = null;
        }
        if ("title".equals(qName)) {
            parsingTitle = false;
        }
        if ("link".equals(qName)) {
            parsingLink = false;
        }
        if ("managingEditor".equals(qName)) {
            parsingDescription = false;
        }
        if ("pubDate".equals(qName)) {
            parsingPubdate = false;
        }
    }

    // Characters method fills current RssItem object with data when title and link tag content is being processed
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (parsingTitle) {
            if (currentItem != null)
                currentItem.setTitle(new String(ch, start, length));
        }
        if (parsingLink) {
            if (currentItem != null) {
                currentItem.setLink(new String(ch, start, length));
                parsingLink = false;
            }
        }
        if (parsingPubdate) {
            if (currentItem != null) {
                currentItem.setPubdate(new String(ch, start, length));
                parsingPubdate = false;
            }
        }
        if (parsingDescription) {
            if (currentItem != null) {
                String value = Html.fromHtml(new String(ch, start, length)).toString();
                //      if(value."<") {
                //        currentItem.setDescription(value);
                //  }
                parsingDescription = false;
            }
        }
    }

}
