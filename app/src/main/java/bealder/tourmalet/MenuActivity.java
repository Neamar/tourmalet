package bealder.tourmalet;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by neamar on 12/13/14.
 */
public class MenuActivity extends Activity {
		public void addMenuListeners(int currentActivity) {
				if (currentActivity != R.id.menu_welcome) {
						addMenuListener(R.id.menu_welcome, WelcomeActivity.class);
				}
				if (currentActivity != R.id.menu_weather) {
						addMenuListener(R.id.menu_weather, WeatherActivity.class);
				}
				if (currentActivity != R.id.menu_contact) {
						addMenuListener(R.id.menu_contact, ContactActivity.class);
				}
		}

		private void addMenuListener(int id, final Class activityToOpen) {
				ImageView contactButton = (ImageView) findViewById(id);
				contactButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
								Intent i = new Intent(MenuActivity.this, activityToOpen);
								startActivity(i);
						}
				});
		}
}
