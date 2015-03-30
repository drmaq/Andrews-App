package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import edu.andrews.cptr475.bernardm.andrewsapp.R;


/**
 * Created by drmaq on 3/30/15.
 */
public class MainActivityNews extends ListActivity {
    private static String SOURCE_URL = "https://www.andrews.edu/agenda/category/Campus+News/rss";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);
        (new ProgressTask(MainActivityNews.this)).execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;
        private Context context;
        public ProgressTask(ListActivity activity) {
            Log.i("1", "Called");
            context = activity;
            dialog = new ProgressDialog(context);
        }
        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }
        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        protected Boolean doInBackground(final String... args) {
            String xml = getXmlFromUrl(SOURCE_URL);
            userParserType2(xml);
            return null;
        }
        public void userParserType2 (String xml){
            try {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document doc = docBuilder.parse (xml);
                // normalize text representation
                System.out.println("firt:-"+doc.getDocumentElement ().getNodeName());
                NodeList listOfObject = doc.getDocumentElement().getChildNodes();
                for(int i=0;i<listOfObject.getLength();i++)
                {
                    if(listOfObject.item(i).getFirstChild()!=null&&listOfObject.item(i).getNodeName().equals("result"))
                    {
                        NodeList listOfResultChild=listOfObject.item(i).getChildNodes();
                        for(int j=0;j<listOfResultChild.getLength();j++)
                        {
                            if(listOfResultChild.item(j).getFirstChild()!=null&&listOfResultChild.item(j).getNodeName().equals("geometry"))
                            {
                                Node geometry=listOfResultChild.item(j);
                                NodeList geometryList=geometry.getChildNodes();
                                for(int k=0;k<geometryList.getLength();k++)
                                {
                                    if(geometryList.item(k).getFirstChild()!=null&&geometryList.item(k).getNodeName().equals("location"))
                                    {
                                        NodeList locationList=geometryList.item(k).getChildNodes();
                                        for(int l=0;l<locationList.getLength();l++)
                                        {
                                            if(locationList.item(l).getFirstChild()!=null)
                                            {
                                                System.out.println(locationList.item(l).getNodeName());
                                                System.out.println(locationList.item(l).getTextContent());
                                            }
                                        }
                                    }
                                }


                            }
                        }

                    }
                }

            }catch (SAXParseException err) {
                System.out.println ("** Parsing error" + ", line "
                        + err.getLineNumber () + ", uri " + err.getSystemId ());
                System.out.println(" " + err.getMessage ());

            }catch (SAXException e) {
                Exception x = e.getException ();
                ((x == null) ? e : x).printStackTrace ();

            }catch (Throwable t) {
                t.printStackTrace ();
            }
            //System.exit (0);

        }//end of main


    }

    public String getXmlFromUrl(String url) {
        String xml = null;

        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return XML
        return xml;
    }

}
