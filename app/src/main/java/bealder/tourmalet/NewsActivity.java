package bealder.tourmalet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class NewsActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        new MixinMenuActivity().addMenuListeners(this);

        listView = (ListView) findViewById(R.id.listView);

        ArrayList<NewsItem> news = new ArrayList<>();
        news.add(new NewsItem());
        news.add(new NewsItem());
        news.add(new NewsItem());

        listView.setAdapter(new NewsAdapter(this, R.layout.item_news, news));

    }
}