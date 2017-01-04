package abanoubm.ranm.main;

import java.util.Arrays;

import abanoubm.ranm.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class Read extends Activity {

	private class ReadTask extends AsyncTask<String, Void, Boolean> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(Read.this);
			pBar.setCancelable(false);
			pBar.show();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				RanemInfo.searchResults = DB.getInstance(
						getApplicationContext()).getTranem(params[0]);
				return true;
			} catch (Exception e) {
				return false;
			}

		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result)
				startActivity(new Intent(getApplicationContext(),
						DisplaySearchResults.class));
			else
				Toast.makeText(getApplicationContext(), R.string.err_msg_db,
						Toast.LENGTH_SHORT).show();
			pBar.dismiss();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_char_chooser);

		((TextView) findViewById(R.id.subhead)).setText(R.string.display);

		GridView lv = (GridView) findViewById(R.id.grid_view);
		lv.setAdapter(new GridListAdapter(this, Arrays
				.asList(RanemInfo.letters)));

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String temp = (String) parent.getItemAtPosition(position);
				new ReadTask().execute(temp);
			}

		});
	}
}
