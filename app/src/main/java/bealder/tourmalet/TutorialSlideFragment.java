package bealder.tourmalet;

/**
 * Created by neamar on 12/13/14.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by neamar on 12/13/14.
 */
public class TutorialSlideFragment extends Fragment {
		public int[] PAGES = new int[]{R.drawable.tutorial_1, R.drawable.tutorial_2, R.drawable.tutorial_3};

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                         Bundle savedInstanceState) {
				int currentPage = getArguments().getInt("page", 1);

				ViewGroup rootView = (ViewGroup) inflater.inflate(
								R.layout.fragment_tutorial_slide, container, false);

				rootView.setBackgroundResource(PAGES[currentPage]);
				return rootView;
		}
}