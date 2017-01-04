package abanoubm.ranm.main;

import java.net.URLEncoder;
import java.util.List;

import abanoubm.ranm.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DisplayTrnema extends Activity {
	private String transText;
	private TextView bodytv;

	private String urlEncode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (Exception e) {
			return"";
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_trnema);

		((TextView) findViewById(R.id.subhead)).setText(getIntent().getStringExtra("title"));
		
		TextView tw = (TextView) findViewById(R.id.tw_iv);
		TextView fb = (TextView) findViewById(R.id.fb_iv);

		TextView titletv = (TextView) findViewById(R.id.title);
		titletv.setText(getIntent().getStringExtra("title"));
		bodytv = (TextView) findViewById(R.id.body);
		bodytv.setText(this.getIntent().getStringExtra("body"));

		transText = getIntent().getStringExtra("section");

		fb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (bodytv.isFocused()) {
					int selStart = bodytv.getSelectionStart();
					int selEnd = bodytv.getSelectionEnd();

					String temp = bodytv
							.getText()
							.subSequence(
									Math.max(0, Math.min(selStart, selEnd)),
									Math.max(0, Math.max(selStart, selEnd)))
							.toString();
					if (temp.length() > 2) {
						transText = temp;
					}
				}

				Intent shareIntent = new Intent(
						android.content.Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
						transText);
				v.getContext().startActivity(
						Intent.createChooser(shareIntent, "شير"));
			}
		});
		tw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (bodytv.isFocused()) {
					int selStart = bodytv.getSelectionStart();
					int selEnd = bodytv.getSelectionEnd();

					String temp = bodytv
							.getText()
							.subSequence(
									Math.max(0, Math.min(selStart, selEnd)),
									Math.max(0, Math.max(selStart, selEnd)))
							.toString();
					if (temp.length() > 2) {
						transText = temp;
					}
				}

				// Create intent using ACTION_VIEW and a normal Twitter url:
				String tweetUrl = String.format(
						"https://twitter.com/intent/tweet?text=%s",
						urlEncode(transText));
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(tweetUrl));

				// Narrow down to official Twitter app, if available:
				List<ResolveInfo> matches = getPackageManager()
						.queryIntentActivities(intent, 0);
				for (ResolveInfo info : matches) {
					if (info.activityInfo.packageName.toLowerCase().startsWith(
							"com.twitter")) {
						intent.setPackage(info.activityInfo.packageName);
					}
				}

				startActivity(intent);
			}
		});

	}
}
