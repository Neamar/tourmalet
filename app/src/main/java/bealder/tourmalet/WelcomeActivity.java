package bealder.tourmalet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bealder.sdk.manager.BealderParameters;
import com.bealder.sdk.manager.BealderSDK;


public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        // - require -
        BealderParameters.startApp(this);

        BealderSDK.askBleActivation(this);

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

    @Override
    protected void onStart() {
        super.onStart();
        // - require -
        BealderParameters.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // - require -
        BealderParameters.onStop();
    }
}
