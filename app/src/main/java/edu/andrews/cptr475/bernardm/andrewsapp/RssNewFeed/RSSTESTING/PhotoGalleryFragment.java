package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.RSSTESTING;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import edu.andrews.cptr475.bernardm.andrewsapp.R;

public class PhotoGalleryFragment extends Fragment {
    GridView mGridView;
    ArrayList<GalleryItem> mItems;
    /** Create an image view containing  the photo for each photo in the gallery */
    private class GalleryItemAdapter extends ArrayAdapter<GalleryItem> {
        public GalleryItemAdapter(ArrayList<GalleryItem> items) {
            super(getActivity(), 0, items);
        }

        /**
         * Setup the view for a gallery items at a given position in an array list
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                //There is now existing view to recycle,
                //Create a new one by inflating the gallery item layout
                convertView = getActivity().getLayoutInflater().inflate(R.layout.gallery_item, parent, false);
            }
            //Specify the image for the image view
            ImageView imageView = (ImageView) convertView.findViewById(R.id.gallery_items_imageView);
            imageView.setImageResource(android.R.drawable.ic_menu_gallery);
            return convertView;
        }
    }
    /** Asynchronous task responsible for downloading items from flickr */
    private class FetchItemsTask extends AsyncTask<Void, Void, ArrayList<GalleryItem>> {
        @Override
        protected ArrayList<GalleryItem> doInBackground(Void... params){
            return new FlickrFetchr().fetchItems();
        }
        @Override
        protected void onPostExecute(ArrayList<GalleryItem> items){
            mItems = items;
            setupAdapter();
        }
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setRetainInstance(true);
        //Start AsyncTask running
        new FetchItemsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        
        mGridView = (GridView)v.findViewById(R.id.gridView);
        setupAdapter();
        
        return v;
    }
    
    void setupAdapter() {
        if (getActivity() == null || mGridView == null) return;
        
        if (mItems != null) {
            //There are gallery items, user our own Adapter to generate the views.
            mGridView.setAdapter(new GalleryItemAdapter(mItems));
        } else {
            mGridView.setAdapter(null);
        }
    }


}
