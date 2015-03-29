package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import edu.andrews.cptr475.bernardm.andrewsapp.R;

/**
 * Main application activity.
 * Downloading RSS data in an async task
 *
 * @author Shemaiah Telemaque
 * @version 0.1
 */
public class CampusNews extends Activity {
    /**
     * @param local A reference to the local object
     */
    private CampusNews local;

    /**
     * This method creates main application view
     *
     * @param
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set view
        setContentView(R.layout.activity_rss);

        // Set reference to this activity
        local = this;

        GetRSSDataTask task = new GetRSSDataTask();

        // Start download RSS task
        task.execute("https://www.andrews.edu/agenda/category/Campus+News/rss");

        // Debug the thread name
        Log.d("CampusNews", Thread.currentThread().getName());
    }

    private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem>> {
        @Override
        protected List<RssItem> doInBackground(String... urls) {

            // Debug the task thread name
            Log.d("CampusNews", Thread.currentThread().getName());

            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);

                // Parse RSS, get items
                return rssReader.getItems();

            } catch (Exception e) {
                Log.e("CampusNews", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<RssItem> result) {

            // Get a ListView from main view
            ListView AUAitems = (ListView) findViewById(R.id.listMainView);

            // Create a list adapter
            ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(local, android.R.layout.simple_list_item_1, result);
            // Set list adapter for the ListView
            AUAitems.setAdapter(adapter);

            // Set list view item click listener
            AUAitems.setOnItemClickListener(new ListListener(result, local));
        }
    }
}