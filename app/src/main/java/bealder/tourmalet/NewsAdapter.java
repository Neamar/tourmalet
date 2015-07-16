package bealder.tourmalet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


public class NewsAdapter extends ArrayAdapter<NewsItem> {
    protected final ArrayList<NewsItem> news;
    protected final LayoutInflater inflater;

    public NewsAdapter(Context context, int resource, ArrayList<NewsItem> results) {
        super(context, resource, results);
        inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        news = results;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null ){
            convertView = inflater.inflate(R.layout.item_news, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.news_title)).setText(news.get(position).title);
        ((TextView) convertView.findViewById(R.id.news_description)).setText(news.get(position).description);
        ((TextView) convertView.findViewById(R.id.news_info)).setText(news.get(position).infos);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.news_image);
        imageView.setImageResource(R.drawable.ic_launcher);
        ImageLoader.getInstance().displayImage(news.get(position).image, imageView);

        return convertView;
    }
}
