package edu.andrews.cptr475.bernardm.andrewsapp.Tourinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import edu.andrews.cptr475.bernardm.andrewsapp.R;

/**
 * @author Bernardo Martinez
 * @version 0.1
 */

public class SelfTourGuide extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftour_guide);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selftour_guide, menu);
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
        private ViewFlipper mviewflipper;
        private Button maboutus;
        private Button mwebviewtour;

        public PlaceholderFragment() {
        }


        private void setFlipperImage(Bitmap iv) {
            //  Log.i("Set Filpper Called", res+"");
            ImageView image = new ImageView(getActivity().getApplicationContext());
            //image.setBackgroundResource(res);
            image.setImageBitmap(iv);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            mviewflipper.addView(image);
        }

        private void displayaboutuspage() {
            Intent i = new Intent(getActivity(), AboutUs.class);
            getActivity().startActivity(i);
        }


        private void displaywebviewtour() {
            Intent i = new Intent(getActivity(), WebviewToursActivity.class);
            getActivity().startActivity(i);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_selftour_guide, container, false);
            mviewflipper = (ViewFlipper) rootView.findViewById(R.id.FotosFlipper);
            int gallery_grid_Images[] = {R.drawable.globe, R.drawable.seminary};
            for (int i = 0; i < gallery_grid_Images.length; i++) {
                //  This will create dynamic image view and add them to ViewFlipper

                Bitmap bMap = BitmapFactory.decodeResource(getResources(), gallery_grid_Images[i]);
                Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 500, 500, true);
                setFlipperImage(bMapScaled);
                mviewflipper.setInAnimation(container.getContext(), R.anim.abc_fade_in);
            }
            maboutus = (Button) rootView.findViewById(R.id.aboutus);
            maboutus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayaboutuspage();
                }

            });


            mwebviewtour = (Button) rootView.findViewById(R.id.starttour);
            mwebviewtour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displaywebviewtour();
                }

            });

            return rootView;
        }
    }
}
