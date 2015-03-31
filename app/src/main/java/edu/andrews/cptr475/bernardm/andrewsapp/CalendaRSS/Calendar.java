package edu.andrews.cptr475.bernardm.andrewsapp.CalendaRSS;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Main application activity.
 * Downloading RSS data in an async task
 *
 * @author Shemaiah Telemaque
 * @version 0.1
 */
public class Calendar extends Activity {
    /**
     * @param local A reference to the local object
     */
    private Calendar local;

    /**
     * This method creates main application view
     *
     * @param
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set view
      //  setContentView(R.layout.fragment_calendar);

        // Set reference to this activity
        local = this;

        GetRSSDataTask task = new GetRSSDataTask();

        // Start download RSS task
        task.execute("http://www.andrews.edu/events/rss");

        // Debug the thread name
        Log.d("Calendar", Thread.currentThread().getName());
    }

    private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem>> {
        @Override
        protected List<RssItem> doInBackground(String... urls) {

            // Debug the task thread name
            Log.d("Calendar", Thread.currentThread().getName());

            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);

                // Parse RSS, get items
                return rssReader.getItems();

            } catch (Exception e) {
                Log.e("Calendar", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<RssItem> result) {

            // Get a ListView from main view
            //ListView AUAitems = (ListView) findViewById(R.id.calendarView);

            // Create a list adapter
            ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(local, android.R.layout.list_content, result);
            // Set list adapter for the ListView
            //AUAitems.setAdapter(adapter);

            // Set list view item click listener
            //AUAitems.setOnItemClickListener(new ListListener(result, local));
        }
    }
}