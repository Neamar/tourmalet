package bealder.tourmalet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class NewsActivity extends Activity {
    private ProgressDialog progress;
    private ListView listView;

    private class RetrieveNewsTask extends AsyncTask<String, Void, ArrayList<NewsItem>> {
        protected ArrayList<NewsItem> doInBackground(String... urls) {
            String xml = downloadXML("http://wcf.tourinsoft.com/Syndication/3.0/cdt65/e1eecc8a-c528-478f-a372-490c1271d4b9/Objects?$top=20");

            final ArrayList<NewsItem> news = parseXmlData(xml);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Dismiss the dialog
                    progress.dismiss();

                    listView.setAdapter(new NewsAdapter(NewsActivity.this, R.layout.item_news, news));
                }
            });

            return news;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        new MixinMenuActivity().addMenuListeners(this);

        listView = (ListView) findViewById(R.id.listView);


        progress = new ProgressDialog(this);
        progress.setTitle("Chargement...");
        progress.setMessage("Nous récupérons les dernières actualités...");
        progress.show();

        new RetrieveNewsTask().execute("");

    }

    public String downloadXML(String url) {
        String xml = null;

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);
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

    public ArrayList<NewsItem> parseXmlData(String xml) {
        JSONObject jsonObj = null;
        try {
            jsonObj = XML.toJSONObject(xml);

            JSONArray entries = jsonObj.getJSONObject("feed").getJSONArray("entry");

            for(int i = 0; i < entries.length(); i++) {
                JSONObject entry = entries.getJSONObject(i);
                NewsItem news = new NewsItem();
                news.title = entry.getJSONObject("title").getString("content");
                Log.e("WTF", news.title);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return new ArrayList<>();
    }
}