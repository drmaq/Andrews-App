package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Class reads RSS data.
 *
 * @author Shemaiah Telemaque
 * @version 0.1
 */
public class RssReader {

    private String rssUrl;

    /**
     * Constructor
     *
     * @param rssUrl
     */
    public RssReader(String rssUrl) {
        this.rssUrl = rssUrl;
    }

    /**
     * Get RSS items.
     * SAX parse RSS data
     *
     * @return
     */
    public List<RssItem> getItems() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        RssParserHandler handler = new RssParserHandler();

        saxParser.parse(rssUrl, handler);

        return handler.getItems();

    }

}
