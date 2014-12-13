package bealder.tourmalet;

import android.os.Bundle;


public class WeatherActivity extends MenuActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_contact);

				addMenuListeners(R.id.menu_weather);
		}
}
