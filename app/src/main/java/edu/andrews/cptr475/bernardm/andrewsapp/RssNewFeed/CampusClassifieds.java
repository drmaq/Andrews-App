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
public class CampusClassifieds extends Activity {
    /**
     * @param local A reference to the local object
     */
    private CampusClassifieds local;

    /**
     * This method creates main application view
     *
     * @param
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set view
        setContentView(R.layout.fragment_classified);

        // Set reference to this activity
        local = this;

        GetRSSDataTask task = new GetRSSDataTask();

        // Start download RSS task
        task.execute("https://www.andrews.edu/agenda/category/Classifieds/rss");

        // Debug the thread name
        Log.d("CampusNews", Thread.currentThread().getName());
        Log.d("CampusNews2", Thread.currentThread().toString());
        // Log.d("CampusNews3"),Thread.enumerate(Thread)RssItem.toString();
    }

    private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem>> {
        @Override
        protected List<RssItem> doInBackground(String... urls) {

            // Debug the task thread name
            Log.d("CampusNews", Thread.currentThread().getName());
            Log.d("CampusNews2", Thread.currentThread().toString());

            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);

                // Parse RSS, get items
                return rssReader.getItems();

            } catch (Exception e) {
                Log.e("CampusNews", e.getMessage());
                Log.e("CampusNews2", e.getLocalizedMessage());
            }
            //titoasads
            return null;
        }

        @Override
        protected void onPostExecute(List<RssItem> result) {

            // Get a ListView from main view
            ListView AUAitems = (ListView) findViewById(R.id.listMainView);

            // Create a list adapter
            ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(local, android.R.layout.simple_list_item_activated_1, result);
            // Set list adapter for the ListView
            AUAitems.setAdapter(adapter);

            // Set list view item click listener
            AUAitems.setOnItemClickListener(new ListListener(result, local));
        }
    }
}