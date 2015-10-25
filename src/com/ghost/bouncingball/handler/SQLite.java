package com.ghost.bouncingball.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "BouncingBall_DB";

	private static final String TABLE_NAME_HIGHSCORE = "Highscore";
	private static final String KEY_HIGHSCORE = "Highscore";

	public SQLite(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME_HIGHSCORE).append(" (").append(KEY_HIGHSCORE).append(" TEXT PRIMARY KEY)")
				.toString();
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}

	public String getMyHighscore() {
		SQLiteDatabase db = getWritableDatabase();

		Cursor c = db.query(TABLE_NAME_HIGHSCORE, new String[] { KEY_HIGHSCORE }, null, null, null, null, null);
		if (c != null) {
			if (c.moveToFirst()) {
				String help = c.getString(c.getColumnIndex(KEY_HIGHSCORE));
				c.close();
				return help;
			}
		}
		return null;
	}

	public void updateMyHighscore(String newHighscore, String oldHighscore) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_HIGHSCORE, newHighscore);

		db.update(TABLE_NAME_HIGHSCORE, values, new StringBuilder(KEY_HIGHSCORE).append("=?").toString(), new String[] { oldHighscore });
	}

	public void setUpMyHighscore() {
		if (getMyHighscore() == null || getMyHighscore().equals("")) {
			SQLiteDatabase db = getWritableDatabase();

			ContentValues values = new ContentValues();

			values.put(KEY_HIGHSCORE, "0");

			db.insert(TABLE_NAME_HIGHSCORE, null, values);
		}
	}

}
