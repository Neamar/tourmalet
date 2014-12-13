package bealder.tourmalet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;


public class SplashActivity extends Activity {

		// Splash screen timer
		private static int SPLASH_TIME_OUT = 100;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_splash);

				new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer.
             */

						@Override
						public void run() {
								// Start main activty
								Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
								startActivity(i);

								// close this activity
								finish();
						}
				}, SPLASH_TIME_OUT);
		}


		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.menu_splash, menu);
				return true;
		}
}
