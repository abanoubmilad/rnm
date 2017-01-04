package abanoubm.ranm.main;

import java.util.List;

import abanoubm.ranm.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GridListAdapter extends ArrayAdapter<String> {
	private static int c1, c2;

	public GridListAdapter(Activity context, List<String> strs) {
		super(context, 0, strs);
		c1 = context.getResources().getColor(R.color.blue1);
		c2 = context.getResources().getColor(R.color.blue2);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.grid_item, parent, false);
		}
		TextView t = (TextView) convertView.findViewById(R.id.item_text);
		t.setText(getItem(position));

		if (position / 5 % 2 == 0)
			t.setBackgroundColor(c2);
		else
			t.setBackgroundColor(c1);

		return convertView;
	}
}