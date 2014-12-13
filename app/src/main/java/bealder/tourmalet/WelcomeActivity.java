package bealder.tourmalet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class WelcomeActivity extends MenuActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_welcome);

				// On first launch, display tutorial
				SharedPreferences settings = getPreferences(MODE_PRIVATE);
				if (!settings.contains("hasShownTutorial")) {
						displayTutorial();
						SharedPreferences.Editor settingsEditor = settings.edit();
						settingsEditor.putBoolean("hasShownTutorial", true);
						settingsEditor.commit();
				}

				Button displayTutorialButton = (Button) findViewById(R.id.display_tutorial);
				displayTutorialButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
								displayTutorial();
						}
				});

				addMenuListeners(R.id.menu_welcome);
		}


		public void displayTutorial() {
				Intent i = new Intent(WelcomeActivity.this, TutorialActivity.class);
				startActivity(i);
		}
}
