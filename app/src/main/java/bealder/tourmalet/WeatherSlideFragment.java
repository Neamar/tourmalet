package bealder.tourmalet;

/**
 * Created by neamar on 12/13/14.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by neamar on 12/13/14.
 */
public class WeatherSlideFragment extends Fragment {
		public int[] ICONS = new int[]{
						R.drawable.weather_0,
						R.drawable.weather_1,
						R.drawable.weather_2,
						R.drawable.weather_3,
						R.drawable.weather_4,
						R.drawable.weather_5,
						R.drawable.weather_6,
						R.drawable.weather_7,
						R.drawable.weather_8,
						R.drawable.weather_9,
						R.drawable.weather_10,
						R.drawable.weather_11,
						R.drawable.weather_12,
						R.drawable.weather_13,
						R.drawable.weather_14,
						R.drawable.weather_15,
						R.drawable.weather_16,
						R.drawable.weather_17,
						R.drawable.weather_18,
						R.drawable.weather_19,
						R.drawable.weather_20,
						R.drawable.weather_21,
						R.drawable.weather_22,
						R.drawable.weather_23,
						R.drawable.weather_24,
						R.drawable.weather_25,
						R.drawable.weather_26,
						R.drawable.weather_27,
						R.drawable.weather_28,
						R.drawable.weather_29,
						R.drawable.weather_30,
						R.drawable.weather_31,
						R.drawable.weather_32,
						R.drawable.weather_33,
						R.drawable.weather_34,
						R.drawable.weather_35,
						R.drawable.weather_36,
						R.drawable.weather_37,
						R.drawable.weather_38,
						R.drawable.weather_39,
						R.drawable.weather_40,
						R.drawable.weather_41,
						R.drawable.weather_42,
						R.drawable.weather_43,
						R.drawable.weather_44,
						R.drawable.weather_45,
						R.drawable.weather_46,
						R.drawable.weather_47,
						R.drawable.weather_48,
						R.drawable.weather_49
		};

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                         Bundle savedInstanceState) {
				int currentPage = getArguments().getInt("page", 1);

				ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_weather, container, false);

				// Set the station name
				TextView stationName = (TextView) rootView.findViewById(R.id.station);
				stationName.setText(DownloadWeatherService.STATIONS[currentPage]);

				// Retrieve the weather
				int woeid = DownloadWeatherService.WOEIDS[currentPage];
				SharedPreferences weatherData = getActivity().getSharedPreferences("weather", Context.MODE_PRIVATE);
				if (weatherData.getString(woeid + "-update", "").equals(DownloadWeatherService.getCurrentDateFormatted())) {
						// Information in the cache is up to date: display it
						try {
								JSONObject todayWeather = new JSONObject(weatherData.getString(woeid + "-today", "{}"));

								ImageView todayWeatherIcon = (ImageView) rootView.findViewById(R.id.today_weather_image);
								todayWeatherIcon.setImageResource(ICONS[Integer.parseInt(todayWeather.getString("code"))]);

								TextView todayWeatherText = (TextView) rootView.findViewById(R.id.today_weather_text);
								todayWeatherText.setText("min. " + todayWeather.getString("low") + "°C\nmax. " + todayWeather.getString("high") + "°C\n" + todayWeather.getString("text"));

								JSONObject tomorrowWeather = new JSONObject(weatherData.getString(woeid + "-tomorrow", "{}"));

								ImageView tomorrowWeatherIcon = (ImageView) rootView.findViewById(R.id.tomorrow_weather_image);
								tomorrowWeatherIcon.setImageResource(ICONS[Integer.parseInt(tomorrowWeather.getString("code"))]);

								TextView tomorrowWeatherText = (TextView) rootView.findViewById(R.id.tomorrow_weather_text);
								tomorrowWeatherText.setText("min. " + tomorrowWeather.getString("low") + "°C\nmax. " + tomorrowWeather.getString("high") + "°C\n" + tomorrowWeather.getString("text"));

						} catch (JSONException e) {
								e.printStackTrace();
						}
				}
				return rootView;
		}
}