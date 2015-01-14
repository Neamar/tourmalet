package bealder.tourmalet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.bealder.android.sdk.service.BealderSdkActivity;


public class WelcomeActivity extends BealderSdkActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);

                // Create instance Bealder
                createBealder("8", "8c7bcad00a2beb41414aaeeb69276efd", "B9407F30-F5F8-466E-AFF9-25556B57FE6D");

            // Octiplex iOS beacon
           //createBealder("8", "8c7bcad00a2beb41414aaeeb69276efd", "e4585c10-4f09-42ec-b957-39f4616c5f3d");

            // Octiplex EzeeWorld beacon
//            createBealder("8", "8c7bcad00a2beb41414aaeeb69276efd", "89864cc7-7b89-48d7-8e9f-acae19d81ad8");

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

				new MixinMenuActivity().addMenuListeners(this);
		}


		public void displayTutorial() {
				Intent i = new Intent(WelcomeActivity.this, TutorialActivity.class);
				startActivity(i);
		}
}
