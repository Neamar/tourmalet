package bealder.tourmalet;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;


public class WeatherActivity extends MenuActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_weather);

				addMenuListeners(R.id.menu_weather);

				TextView dateText = (TextView) findViewById(R.id.date);

				String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());

				dateString = dateString.substring(0, 1).toUpperCase() + dateString.substring(1).toLowerCase();
				dateText.setText(dateString);
		}
}
