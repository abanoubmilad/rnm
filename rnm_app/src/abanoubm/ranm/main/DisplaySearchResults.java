package abanoubm.ranm.main;

import abanoubm.ranm.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplaySearchResults extends Activity {
	private ListView lv;

	private class DisplayTask extends AsyncTask<Void, Void, TranemAdapter> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(DisplaySearchResults.this);
			pBar.setCancelable(false);
			pBar.show();
		}

		@Override
		protected TranemAdapter doInBackground(Void... params) {
			return new TranemAdapter(getApplicationContext(),
					RanemInfo.searchResults);
		}

		@Override
		protected void onPostExecute(TranemAdapter result) {
			lv.setAdapter(result);
			pBar.dismiss();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chooser);
		lv = (ListView) findViewById(R.id.list);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1,
					int position, long arg3) {
					Trnema section = (Trnema) parent
							.getItemAtPosition(position);

					Intent intent = new Intent(getApplicationContext(),
							DisplayTrnema.class);
					intent.putExtra("title", section.getTitle());
					intent.putExtra("body", section.getBody());
					startActivity(intent);
				
			}
		});
		((TextView) findViewById(R.id.subhead)).setText(R.string.display);

		if (RanemInfo.searchResults == null
				|| RanemInfo.searchResults.size() == 0) {
			Toast.makeText(getApplicationContext(), "ﻻ توجد ترانيم متوافقة",
					Toast.LENGTH_SHORT).show();
			finish();
		} else {
			Toast.makeText(
					getApplicationContext(),RanemInfo.getArabicNum(RanemInfo.searchResults.size())
							+ " ترنيمة/ترانيم ", Toast.LENGTH_SHORT).show();
			new DisplayTask().execute();
		}

	}
}
