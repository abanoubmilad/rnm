package abanoubm.ranm.main;

import abanoubm.ranm.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends Activity {
	private EditText et;
	private RadioGroup rg;

	private class SearchTask extends AsyncTask<String, Void, Boolean> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(Search.this);
			pBar.setCancelable(false);
			pBar.show();
		}
		@Override
		protected Boolean doInBackground(String... params) {
			try {
				RanemInfo.searchResults = DB.getInstance(
						getApplicationContext()).search(params[0],
						Integer.parseInt(params[1]));
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
		setContentView(R.layout.activity_search);

		((TextView) findViewById(R.id.subhead)).setText(R.string.search);

		et = (EditText) findViewById(R.id.sa_edittext);
		TextView search = (TextView) findViewById(R.id.sa_iv);
		rg = (RadioGroup) findViewById(R.id.radioGroup);

		rg.check(R.id.radio_title);

		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String input = et.getText().toString().trim();
				if (input.length() < 2) {
					Toast.makeText(getApplicationContext(),
							" قم بادخال كلمة أو جملة للبحث ", Toast.LENGTH_SHORT)
							.show();
				} else {
					int indicator = 2;
					switch (rg.getCheckedRadioButtonId()) {
					case R.id.radio_title:
						indicator = 1;
						break;
					}

					new SearchTask().execute(input, indicator + "");
				}
			}
		});

	}
}
