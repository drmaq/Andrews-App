package edu.andrews.cptr475.bernardm.andrewsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import edu.andrews.cptr475.bernardm.andrewsapp.CalendaRSS.Calendar;
import edu.andrews.cptr475.bernardm.andrewsapp.Map.MapsActivity;
import edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.MainActivityNews;
import edu.andrews.cptr475.bernardm.andrewsapp.Tourinfo.SelfTourGuide;

/**
 * @author Bernardo Martinez
 * @version 0.1
 */
public class MainActivity extends ActionBarActivity {

//Mainactivity updated 3/27/2015

// <meta-data android:name="com.google.android.maps.v2.API_KEY" android:value="@string/google_maps_key"/>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


        //  View v = inflater.inflate(R.layout.fragment_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private ImageButton mgooglemaps;
        private ImageButton mselfguidedtour;
        private ImageButton mnewsrss;
        private ImageButton mcalendar;

        public PlaceholderFragment() {
        }


        /**
         * Launch	activity	to	display	author	fact
         */
        private void displaygooglemap() {
            //	Create	intent	with	name	of	class	for	second	activity.
            //	This	intent	will	be	sent	to	the	Activity	Manager	in	the	OS
            //	which	will	launch	the	activity.
            Intent i = new Intent(getActivity(), MapsActivity.class);
            getActivity().startActivity(i);
        }

        private void displayselftourguide() {

            Intent i = new Intent(getActivity(), SelfTourGuide.class);
            getActivity().startActivity(i);
        }

        private void displayrssnews() {

            Intent i = new Intent(getActivity(), MainActivityNews.class);
            getActivity().startActivity(i);
        }

        private void displayrcalendar() {

            Intent i = new Intent(getActivity(), Calendar.class);
            getActivity().startActivity(i);
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            mgooglemaps = (ImageButton) rootView.findViewById(R.id.imageButton);
            mgooglemaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displaygooglemap();
                }

            });
            mselfguidedtour = (ImageButton) rootView.findViewById(R.id.selftourguide);
            mselfguidedtour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayselftourguide();
                }

            });

            mnewsrss = (ImageButton) rootView.findViewById(R.id.news);
            mnewsrss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayrssnews();
                }
            });
            mcalendar = (ImageButton) rootView.findViewById(R.id.calendar);
            mcalendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayrcalendar();
                }
            });


            ///code to create a banner for comments
           /* AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);*/

            return rootView;
        }
    }
}
