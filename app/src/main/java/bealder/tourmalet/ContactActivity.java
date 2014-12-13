package bealder.tourmalet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class ContactActivity extends MenuActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_contact);

				addMenuListeners(R.id.menu_contact);

				addSocialLink(R.id.contact_facebook, "https://fr-fr.facebook.com/grandtourmalet");
				addSocialLink(R.id.contact_twitter, "https://twitter.com/GrandTourmalet");
				addSocialLink(R.id.contact_instagram, "http://instagram.com/grandtourmalet/");
		}

		private void addSocialLink(int id, final String url) {
				ImageView socialButton = (ImageView) findViewById(id);
				socialButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
								Intent i = new Intent(Intent.ACTION_VIEW);
								i.setData(Uri.parse(url));
								startActivity(i);
						}
				});
		}
}
