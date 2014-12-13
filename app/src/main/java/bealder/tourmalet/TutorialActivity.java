package bealder.tourmalet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;


public class TutorialActivity extends FragmentActivity {
		/**
		 * The number of pages (wizard steps) to show in this demo.
		 */
		private static final int NUM_PAGES = 3;

		/**
		 * The pager widget, which handles animation and allows swiping horizontally to access previous
		 * and next wizard steps.
		 */
		private ViewPager mPager;

		/**
		 * The pager adapter, which provides the pages to the view pager widget.
		 */
		private PagerAdapter mPagerAdapter;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_tutorial);

				// Instantiate a ViewPager and a PagerAdapter.
				mPager = (ViewPager) findViewById(R.id.pager);
				mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
				mPager.setAdapter(mPagerAdapter);

				mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
						@Override
						public void onPageScrolled(int i, float v, int i2) {
							if(i == NUM_PAGES - 1 && v > 0) {
									finish();
							}
						}

						@Override
						public void onPageSelected(int i) {
						}

						@Override
						public void onPageScrollStateChanged(int i) {

						}
				});
		}

		@Override
		public void onBackPressed() {
				if (mPager.getCurrentItem() == 0) {
						// If the user is currently looking at the first step, allow the system to handle the
						// Back button. This calls finish() on this activity and pops the back stack.
						super.onBackPressed();
				} else {
						// Otherwise, select the previous step.
						mPager.setCurrentItem(mPager.getCurrentItem() - 1);
				}
		}

		/**
		 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
		 * sequence.
		 */
		private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
				public ScreenSlidePagerAdapter(FragmentManager fm) {
						super(fm);
				}

				@Override
				public Fragment getItem(int position) {
						// Finish tutorial when sliding past last slide

						// Avoid overflowing
						position = Math.min(position, NUM_PAGES - 1);

						Bundle bundle = new Bundle();
						bundle.putInt("page", position);

						Fragment page = new TutorialSlideFragment();
						page.setArguments(bundle);
						return page;
				}

				@Override
				public int getCount() {
						return NUM_PAGES + 1;
				}
		}
}