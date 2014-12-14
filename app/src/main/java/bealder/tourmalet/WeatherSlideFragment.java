package bealder.tourmalet;

/**
 * Created by neamar on 12/13/14.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by neamar on 12/13/14.
 */
public class WeatherSlideFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                         Bundle savedInstanceState) {
				int currentPage = getArguments().getInt("page", 1);

				ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_weather, container, false);

				TextView stationName = (TextView) rootView.findViewById(R.id.station);
				stationName.setText(DownloadWeatherService.STATIONS[currentPage]);

				return rootView;
		}
}