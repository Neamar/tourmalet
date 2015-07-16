package bealder.tourmalet;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class NewsDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);


        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        
        NewsItem newsItem = (NewsItem) getIntent().getExtras().getSerializable("newsItem");


        ((TextView) findViewById(R.id.news_title)).setText(newsItem.title);

        ((TextView) findViewById(R.id.news_description)).setText(Html.fromHtml(newsItem.description.replaceAll("\n", "<br>")));

        ((TextView) findViewById(R.id.news_info)).setText(newsItem.info);

        ImageView imageView = (ImageView) findViewById(R.id.news_image);
        imageView.setImageResource(R.drawable.ic_launcher);
        ImageLoader.getInstance().displayImage(newsItem.image, imageView);
    }
}
