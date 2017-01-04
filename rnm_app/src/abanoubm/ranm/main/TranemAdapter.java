package abanoubm.ranm.main;

import java.util.ArrayList;

import abanoubm.ranm.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TranemAdapter extends ArrayAdapter<Trnema> {

	public TranemAdapter(Context context, ArrayList<Trnema> hymns) {
		super(context, 0, hymns);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Trnema hymn = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.item, parent, false);
		}

		((TextView) convertView.findViewById(R.id.item)).setText(hymn
				.getTitle());
		return convertView;
	}
}