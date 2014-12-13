package bealder.tourmalet;

import android.os.Bundle;


public class TutorialActivity extends SlideActivity {
		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_tutorial);

				initPager();
		}
}