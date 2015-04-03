package edu.andrews.cptr475.bernardm.andrewsapp.Directory;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.AsyncTask;

import edu.andrews.cptr475.bernardm.andrewsapp.R;


public class DirectoryListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_list);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_directory_list, menu);
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
        ListView list;
        TextView fname;
        TextView lname;
        Button getDirectory;
        EditText searchDirectory;



        ArrayList<HashMap<String, String>> mDirectory = new ArrayList<HashMap<String, String>>();

        private String url;
        private static final String TAG_FNAME = "fname";
        private static final String TAG_LNAME = "lname";
        private static final String TAG_USERNAME = "username";
        JSONArray directory = null;

        public PlaceholderFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = super.onCreateView(inflater, container, savedInstanceState);

            getActivity().setContentView(R.layout.fragment_directory_list);
            mDirectory = new ArrayList<HashMap<String, String>>();

            searchDirectory = (EditText)getActivity().findViewById(R.id.searchEditText);
            searchDirectory.setHint("Enter name");

            searchDirectory.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        search();
                    }
                    return handled;
                }
            });

            getDirectory = (Button)getActivity().findViewById(R.id.searchButton);
            getDirectory.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    search();
                }
            });
            return v;
        }

        private void search(){
            url = "http://www.andrews.edu/directory/" + searchDirectory.getText().toString() + "/json";
            url = url.replace(" ","%20");
            try  {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                // TODO: handle exception
            }


            new JSONParse().execute();
        }

        private class JSONParse extends AsyncTask<String, String, JSONArray> {
            private ProgressDialog pDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                fname = (TextView)getActivity().findViewById(R.id.fnameTextView);
                lname = (TextView)getActivity().findViewById(R.id.lnameTextView);
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Getting Data ...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }

            @Override
            protected JSONArray doInBackground(String... args) {
                JsonParser jParser = new JsonParser();
                // Getting JSON from URL
                JSONArray json = jParser.getJSONfromURL(url);
                return json;
            }

            @Override
            protected void onPostExecute(JSONArray json) {
                pDialog.dismiss();
                try {
                    // Getting JSON Array from URL
                    directory = json;
                    mDirectory.clear();
                    for(int i = 0; i < directory.length(); i++){
                        JSONObject c = directory.getJSONObject(i);
                        // Storing  JSON item in a Variable
                        String fname = c.getString("f");
                        String lname = c.getString("l");
                        String username = c.getString("u");
                        // Adding value HashMap key => value
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_FNAME, fname);
                        map.put(TAG_LNAME, lname);
                        map.put(TAG_USERNAME, username);
                        mDirectory.add(map);
                        list = (ListView)getActivity().findViewById(R.id.directoryListView);
                        ListAdapter adapter = new SimpleAdapter(getActivity(), mDirectory,
                                R.layout.directory_item, new String[] { TAG_FNAME,TAG_LNAME }, new int[] {R.id.fnameTextView,R.id.lnameTextView});
                            list.setAdapter(adapter);

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                Toast.makeText(getActivity(), "You clicked on "+mDirectory.get(+position).get("username"), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
