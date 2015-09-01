package bealder.tourmalet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        new MixinMenuActivity().addMenuListeners(this);

        listView = (ListView) findViewById(R.id.listView);


        progress = new ProgressDialog(this);
        progress.setTitle("Chargement...");
        progress.setMessage("Nous récupérons les dernières actualités...");
        progress.show();

        new RetrieveNewsTask().execute("");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsItem newsItem = (NewsItem) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
                intent.putExtra("newsItem", newsItem);

                startActivity(intent);
            }
        });
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
        ArrayList<NewsItem> newsItems = new ArrayList<>();

        JSONObject jsonObj = null;
        try {
            jsonObj = XML.toJSONObject(xml);

            JSONArray entries = jsonObj.getJSONObject("feed").getJSONArray("entry");

            for(int i = 0; i < entries.length(); i++) {
                JSONObject entry = entries.getJSONObject(i);
                NewsItem news = new NewsItem();
                news.title = entry.getJSONObject("title").getString("content");
                news.description = entry.getJSONObject("content").getJSONObject("m:properties").getString("d:DESCRIPTIF");

                // Some items contains XML child, we need to retrieve the raw string in such a case
                if(news.description.indexOf("xml:space") != -1) {
                    news.description = entry.getJSONObject("content").getJSONObject("m:properties").getJSONObject("d:DESCRIPTIF").getString("content");
                }

                String date = entry.getString("updated").replaceFirst("T.+$", "");
                String commune = entry.getJSONObject("content").getJSONObject("m:properties").getString("d:COMMUNE").toLowerCase();

                news.info = "Date : " + date + "\nLieu : " + commune;

                news.image = entry.getJSONObject("content").getJSONObject("m:properties").getString("d:PHOTOS").split("\\|")[1];

                newsItems.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(this, "Impossible de récupérer les news, problème serveur.", Toast.LENGTH_SHORT).show();
        }

        return newsItems;
    }
}