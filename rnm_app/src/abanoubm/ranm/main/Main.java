package abanoubm.ranm.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import abanoubm.ranm.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		((TextView) findViewById(R.id.subhead)).setText(R.string.app_name);
		
		((TextView) findViewById(R.id.footer)).setText(String.format(
				getResources().getString(R.string.copyright),
				new SimpleDateFormat("yyyy", Locale.getDefault())
						.format(new Date())));
		
		ListView lv = (ListView) findViewById(R.id.home_list);
		lv.setAdapter(new ArrayAdapter<String>(
				getApplicationContext(), R.layout.item, R.id.item,
				RanemInfo.menuItems));

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					startActivity(new Intent(getApplicationContext(),
							Read.class));
					break;
				case 1:
					startActivity(new Intent(getApplicationContext(),
							Search.class));
					break;
				case 2:
					startActivity(new Intent(getApplicationContext(),
							Contact.class));
					break;
				}
			}
		});

	}
}
