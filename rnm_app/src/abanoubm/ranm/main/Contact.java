package abanoubm.ranm.main;

import abanoubm.ranm.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Contact extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		((TextView) findViewById(R.id.subhead)).setText(R.string.contact);
		
		TextView bostanfb = (TextView) findViewById(R.id.appfb_iv);
		TextView fb = (TextView) findViewById(R.id.fb_iv);
		TextView linkedin = (TextView) findViewById(R.id.linkedin_iv);
		TextView email = (TextView) findViewById(R.id.email_iv);

		email.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
			            "mailto","abanoubcs@gmail.com", null));
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ranm Feedback");
			startActivity(Intent.createChooser(emailIntent, "Feedback Email"));
			}
		});
		
		bostanfb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("https://www.facebook.com/ranmapp"));
				startActivity(browserIntent);
			}
		});

		fb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("https://www.facebook.com/EngineeroBono"));
				startActivity(browserIntent);
			}
		});

		linkedin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://eg.linkedin.com/in/abanoubmelad/"));
				startActivity(browserIntent);
			}
		});
		

	}
}
