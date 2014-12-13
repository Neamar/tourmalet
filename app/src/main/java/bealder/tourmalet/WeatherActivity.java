package bealder.tourmalet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;


public class WeatherActivity extends SlideActivity {
		/**
		 * The number of weather station to show
		 */
		private final int NUM_PAGES = 3;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_weather);

				new MixinMenuActivity().addMenuListeners(this);

				TextView dateText = (TextView) findViewById(R.id.date);

				String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());

				dateString = dateString.substring(0, 1).toUpperCase() + dateString.substring(1).toLowerCase();
				dateText.setText(dateString);

				initPager(new ScreenSlidePagerAdapter(getSupportFragmentManager()));
		}


		protected class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
				public ScreenSlidePagerAdapter(FragmentManager fm) {
						super(fm);
				}

				@Override
				public Fragment getItem(int position) {
						Bundle bundle = new Bundle();
						bundle.putInt("page", position);

						Fragment page = new WeatherSlideFragment();
						page.setArguments(bundle);
						return page;
				}

				@Override
				public int getCount() {
						return NUM_PAGES;
				}
		}
}
