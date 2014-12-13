package bealder.tourmalet;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by neamar on 12/13/14.
 */
public class SlideActivity extends FragmentActivity {


		/**
		 * The pager widget, which handles animation and allows swiping horizontally to access previous
		 * and next wizard steps.
		 */
		private ViewPager mPager;

		/**
		 * The pager adapter, which provides the pages to the view pager widget.
		 */
		private PagerAdapter mPagerAdapter;


		protected void initPager(FragmentStatePagerAdapter adapter) {
				// Instantiate a ViewPager and a PagerAdapter.
				mPager = (ViewPager) findViewById(R.id.pager);
				mPagerAdapter = adapter;
				mPager.setAdapter(mPagerAdapter);
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
}
