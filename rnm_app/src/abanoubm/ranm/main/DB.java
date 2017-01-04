package abanoubm.ranm.main;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DB extends SQLiteOpenHelper {

	private static String DB_PATH = "";
	private static String DB_NAME = ".systrdefault";
	private static String assets_DB_NAME = "systrdefault.zip";
	private static String Tb_NAME = "ranm_tb";
	private SQLiteDatabase db;
	private final Context mContext;
	private static DB dbm;

	public static DB getInstance(Context context) throws IOException {
		if (dbm == null)
			dbm = new DB(context);
		return dbm;
	}

	private DB(Context context) throws IOException {
		super(context, DB_NAME, null, 1);
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			DB_PATH = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/.systfile/";
		} else {
			if (android.os.Build.VERSION.SDK_INT >= 17) {
				DB_PATH = context.getApplicationInfo().dataDir + "/.systfile/";
			} else {
				DB_PATH = "/data/data/" + context.getPackageName()
						+ "/.systfile/";
			}
		}
		this.mContext = context;

		String mPath = DB_PATH + DB_NAME;

		if (!new File(DB_PATH + DB_NAME).exists())
			unpackZip();

		db = SQLiteDatabase.openDatabase(mPath, null,
				SQLiteDatabase.CREATE_IF_NECESSARY);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

	private void unpackZip() throws IOException {
		InputStream is;
		ZipInputStream zis;

		is = mContext.getAssets().open(assets_DB_NAME);
		zis = new ZipInputStream(new BufferedInputStream(is));

		while (zis.getNextEntry() != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024 * 4];
			int count;

			File dir = new File(DB_PATH);
			dir.mkdirs();

			FileOutputStream fout = new FileOutputStream(DB_PATH + DB_NAME);

			while ((count = zis.read(buffer)) != -1) {
				baos.write(buffer, 0, count);
				byte[] bytes = baos.toByteArray();
				fout.write(bytes);
				baos.reset();
			}

			fout.close();
			zis.closeEntry();
		}

		zis.close();

	}

	public ArrayList<Trnema> search(String text, int indicator) {
		String selectQuery;
		if (indicator == 1)
			selectQuery = "SELECT title,body FROM " + Tb_NAME + " WHERE "
					+ "title" + " like " + "'%" + text + "%'";
		else

			selectQuery = "SELECT title,body FROM " + Tb_NAME + " WHERE "
					+ "body" + " like " + "'%" + text + "%'";
		try {
			Cursor c = db.rawQuery(selectQuery, null);
			ArrayList<Trnema> results = new ArrayList<Trnema>(c.getCount());
			if (c.moveToNext()) {
				int titleCol = c.getColumnIndex("title");
				int bodyCol = c.getColumnIndex("body");
				do {
					results.add(new Trnema(c.getString(titleCol).replace("\\n",
							"\n"), c.getString(bodyCol).replace("\\n", "\n")));
				} while (c.moveToNext());
			}
			return results;
		} catch (Exception e) {
			return null;
		}

	}

	public ArrayList<Trnema> getTranem(String prefix) {
		String selectQuery = "SELECT title,body FROM " + Tb_NAME + " WHERE "
				+ "title" + " like '" + prefix + "%'";
		try {
			Cursor c = db.rawQuery(selectQuery, null);
			ArrayList<Trnema> results = new ArrayList<Trnema>(c.getCount());
			if (c.moveToNext()) {
				int titleCol = c.getColumnIndex("title");
				int bodyCol = c.getColumnIndex("body");
				do {
					results.add(new Trnema(c.getString(titleCol).replace("\\n",
							"\n"), c.getString(bodyCol).replace("\\n", "\n")));
				} while (c.moveToNext());
			}
			return results;
		} catch (Exception e) {
			return null;
		}

	}
}